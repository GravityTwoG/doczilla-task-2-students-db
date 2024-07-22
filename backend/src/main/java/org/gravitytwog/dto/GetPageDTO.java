package org.gravitytwog.dto;

public class GetPageDTO {
    public int pageSize;

    public int pageNumber;

    public GetPageDTO(
            int pageSize,
            int pageNumber
    ) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }
}
