package com.villamorvinzie.view.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class ResponseDto {
    private LocalDateTime timestamp;
    private String traceId;

    public ResponseDto(LocalDateTime timestamp, String traceId) {
        this.timestamp = timestamp;
        this.traceId = traceId;
    }

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }
}
