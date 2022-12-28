package br.com.dbccompany.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties
public class PageDTO {

    private String totalElementos;
    private String quantidadePaginas;
    private String pagina;
    private String tamanho;
    private List<TodosDTO> elementos;
}
