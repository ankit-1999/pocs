package com.poc.scim.Controller;

import com.poc.scim.dto.PatchRequest;
import com.poc.scim.dto.ScimConstant;
import com.poc.scim.dto.group.GroupRecord;
import com.poc.scim.service.groupService.GroupService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController("scim2-group")
@RequestMapping(value = "/scim2/Groups", produces = ScimConstant.APPLICATION_JSON)
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(final GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<?> createGroup(@RequestBody final GroupRecord groupRecord) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(groupService.createGroup(groupRecord));
        } catch (Exception e) {
            return ResponseUtil.badRequest(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> replaceGroup(@PathVariable String id, @RequestBody GroupRecord groupRecord) {
        try {
            return ResponseEntity.ok().body(groupService.updateGroup(id, groupRecord));
        } catch (Exception e) {
            return ResponseUtil.badRequest(e);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchGroup(@PathVariable String id, @RequestBody PatchRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(groupService.patchGroup(id, request));
        } catch (Exception e) {
            return ResponseUtil.badRequest(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable String id) {
        try {
            groupService.deleteGroup(id);
            return ResponseEntity.noContent().build();
        } catch (final Exception e) {
            return ResponseUtil.badRequest(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readGroup(@PathVariable String id) {
        try {
            return ResponseEntity.ok().body(groupService.getGroup(id));
        } catch (Exception e) {
            return ResponseUtil.badRequest(e);
        }
    }
}
