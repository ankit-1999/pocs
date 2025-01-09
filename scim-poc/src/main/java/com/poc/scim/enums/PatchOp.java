package com.poc.scim.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PatchOp {
    ADD, REMOVE, REPLACE;

    @JsonCreator
    public static PatchOp fromString(String key) {
        return key == null
                ? null
                : PatchOp.valueOf(key.toUpperCase());
    }

    @JsonValue
    public String getKey() {
        return name().toLowerCase();
    }
}
