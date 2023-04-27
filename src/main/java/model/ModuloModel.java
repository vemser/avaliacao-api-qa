package model;

public class ModuloModel {
    private String nome;
    private String descricao;
    private String status;
    private Integer idTrilha;
    private Integer idModulo;
    private String ativo;

    public Integer getIdTrilha() {
        return idTrilha;
    }

    public void setIdTrilha(Integer idTrilha) {
        this.idTrilha = idTrilha;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
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
    public void setIdModulo(Integer idModulo){this.idModulo = idModulo;}
    public Integer getIdModulo(){return idModulo;}


    public ModuloModel(String nome, String descricao, String status) {
        this.nome = nome;
        this.descricao = descricao;
        this.status = status;
    }

    public ModuloModel() {
    }
}
