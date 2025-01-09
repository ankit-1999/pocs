package com.poc.scim.service.userService;

import com.poc.scim.dto.ErrorRecord;
import com.poc.scim.dto.PatchRequest;
import com.poc.scim.dto.ScimConstant;
import com.poc.scim.dto.misc.ListResponse;
import com.poc.scim.dto.user.UserRecord;
import com.poc.scim.exception.ScimException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final List<UserRecord> users = new ArrayList<>();
    @Value("${spring.application.scimBaseUrl}")
    private String scimBaseUrl;

    @Override
    public UserRecord createUser(final UserRecord record) {
        final String userId = UUID.randomUUID().toString();
        record.setId(userId);
        record.setSchemas(Set.of(ScimConstant.URN_USER));
        record.getMeta().setCreated(Date.from(Instant.now()));
        record.getMeta().setLastModified(Date.from(Instant.now()));
        record.getMeta().setLocation(scimBaseUrl + ScimConstant.PATH_USERS + "/" + userId);
        record.getMeta().setVersion(String.valueOf(Date.from(Instant.now())));
        System.out.println("scim user create successfully");
        users.add(record);
        System.out.println(record);
        return record;
    }

    @Override
    public UserRecord updateUser(final String userId, final UserRecord record) {
        final UserRecord userRecord = findUser(userId);
        if (Objects.nonNull(userRecord)) {
            userRecord.setName(record.getName());
            userRecord.setUserName(record.getUserName());
            userRecord.getMeta().setLastModified(Date.from(Instant.now()));
            userRecord.getMeta().setLocation(scimBaseUrl + ScimConstant.PATH_USERS + "/" + userRecord.getId());
            userRecord.getMeta().setVersion(String.valueOf(Date.from(Instant.now())));
            deleteUser(userId);
            users.add(userRecord);
            System.out.println("scim user updated successfully");
            System.out.println(userRecord);
            return userRecord;
        } else {
            record.setId(userId);
            record.getMeta().setCreated(Date.from(Instant.now()));
            record.getMeta().setLastModified(Date.from(Instant.now()));
            record.getMeta().setLocation(scimBaseUrl + ScimConstant.PATH_USERS + "/" + userId);
            record.getMeta().setVersion(String.valueOf(Date.from(Instant.now())));
            users.add(record);
            System.out.println("scim user created successfully");
            System.out.println(record);
            return record;
        }
    }

    @Override
    public void deleteUser(final String userId) {
        final UserRecord userRecord = getUser(userId);
        if (Objects.nonNull(getUser(userId))) {
            removeUser(userRecord.getId());
        } else {
            throw new ScimException(new ErrorRecord(404, "Resource " + userId + " not found"));
        }
        System.out.println("scim user deleted successfully");
    }

    @Override
    public UserRecord getUser(final String userId) {
        final UserRecord userRecord = findUser(userId);
        if (Objects.isNull(userRecord)) {
            throw new ScimException(new ErrorRecord(404, "Resource " + userId + " not found"));
        }
        System.out.println("scim user fetched successfully");
        System.out.println(userRecord);
        return userRecord;
    }

    @Override
    public UserRecord patchUser(String userId, PatchRequest request) throws ScimException {
        System.out.println("scim user patch successfully");
        System.out.println(request);
        return getUser(userId);
    }

    @Override
    public ListResponse<UserRecord> searchUser(final String filter) throws ScimException {
        final ListResponse<UserRecord> response = new ListResponse<>();
        response.setResources(new ArrayList<>());
        String userName;
        if (filter != null && filter.startsWith("userName eq ")) {
            userName = filter.substring("userName eq ".length()).replace("\"", "");
        } else {
            userName = null;
        }
        final List<UserRecord> filteredUsers;
        if (userName != null) {
            filteredUsers = users.stream()
                    .filter(user -> user.getUserName().equals(userName))
                    .collect(Collectors.toList());
        } else {
            filteredUsers = new ArrayList<>(users);
        }
        response.setResources(filteredUsers);
        response.setItemsPerPage(filteredUsers.size());
        response.setStartIndex(1);
        response.setTotalResults(filteredUsers.size());
        response.setSchemas(Set.of(ScimConstant.URN_LIST_RESPONSE));
        System.out.println("scim users searched successfully");
        return response;
    }

    private UserRecord findUser(final String id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private void removeUser(final String userId) {
        users.removeIf(user -> user.getId().equals(userId));
    }
}
