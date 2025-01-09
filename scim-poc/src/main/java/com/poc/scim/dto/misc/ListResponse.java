package com.poc.scim.dto.misc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.poc.scim.dto.BaseRecord;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Data
public class ListResponse<T extends BaseRecord> {

    /**
     * This must be ScimConstant.URN_LIST_RESPONSE
     */
    private Set<String> schemas;
    private Integer totalResults;
    private Integer itemsPerPage;
    private Integer startIndex;

    @JsonProperty("Resources")
    private List<T> resources;

    @JsonIgnore
    public void addResource(T resource) {
        if (resources == null) {
            resources = new ArrayList<>();
        }
        resources.add(resource);
    }
}
