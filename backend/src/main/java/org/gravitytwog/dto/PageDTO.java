package org.gravitytwog.dto;

import java.util.List;

public class PageDTO<T> {
    public List<T> items;

    public int totalCount;

    public PageDTO(List<T> items, int totalCount) {
        this.items = items;
        this.totalCount = totalCount;
    }
}
