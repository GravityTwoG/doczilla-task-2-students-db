package org.gravitytwog.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class GetPageDTO {
    @NotNull
    @Min(1)
    public int pageSize;

    @NotNull
    @Min(1)
    public int pageNumber;

    public GetPageDTO(
            int pageSize,
            int pageNumber
    ) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }
}
