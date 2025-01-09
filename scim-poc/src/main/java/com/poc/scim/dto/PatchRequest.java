package com.poc.scim.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.poc.scim.dto.misc.PatchOperation;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Data
public class PatchRequest {
    private Set<String> schemas = Collections.singleton(ScimConstant.URN_PATCH_OP);

    @JsonProperty("Operations")
    private List<PatchOperation> operations;
}
