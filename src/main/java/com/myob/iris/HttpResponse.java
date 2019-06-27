package com.myob.iris;

import java.util.Objects;

public class HttpResponse {
    private int status;
    private String response;

    public HttpResponse(int status, String response) {
        this.status = status;
        this.response = response;
    }


    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HttpResponse)) return false;
        HttpResponse that = (HttpResponse) o;
        return status == that.status &&
                Objects.equals(response, that.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, response);
    }
}
