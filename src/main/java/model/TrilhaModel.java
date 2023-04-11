package model;

public class TrilhaModel {
    private Integer idPrograma;
    private String nome;
    private String descricao;
    private String status;
    private Integer idTrilha;
    private Boolean ativo;

    public TrilhaModel() {
    }

    public TrilhaModel(Integer idPrograma, String nome, String descricao, String status) {
        this.idPrograma = idPrograma;
        this.nome = nome;
        this.descricao = descricao;
        this.status = status;
    }

    public TrilhaModel(Integer idPrograma, String nome, String descricao, String status, Integer idTrilha, Boolean ativo) {
        this.idPrograma = idPrograma;
        this.nome = nome;
        this.descricao = descricao;
        this.status = status;
        this.idTrilha = idTrilha;
        this.ativo = ativo;
    }

    public Integer getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Integer idPrograma) {
        this.idPrograma = idPrograma;
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

    public Integer getIdTrilha() {
        return idTrilha;
    }

    public void setIdTrilha(Integer idTrilha) {
        this.idTrilha = idTrilha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "TrilhaModel{" +
                "idPrograma=" + idPrograma +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", status='" + status + '\'' +
                ", idTrilha=" + idTrilha +
                ", ativo=" + ativo +
                '}';
    }
}
