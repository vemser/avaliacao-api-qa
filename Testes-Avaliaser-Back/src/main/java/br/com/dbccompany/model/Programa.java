package br.com.dbccompany.model;

import lombok.Data;

@Data
public class Programa {

    private String nome;
    private String situacao;
    private String descricao;
    private String dataInicio;
    private String dataFim;

    public Programa(String nome, String situacao, String descricao, String dataInicio, String dataFim) {
        this.nome = nome;
        this.situacao = situacao;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    @Override
    public String toString() {
        return "Programa{" +
                "nome='" + nome + '\'' +
                ", situacao='" + situacao + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataInicio='" + dataInicio + '\'' +
                ", dataFim='" + dataFim + '\'' +
                '}';
    }
}
