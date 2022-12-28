package br.com.dbccompany.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties
public class TrilhaDTO {

    private String nome;
    private Integer edicao;
    private String anoEdicao;
    private Integer idTrilha;
    private Integer pontuacaoAluno;
    private List<TodosDTO> usuarios;
}
