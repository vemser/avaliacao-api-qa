package br.com.dbccompany.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties
public class ModuloDTO {

    private String nome;
    private String dataInicio;
    private String dataFim;
    private TrilhaDTO trilhas;
    private Integer idModulo;
}
