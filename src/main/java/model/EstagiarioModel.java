package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EstagiarioModel {
    public Integer idTrilha;
    public String nome;
    public String cpf;
    public String emailPessoal;
    public String emailCorporativo;
    public String telefone;
    public String dataNascimento;
    public String estado;
    public String cidade;
    public String curso;
    public String instituicaoEnsino;
    public String linkedin;
    public String github;
    public String observacoes;
    public String status;
    public String trilha;
    public Integer idEstagiario;
    public Integer pontuacao;
    public boolean ativo;
    public String programa;
    public Integer idPrograma;
}
