package com.myob.iris;

import java.util.Objects;

public class CRUDResult {
    private boolean flag;
    private String response;

    public CRUDResult(boolean flag, String response) {
        this.flag = flag;
        this.response = response;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CRUDResult)) return false;
        CRUDResult that = (CRUDResult) o;
        return flag == that.flag &&
                response.equals(that.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flag, response);
    }
}
