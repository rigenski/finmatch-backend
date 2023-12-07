package com.rigenski.finmatch.response;

public class ApiResponse<T> {
    private final String result;
    private final String message;
    private final T content;

    public ApiResponse(String result, String message, T content) {
        this.result = result;
        this.message = message;
        this.content = content;
    }

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public T getContent() {
        return content;
    }
}