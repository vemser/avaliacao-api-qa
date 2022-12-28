package br.com.dbccompany.model;

import lombok.Data;

@Data
public class Usuario {

    private String foto;
    private String nome;
    private String login;
    private String email;
    private String senha;
    private Integer statusUsuario;
    private Integer tipoPerfil;
    private String cidade;
    private String especialidade;

    public Usuario(String foto, String nome, String login, String email, String senha, Integer statusUsuario, Integer tipoPerfil, String cidade, String especialidade) {
        this.foto = foto;
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.senha = senha;
        this.statusUsuario = statusUsuario;
        this.tipoPerfil = tipoPerfil;
        this.cidade = cidade;
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "foto='" + foto + '\'' +
                ", nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", statusUsuario=" + statusUsuario +
                ", tipoPerfil=" + tipoPerfil +
                ", cidade='" + cidade + '\'' +
                ", especialidade='" + especialidade + '\'' +
                '}';
    }
}
