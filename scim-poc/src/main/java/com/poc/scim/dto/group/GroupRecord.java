package com.poc.scim.dto.group;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.poc.scim.dto.BaseRecord;
import com.poc.scim.dto.ExtensionRecord;
import com.poc.scim.dto.MetaRecord;
import com.poc.scim.dto.ScimConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class GroupRecord extends BaseRecord {
    private String displayName;
    private List<GroupMember> members;
    @JsonIgnore
    private Map<String, ExtensionRecord> extensions = new HashMap<>();

    public GroupRecord() {
        this.meta = new MetaRecord();
        meta.setResourceType(ScimConstant.NAME_GROUP);

        this.schemas = Set.of(ScimConstant.URN_GROUP);
    }

    @JsonAnySetter
    protected void setExtensions(String key, Object value) {
        if (value instanceof ExtensionRecord) {
            this.extensions.put(key, (ExtensionRecord) value);
            if (this.schemas != null) {
                this.schemas.add(key);
            }
        } else {
            throw new IllegalArgumentException("Invalid field provided " + key);
        }
    }

    @JsonAnyGetter
    public Map<String, ExtensionRecord> getExtensions() {
        return extensions;
    }


    @Data
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class GroupMember {
        private String type;
        private String display;
        private String value;
        @JsonProperty("$ref")
        private String ref;
    }
}

