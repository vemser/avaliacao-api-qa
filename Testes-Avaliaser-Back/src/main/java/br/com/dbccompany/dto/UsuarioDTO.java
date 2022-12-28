package br.com.dbccompany.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties
public class UsuarioDTO {

    private String foto;
    private String nome;
    private String login;
    private String email;
    private String senha;
    private Integer statusUsuario;
    private Integer tipoPerfil;
    private String cidade;
    private String especialidade;
    private Integer idUsuario;
}
