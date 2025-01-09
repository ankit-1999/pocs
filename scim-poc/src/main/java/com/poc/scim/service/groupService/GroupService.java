package com.poc.scim.service.groupService;

import com.poc.scim.dto.PatchRequest;
import com.poc.scim.dto.group.GroupRecord;
import com.poc.scim.exception.ScimException;

public interface GroupService {

    GroupRecord createGroup(GroupRecord record) throws ScimException;

    GroupRecord patchGroup(String groupId, PatchRequest request) throws ScimException;

    GroupRecord updateGroup(String groupId, GroupRecord record) throws ScimException;

    void deleteGroup(String groupId) throws ScimException;

    GroupRecord getGroup(String groupId) throws ScimException;
}
