package com.filmapp.commons.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PaginationResult<T> {
    List<T> result;
    long sizeAll;
}
