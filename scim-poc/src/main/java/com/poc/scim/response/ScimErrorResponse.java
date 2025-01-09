package com.poc.scim.response;

import com.poc.scim.dto.ScimConstant;
import lombok.Data;

import java.util.Set;

@Data
public class ScimErrorResponse {
    private final Set<String> schemas = Set.of(ScimConstant.URN_ERROR);
    private String status;
    private String detail;
}
