package br.com.dbccompany.model;

public class LoginBuilder {

    private String email;
    private String senha;

    public LoginBuilder email(String email) {
        this.email = email;
        return this;
    }

    public LoginBuilder senha(String senha) {
        this.senha = senha;
        return this;
    }

    public Login build() {
        return new Login(email, senha);
    }
}
