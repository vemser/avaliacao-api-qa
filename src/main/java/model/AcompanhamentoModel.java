package model;

import io.restassured.http.ContentType;

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
    private Integer idAcompanhamento;
    private Boolean ativo;
    private String nome;


    public AcompanhamentoModel(Integer idPrograma, String titulo, String dataInicio, String dataFim, String horarioInicio, String horarioFim, Integer duracao, String descricao, Integer numeroResponsaveis, String status) {
        this.idPrograma = idPrograma;
        this.titulo = titulo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.duracao = duracao;
        this.descricao = descricao;
        this.numeroResponsaveis = numeroResponsaveis;
        this.status = status;
    }
    public AcompanhamentoModel(){
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getIdAcompanhamento() {
        return idAcompanhamento;
    }

    public void setIdAcompanhamento(Integer idAcompanhamento) {
        this.idAcompanhamento = idAcompanhamento;
    }

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
