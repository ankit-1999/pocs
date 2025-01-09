package com.poc.scim.Controller;

import com.poc.scim.dto.ErrorRecord;
import com.poc.scim.dto.PatchRequest;
import com.poc.scim.dto.ScimConstant;
import com.poc.scim.dto.user.UserRecord;
import com.poc.scim.exception.ScimException;
import com.poc.scim.response.ScimErrorResponse;
import com.poc.scim.service.userService.UserService;
import com.poc.scim.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/scim2/Users", produces = ScimConstant.APPLICATION_JSON)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody final UserRecord userRecord) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRecord));
        } catch (final Exception e) {
            return ResponseUtil.badRequest(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> replaceUser(@PathVariable final String id, @RequestBody final UserRecord userRecord) {
        try {
            return ResponseEntity.ok(userService.updateUser(id, userRecord));
        } catch (final Exception e) {
            return ResponseUtil.badRequest(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable final String id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (final Exception e) {
            return ResponseUtil.badRequest(e);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable final String userId) {
        try {
            return ResponseEntity.ok(userService.getUser(userId));
        } catch (ScimException se) {
            final ErrorRecord errorRecord = se.getError();
            final ScimErrorResponse errorResponse = new ScimErrorResponse();
            errorResponse.setDetail(errorRecord.getDetail());
            errorResponse.setStatus(String.valueOf(errorRecord.getStatus()));
            return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorRecord.getStatus()));
        } catch (final Exception e) {
            return ResponseUtil.badRequest(e);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchUser(@PathVariable String id, @RequestBody PatchRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.patchUser(id, request));
        } catch (Exception e) {
            return ResponseUtil.badRequest(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> searchUsers(@RequestParam(required = false, name = "filter") final String filter) {
        try {
            return ResponseEntity.ok().body(userService.searchUser(filter));
        } catch (Exception e) {
            return ResponseUtil.badRequest(e);
        }
    }
}
