package br.com.dbccompany.dto;

import lombok.Data;

import java.util.List;

@Data
public class AtividadeDTO {

    private Integer idAtividade;
    private Integer idModulo;
    private String titulo;
    private String instrucoes;
    private Integer pesoAtividade;
    private String dataCriacao;
    private String dataEntrega;
    private Integer pontuacao;
    private String link;
    private Integer statusAtividade;
    private String nomeInstrutor;
    private List<TrilhaDTO> trilhas;
}
