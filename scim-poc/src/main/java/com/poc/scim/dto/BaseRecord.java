package com.poc.scim.dto;

import lombok.Data;

import java.util.Set;

@Data
public abstract class BaseRecord {
    protected String id;
    protected String externalId;
    protected MetaRecord meta;
    protected Set<String> schemas;
}
