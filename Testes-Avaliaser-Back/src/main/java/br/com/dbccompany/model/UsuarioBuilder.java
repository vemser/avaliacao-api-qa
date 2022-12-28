package br.com.dbccompany.model;

public class UsuarioBuilder {

    private String foto;
    private String nome;
    private String login;
    private String email;
    private String senha;
    private Integer statusUsuario;
    private Integer tipoPerfil;
    private String cidade;
    private String especialidade;

    public UsuarioBuilder foto(String foto) {
        this.foto = foto;
        return this;
    }

    public UsuarioBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public UsuarioBuilder login(String login) {
        this.login = login;
        return this;
    }

    public UsuarioBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UsuarioBuilder senha(String senha) {
        this.senha = senha;
        return this;
    }

    public UsuarioBuilder statusUsuario(Integer statusUsuario) {
        this.statusUsuario = statusUsuario;
        return this;
    }

    public UsuarioBuilder tipoPerfil(Integer tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
        return this;
    }

    public UsuarioBuilder cidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public UsuarioBuilder especialidade(String especialidade) {
        this.especialidade = especialidade;
        return this;
    }

    public Usuario build() {
        return new Usuario(foto, nome, login, email, senha, statusUsuario, tipoPerfil, cidade, especialidade);
    }
}
