package br.com.dbccompany.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties
public class TodosDTO {

    private Integer idUsuario;
    private String nome;
    private String email;
    private Integer statusUsuario;
    private Integer tipoPerfil;
    private String instrucoes;
    private Integer pesoAtividade;
    private String dataCriacao;
    private String dataEntrega;
    private String link;
    private String nomeInstrutor;
    private Integer idAtividade;
    private String titulo;
    private Integer nota;
    private Integer edicao;
    private Date anoEdicao;
    private Integer idTrilha;
    private List<UsuarioDTO> usuarios;
    private List<TrilhaDTO> trilhas;
    private String login;
    private String cidade;
    private String especialidade;
    private Integer idModulo;
    private String statusAtividade;
    private String nomeModulo;
    private String trilhaNome;
    private String foto;
    private String nomeTrilha;
}
