package com.poc.scim.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.poc.scim.util.DateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MetaRecord {
    private String resourceType;

    @JsonIgnore
    private Date created;

    @JsonIgnore
    private Date lastModified;

    private String location;
    private String version;

    @JsonSetter("created")
    public void setCreatedByStr(String created) {
        this.created = DateUtil.parseDate(created);
    }

    @JsonSetter("lastModified")
    public void setLastModifiedByStr(String lastModified) {
        this.lastModified = DateUtil.parseDate(lastModified);
    }

    @JsonGetter("created")
    public String getCreatedToStr() {
        if (created == null) {
            return null;
        }
        return ScimConstant.SCIM_DATE_FORMAT.format(created);
    }

    @JsonGetter("lastModified")
    public String getLastModifiedToStr() {
        if (lastModified == null) {
            return null;
        }
        return ScimConstant.SCIM_DATE_FORMAT.format(lastModified);
    }
}
