package com.poc.scim.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class ErrorRecord {
    private final Set<String> schemas = Set.of(ScimConstant.URN_ERROR);
    private String detail;
    private int status;

    public ErrorRecord(final int status, final String detail) {
        this.detail = detail;
        this.status = status;
    }

    @Override
    @JsonIgnore
    public String toString() {
        return "ScimError{" +
                ", detail='" + detail + '\'' +
                ", status=" + status +
                '}';
    }
}
