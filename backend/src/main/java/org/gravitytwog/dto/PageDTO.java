package org.gravitytwog.dto;

import java.util.ArrayList;

public class PageDTO<T> {
    public ArrayList<T> items;

    public int totalCount;

    public PageDTO(ArrayList<T> items, int totalCount) {
        this.items = items;
        this.totalCount = totalCount;
    }
}
