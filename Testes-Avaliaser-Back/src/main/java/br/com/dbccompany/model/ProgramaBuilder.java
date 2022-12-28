package br.com.dbccompany.model;

public class ProgramaBuilder {

    private String nome;
    private String situacao;
    private String descricao;
    private String dataInicio;
    private String dataFim;

    public ProgramaBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public ProgramaBuilder situacao(String situacao) {
        this.situacao = situacao;
        return this;
    }

    public ProgramaBuilder descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public ProgramaBuilder dataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
        return this;
    }

    public ProgramaBuilder dataFim(String dataFim) {
        this.dataFim = dataFim;
        return this;
    }

    public Programa build() {
        return new Programa(nome, situacao, descricao, dataInicio, dataFim);
    }
}
