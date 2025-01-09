package com.poc.scim.util;

import com.poc.scim.dto.ErrorRecord;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static ResponseEntity<?> badRequest(Exception e) {
        return ResponseEntity.badRequest().body(new ErrorRecord(400, e.getMessage()));
    }
}
