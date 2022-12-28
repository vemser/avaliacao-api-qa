package br.com.dbccompany.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties
public class ResponseDTO {

    private Date timestamp;
    private Integer status;
    private String message;
    private String error;
    private String path;
}
