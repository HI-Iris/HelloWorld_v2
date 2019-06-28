package com.myob.iris;

import java.util.Objects;

public class HttpResponse {
    private int statusCode;
    private String response;

    public HttpResponse(int statusCode, String response) {
        this.statusCode = statusCode;
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HttpResponse)) return false;
        HttpResponse that = (HttpResponse) o;
        return statusCode == that.statusCode &&
                Objects.equals(response, that.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusCode, response);
    }
}
