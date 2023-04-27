package model;

import java.util.List;

public class AvaliacaoModel {
    private List<FeedbackModel> feedbacks;
    private Integer idAvaliacao;
    private Integer idAcompanhamento;
    private Integer idEstagiario;
    private String objetivoProfissional;
    private String recomendacaoMelhorias;
    private String link;
    private String status;
    private String ativo;

    public AvaliacaoModel(Integer idAcompanhamento, Integer idEstagiario, String objetivoProfissional, String recomendacaoMelhorias, String link, String status) {
        this.idAcompanhamento = idAcompanhamento;
        this.idEstagiario = idEstagiario;
        this.objetivoProfissional = objetivoProfissional;
        this.recomendacaoMelhorias = recomendacaoMelhorias;
        this.link = link;
        this.status = status;
    }

    public AvaliacaoModel() {
    }

    public List<FeedbackModel> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedbackModel> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public Integer getIdAcompanhamento() {
        return idAcompanhamento;
    }

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public void setIdAcompanhamento(Integer idAcompanhamento) {
        this.idAcompanhamento = idAcompanhamento;
    }

    public Integer getIdEstagiario() {
        return idEstagiario;
    }

    public void setIdEstagiario(Integer idEstagiario) {
        this.idEstagiario = idEstagiario;
    }

    public String getObjetivoProfissional() {
        return objetivoProfissional;
    }

    public void setObjetivoProfissional(String objetivoProfissional) {
        this.objetivoProfissional = objetivoProfissional;
    }

    public String getRecomendacaoMelhorias() {
        return recomendacaoMelhorias;
    }

    public void setRecomendacaoMelhorias(String recomendacaoMelhorias) {
        this.recomendacaoMelhorias = recomendacaoMelhorias;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "AvaliacaoModel{" +
                "idAvaliacao=" + idAvaliacao +
                ", idAcompanhamento=" + idAcompanhamento +
                ", idEstagiario=" + idEstagiario +
                ", objetivoProfissional='" + objetivoProfissional + '\'' +
                ", recomendacaoMelhorias='" + recomendacaoMelhorias + '\'' +
                ", link='" + link + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}
