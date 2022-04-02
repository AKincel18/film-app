package com.filmapp.commons.pagination;

import org.springframework.http.ResponseEntity;

import java.util.List;

public class PaginationResponseEntity {

    public static <T>ResponseEntity<List<T>> ok(PaginationResult<T> result) {
        return ResponseEntity.ok()
                .header("X-MAX-RESULTS", String.valueOf(result.getSizeAll()))
                .header("Access-Control-Expose-Headers", "X-MAX-RESULTS")
                .body(result.getResult());
    }
}
