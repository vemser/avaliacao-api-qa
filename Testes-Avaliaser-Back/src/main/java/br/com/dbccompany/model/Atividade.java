package br.com.dbccompany.model;

import br.com.dbccompany.dto.TrilhaDTO;

import lombok.Data;

import java.util.List;

@Data
public class Atividade {

    private String titulo;
    private String instrucoes;
    private Integer pesoAtividade;
    private String dataCriacao;
    private String dataEntrega;
    private Integer idModulo;
    private Integer pontuacao;
    private String link;
    private List<TrilhaDTO> trilha;

    public Atividade(String titulo, String instrucoes, Integer pesoAtividade, String dataCriacao, String dataEntrega, Integer idModulo, List<TrilhaDTO> trilha) {
        this.titulo = titulo;
        this.instrucoes = instrucoes;
        this.pesoAtividade = pesoAtividade;
        this.dataCriacao = dataCriacao;
        this.dataEntrega = dataEntrega;
        this.idModulo = idModulo;
        this.trilha = trilha;
    }

    public Atividade(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Atividade(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Atividade{" +
                "titulo='" + titulo + '\'' +
                ", instrucoes='" + instrucoes + '\'' +
                ", pesoAtivdade=" + pesoAtividade +
                ", dataCriacao=" + dataCriacao +
                ", dataEntrega=" + dataEntrega +
                ", idModulo=" + idModulo +
                ", trilha=" + trilha +
                '}';
    }
}
