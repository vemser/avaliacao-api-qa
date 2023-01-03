package br.com.dbccompany.dto;

import lombok.Data;

@Data
public class GeralDTO {

    // Programa *********

    private Integer idPrograma;
    private String nome;
    private String situacao;
    private String descricao;
    private String dataInicio;
    private String dataFim;


}
