package br.com.dbccompany.model;

import lombok.Data;

import java.util.List;

@Data
public class Trilha {

    private String nome;
    private Integer edicao;
    private String anoEdicao;
    private List<Integer> idTrilha;
    private String login;

    public Trilha(String nome, Integer edicao, String anoEdicao) {
        this.nome = nome;
        this.edicao = edicao;
        this.anoEdicao = anoEdicao;
    }

    public Trilha(List<Integer> idTrilha, String login) {
        this.idTrilha = idTrilha;
        this.login = login;
    }

    @Override
    public String toString() {
        return "Trilha{" +
                "nome='" + nome + '\'' +
                ", edicao=" + edicao +
                ", anoEdicao='" + anoEdicao + '\'' +
                '}';
    }
}
