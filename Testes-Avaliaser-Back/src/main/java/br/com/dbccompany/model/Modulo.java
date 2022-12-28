package br.com.dbccompany.model;


import lombok.Data;


@Data
public class Modulo {

    private String nome;
    private String dataInicio;
    private String dataFim;
    private Integer idMoulo;
    private Integer idTrilha;


    public Modulo(String nome, String dataInicio, String dataFim) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Modulo(Integer idMoulo, Integer idTrilha) {
        this.idMoulo = idMoulo;
        this.idTrilha = idTrilha;
    }

    @Override
    public String toString() {
        return "Modulo{" +
                "nome='" + nome + '\'' +
                ", dataInicio='" + dataInicio + '\'' +
                ", dataFim='" + dataFim + '\'' +
                '}';
    }
}
