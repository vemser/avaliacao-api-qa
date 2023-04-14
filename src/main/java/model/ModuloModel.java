package model;

public class ModuloModel {
    private String nome;
    private String descricao;
    private String status;
    private Integer idTrilha;

    public Integer getIdTrilha() {
        return idTrilha;
    }

    public void setIdTrilha(Integer idTrilha) {
        this.idTrilha = idTrilha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ModuloModel(String nome, String descricao, String status) {
        this.nome = nome;
        this.descricao = descricao;
        this.status = status;
    }

    public ModuloModel() {
    }
}
