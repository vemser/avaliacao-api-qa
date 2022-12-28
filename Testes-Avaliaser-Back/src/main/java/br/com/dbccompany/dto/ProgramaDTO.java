package br.com.dbccompany.dto;

import lombok.Data;

@Data
public class ProgramaDTO {

    private Integer id;
    private String nome;
    private String situacao;
    private String descricao;
    private String dataInicio;
    private String dataFim;
}
