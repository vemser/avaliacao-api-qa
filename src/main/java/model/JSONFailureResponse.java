package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class JSONFailureResponse {
    private String timestamp;
    private Integer status;
    private List<String> errors;
    private List<String> messages;
    private String error;
    private String message;
    private String path;
}
