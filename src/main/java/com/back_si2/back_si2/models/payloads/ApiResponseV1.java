package com.back_si2.back_si2.models.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseV1<T> {
    @Builder.Default
    private int status = 200;
    @Builder.Default
    private boolean success = true;
    @Builder.Default
    private String message = "";
    @Builder.Default
    private T data = null;
}
