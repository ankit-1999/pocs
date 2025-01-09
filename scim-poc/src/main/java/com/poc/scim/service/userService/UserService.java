package com.poc.scim.service.userService;

import com.poc.scim.dto.PatchRequest;
import com.poc.scim.dto.misc.ListResponse;
import com.poc.scim.dto.user.UserRecord;
import com.poc.scim.exception.ScimException;

public interface UserService {

    UserRecord createUser(UserRecord record) throws ScimException;

    UserRecord updateUser(String userId, UserRecord record) throws ScimException;

    void deleteUser(String userId) throws ScimException;

    UserRecord getUser(String id) throws ScimException;

    UserRecord patchUser(String userId, PatchRequest request) throws ScimException;

    ListResponse<UserRecord> searchUser(String record) throws ScimException;
}
