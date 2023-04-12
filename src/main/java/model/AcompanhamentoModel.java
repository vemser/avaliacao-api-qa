package model;

public class AcompanhamentoModel {
    private Integer idPrograma;
    private String titulo;
    private String dataInicio;
    private String dataFim;
    private String horarioInicio;
    private String horarioFim;
    private Integer duracao;
    private String descricao;
    private Integer numeroResponsaveis;
    private String status;



    public Integer getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Integer idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horaInicio) {
        this.horarioInicio = horaInicio;
    }

    public String getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(String horaFim) {
        this.horarioFim = horaFim;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
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

    public Integer getNumeroResponsaveis() {
        return numeroResponsaveis;
    }

    public void setNumeroResponsaveis(Integer numeroResponsaveis) {
        this.numeroResponsaveis = numeroResponsaveis;
    }
}
