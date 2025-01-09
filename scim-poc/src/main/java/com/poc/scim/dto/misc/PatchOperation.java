package com.poc.scim.dto.misc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.poc.scim.enums.PatchOp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@AllArgsConstructor
public class PatchOperation {
    private PatchOp op;
    private String path;
    private Object value;
}
