package br.com.dbccompany.model;

import lombok.Data;

@Data
public class Trilha {

    private Integer idTrilha;
    private String nome;
    private String descricao;

    public Trilha(Integer idTrilha, String nome, String descricao) {
        this.idTrilha = idTrilha;
        this.nome = nome;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Trilha{" +
                "idTrilha=" + idTrilha +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
