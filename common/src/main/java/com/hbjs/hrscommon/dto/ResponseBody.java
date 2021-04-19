package com.hbjs.hrscommon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBody<T> {

    private static final long serialVersionUID = 2510159376609227086L;

    private Map<String, String> headers;

    private T data;

    public ResponseBody<T> addHead(Map<String, String> headers) {
        if (this.headers == null) {
            this.headers = headers;
        } else {
            this.headers.putAll(headers);
        }
        return this;
    }

    public ResponseBody<T> addHead(String key, String value) {
        if (this.headers == null) {
            this.headers = new HashMap<>();
        }
        headers.put(key, value);
        return this;
    }

}
