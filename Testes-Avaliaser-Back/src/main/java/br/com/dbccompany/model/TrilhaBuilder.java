package br.com.dbccompany.model;

public class TrilhaBuilder {

    private Integer idTrilha;
    private String nome;
    private String descricao;

    public TrilhaBuilder idTrilha(Integer idTrilha) {
        this.idTrilha = idTrilha;
        return this;
    }

    public TrilhaBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public TrilhaBuilder descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public Trilha build() {
        return new Trilha(idTrilha, nome, descricao);
    }
}
