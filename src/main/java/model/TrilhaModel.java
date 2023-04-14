package model;

import java.util.List;

public class TrilhaModel {
    private Integer idPrograma;
    private String nome;
    private String descricao;
    private String status;
    private Integer idTrilha;
    private Boolean ativo;

    private List<ModuloModel> modulos;
    private String timestamp;

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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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

    public List<ModuloModel> getModulos() {
        return modulos;
    }

    public void setModulos(List<ModuloModel> modulos) {
        this.modulos = modulos;
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
