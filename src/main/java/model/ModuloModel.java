package model;

public class ModuloModel {
    private String nome;
    private String descricao;
    private String status;

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

    public ModuloModel() {
        this.nome = nome;
        this.descricao = descricao;
        this.status = status;
    }
}
