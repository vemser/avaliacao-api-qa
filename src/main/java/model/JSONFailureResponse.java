package model;

import java.util.List;

public class JSONFailureResponse {
    private String timestamp;
    private Integer status;
    private List<String> errors;

    public JSONFailureResponse() {
    }

    public JSONFailureResponse(String timestamp, Integer status, List<String> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.errors = errors;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
