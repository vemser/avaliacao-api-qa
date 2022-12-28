package br.com.dbccompany.model;

public class ModuloBuilder {

    private String nome;
    private String dataInicio;
    private String dataFim;
    private Integer idMoulo;
    private Integer idTrilha;

    public ModuloBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public ModuloBuilder dataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
        return this;
    }

    public ModuloBuilder dataFim(String dataFim) {
        this.dataFim = dataFim;
        return this;
    }

    public ModuloBuilder idMoulo(Integer idMoulo) {
        this.idMoulo = idMoulo;
        return this;
    }

    public ModuloBuilder idTrilha(Integer idTrilha) {
        this.idTrilha = idTrilha;
        return this;
    }

    public Modulo build() {
        return new Modulo(nome, dataInicio, dataFim);
    }

    public Modulo buildVinculacaoComTrilha() { return new Modulo(idMoulo, idTrilha);}
}
