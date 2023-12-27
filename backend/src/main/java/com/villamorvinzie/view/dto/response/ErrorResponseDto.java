package com.villamorvinzie.view.dto.response;

import java.time.LocalDateTime;

public class ErrorResponseDto extends ResponseDto {

    private String message;

    public ErrorResponseDto(String message, LocalDateTime timestamp, String traceId) {
        super(timestamp, traceId);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
