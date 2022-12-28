package br.com.dbccompany.model;

import java.util.ArrayList;
import java.util.List;

public class TrilhaBuilder {

    private String nome;
    private Integer edicao;
    private String anoEdicao;
    private Integer idTrilha;
    private String login;

    public TrilhaBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public TrilhaBuilder edicao(Integer edicao) {
        this.edicao = edicao;
        return this;
    }

    public TrilhaBuilder anoEdicao(String anoEdicao) {
        this.anoEdicao = anoEdicao;
        return this;
    }

    public TrilhaBuilder idTrilha(Integer idTrilha) {
        this.idTrilha = idTrilha;
        return this;
    }

    public TrilhaBuilder login(String login) {
        this.login = login;
        return this;
    }

    public Trilha build() {
        return new Trilha(nome, edicao, anoEdicao);
    }

    public Trilha buildVincular() {
        List<Integer> id = new ArrayList<>();
        id.add(idTrilha);
        return new Trilha(id, login);
    }
}
