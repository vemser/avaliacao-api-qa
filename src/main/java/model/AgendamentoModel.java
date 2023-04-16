package model;

public class AgendamentoModel {
    private Integer idAvaliacao;
    private String dataHorario;
    private String responsavel;
    private Integer idAgendamento;
    private String ativo;
    private Integer idAcompanhamento;

    public AgendamentoModel(Integer iAvaliacao, String dataHorario, String responsavel) {
        this.idAvaliacao = iAvaliacao;
        this.dataHorario = dataHorario;
        this.responsavel = responsavel;
    }

    public AgendamentoModel() {
    }

    public Integer getidAvaliacao() {
        return idAvaliacao;
    }

    public void setidAvaliacao(Integer iAvaliacao) {
        this.idAvaliacao = iAvaliacao;
    }

    public String getDataHorario() {
        return dataHorario;
    }

    public void setDataHorario(String dataHorario) {
        this.dataHorario = dataHorario;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public Integer getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(Integer idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public Integer getIdAcompanhamento() {
        return idAcompanhamento;
    }

    public void setIdAcompanhamento(Integer idAcompanhamento) {
        this.idAcompanhamento = idAcompanhamento;
    }
}
