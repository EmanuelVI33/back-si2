package com.back_si2.back_si2.models.payloads;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiResponse {
    private Date time = new Date();
    private String message;
    private String url;

    public ApiResponse(String mensaje, String url) {
        this.message = mensaje;
        this.url = url.replace("uri=", "");
    }
}
