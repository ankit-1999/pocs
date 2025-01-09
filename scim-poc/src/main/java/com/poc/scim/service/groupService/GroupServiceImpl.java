package com.poc.scim.service.groupService;

import com.poc.scim.dto.ErrorRecord;
import com.poc.scim.dto.PatchRequest;
import com.poc.scim.dto.ScimConstant;
import com.poc.scim.dto.group.GroupRecord;
import com.poc.scim.exception.ScimException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class GroupServiceImpl implements GroupService {

    private final List<GroupRecord> groups = new ArrayList<>();
    @Value("${spring.application.scimBaseUrl}")
    private String scimBaseUrl;

    @Override
    public GroupRecord createGroup(final GroupRecord record) {
        final String groupId = UUID.randomUUID().toString();
        record.setId(groupId);
        record.getMeta().setCreated(Date.from(Instant.now()));
        record.getMeta().setLastModified(Date.from(Instant.now()));
        record.getMeta().setLocation(scimBaseUrl + ScimConstant.PATH_GROUPS + "/" + groupId);
        record.getMeta().setVersion(String.valueOf(Date.from(Instant.now())));
        groups.add(record);
        System.out.println("scim group create successfully");
        return record;
    }

    @Override
    public GroupRecord updateGroup(final String groupId, final GroupRecord record) {
        final GroupRecord groupRecord = findGroup(groupId);
        if (Objects.nonNull(groupRecord)) {
            groupRecord.setDisplayName(record.getDisplayName());
            groupRecord.getMeta().setLastModified(Date.from(Instant.now()));
            groupRecord.getMeta().setLocation(scimBaseUrl + ScimConstant.PATH_GROUPS + "/" + groupRecord.getId());
            groupRecord.getMeta().setVersion(String.valueOf(Date.from(Instant.now())));
        } else {
            throw new ScimException(new ErrorRecord(404, "Not Found"));
        }
        deleteGroup(groupId);
        groups.add(groupRecord);
        System.out.println("scim group updated successfully");
        return groupRecord;
    }

    @Override
    public void deleteGroup(final String groupId) {
        final GroupRecord groupRecord = findGroup(groupId);
        if (Objects.nonNull(groupRecord)) {
            removeGroup(groupRecord.getId());
        } else {
            throw new ScimException(new ErrorRecord(404, "Not Found"));
        }
        System.out.println("scim group deleted successfully");
    }

    @Override
    public GroupRecord getGroup(final String groupId) {
        final GroupRecord groupRecord = findGroup(groupId);
        if (Objects.nonNull(groupRecord)) {
            return groupRecord;
        } else {
            throw new ScimException(new ErrorRecord(404, "Not Found"));
        }
    }

    @Override
    public GroupRecord patchGroup(final String groupId, final PatchRequest request) {
        return getGroup(groupId);
    }

    private GroupRecord findGroup(final String id) {
        return groups.stream()
                .filter(groupRecord -> groupRecord.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private void removeGroup(final String groupId) {
        groups.removeIf(groupRecord -> groupRecord.getId().equals(groupId));
    }
}
