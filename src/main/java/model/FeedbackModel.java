package model;

public class FeedbackModel {
    private Integer idAvaliacao;
    private Integer nota;
    private String descricao;
    private String tipoFeedback;
    private String cargoResponsavel;
    private Integer idFeedBack;
    private String status;
    private String loginResponsavel;
    private Boolean ativo;
    private Integer idPrograma;

    public FeedbackModel(Integer idAvaliacao, Integer nota, String descricao, String tipoFeedback, String cargoResponsavel, String status) {
        this.idAvaliacao = idAvaliacao;
        this.nota = nota;
        this.descricao = descricao;
        this.tipoFeedback = tipoFeedback;
        this.cargoResponsavel = cargoResponsavel;
        this.status = status;
    }

    public FeedbackModel(){

    }

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoFeedback() {
        return tipoFeedback;
    }

    public void setTipoFeedback(String tipoFeedback) {
        this.tipoFeedback = tipoFeedback;
    }

    public String getCargoResponsavel() {
        return cargoResponsavel;
    }

    public void setCargoResponsavel(String cargoResponsavel) {
        this.cargoResponsavel = cargoResponsavel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIdFeedBack() {
        return idFeedBack;
    }

    public void setIdFeedBack(Integer idFeedBack) {
        this.idFeedBack = idFeedBack;
    }

    public String getLoginResponsavel() {
        return loginResponsavel;
    }

    public void setLoginResponsavel(String loginResponsavel) {
        this.loginResponsavel = loginResponsavel;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Integer idPrograma) {
        this.idPrograma = idPrograma;
    }
}
