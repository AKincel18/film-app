package com.filmapp.commons.pagination;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class PaginationResponseEntity {

    public static <T> ResponseEntity<List<T>> ok(Page<T> result) {
        return ResponseEntity.ok()
                .header("X-MAX-RESULTS", String.valueOf(result.getTotalElements()))
                .header("Access-Control-Expose-Headers", "X-MAX-RESULTS")
                .body(result.getContent());
    }
}
