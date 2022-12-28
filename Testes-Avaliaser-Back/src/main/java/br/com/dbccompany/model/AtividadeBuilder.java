package br.com.dbccompany.model;

import br.com.dbccompany.dto.TrilhaDTO;

import java.util.List;

public class AtividadeBuilder {

    private String titulo;
    private String instrucoes;
    private Integer pesoAtividade;
    private String dataCriacao;
    private String dataEntrega;
    private Integer idModulo;
    private Integer pontuacao;
    private String link;
    private List<TrilhaDTO> trilha;

    public AtividadeBuilder titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public AtividadeBuilder instrucoes(String instrucoes) {
        this.instrucoes = instrucoes;
        return this;
    }

    public AtividadeBuilder pesoAtividade(Integer pesoAtividade) {
        this.pesoAtividade = pesoAtividade;
        return this;
    }

    public AtividadeBuilder dataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
        return this;
    }

    public AtividadeBuilder dataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
        return this;
    }

    public AtividadeBuilder idModulo(Integer idModulo) {
        this.idModulo = idModulo;
        return this;
    }

    public AtividadeBuilder trilha(List<TrilhaDTO> trilha) {
        this.trilha = trilha;
        return this;
    }

    public Atividade build() {
        return new Atividade(titulo, instrucoes, pesoAtividade, dataCriacao, dataEntrega, idModulo, trilha);
    }
}
