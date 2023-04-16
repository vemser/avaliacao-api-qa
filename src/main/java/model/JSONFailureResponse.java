package model;

import java.util.List;

public class JSONFailureResponse {
    private String timestamp;
    private Integer status;
    private List<String> errors;
    private List<String> messages;
    private String error;
    private String message;
    private String path;


    public JSONFailureResponse() {
    }

    public JSONFailureResponse(String timestamp, Integer status, List<String> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.errors = errors;
        this.messages = errors;
    }

    public JSONFailureResponse(String timestamp, Integer status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }

    public JSONFailureResponse(String timestamp, Integer status, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "JSONFailureResponse{" +
            "timestamp='" + timestamp + '\'' +
            ", status=" + status +
            ", errors=" + errors +
            ", messages=" + messages +
            ", error='" + error + '\'' +
            ", message='" + message + '\'' +
            ", path='" + path + '\'' +
            '}';
    }
}
