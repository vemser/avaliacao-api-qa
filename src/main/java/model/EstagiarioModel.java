package model;

public class EstagiarioModel {
    /*
    Atributos

    {
  "idTrilha": 1,
  "nome": "Tadeu Tavares Torres",
  "cpf": "57953891026",
  "emailPessoal": "tadeutorres.contato@email.com",
  "emailCorporativo": "tadeu.torres@dbccompany.com.br",
  "telefone": "31912345678",
  "dataNascimento": "2000-01-01",
  "estado": "MG",
  "cidade": "Ouro preto",
  "curso": "Sistemas de Informação",
  "instituicaoEnsino": "UFMG",
  "linkedin": "https://www.linkedin.com/in/tadeu-torres-1928121uc/",
  "github": "https://github.com/Tadeu-Torres",
  "observacoes": "Além disso...",
  "status": "DISPONIVEL",
  "idEstagiario": 0,
  "pontuacao": 0,
  "ativo": true
}
     */
    private Integer idTrilha;
    private String trilha;
    private String nome;
    private String cpf;
    private String emailPessoal;
    private String emailCorporativo;
    private String telefone;
    private String dataNascimento;
    private String estado;
    private String cidade;
    private String curso;
    private String instituicaoEnsino;
    private String linkedin;
    private String github;
    private String observacoes;
    private String status;
    private Integer idEstagiario;
    private Integer pontuacao;
    private Boolean ativo;
    private String programa;

    public EstagiarioModel() {
    }

    public EstagiarioModel(Integer idTrilha, String trilha, String nome, String cpf, String emailPessoal, String emailCorporativo, String telefone, String dataNascimento, String estado, String cidade, String curso, String instituicaoEnsino, String linkedin, String github, String observacoes, String status) {
        this.idTrilha = idTrilha;
        this.nome = nome;
        this.cpf = cpf;
        this.emailPessoal = emailPessoal;
        this.emailCorporativo = emailCorporativo;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.estado = estado;
        this.cidade = cidade;
        this.curso = curso;
        this.instituicaoEnsino = instituicaoEnsino;
        this.linkedin = linkedin;
        this.github = github;
        this.observacoes = observacoes;
        this.status = status;
    }

    public EstagiarioModel(Integer idTrilha, String trilha, String nome, String cpf, String emailPessoal, String emailCorporativo, String telefone, String dataNascimento, String estado, String cidade, String curso, String instituicaoEnsino, String linkedin, String github, String observacoes, String status, Integer idEstagiario, Integer pontuacao, Boolean ativo) {
        this.idTrilha = idTrilha;
        this.trilha = trilha;
        this.nome = nome;
        this.cpf = cpf;
        this.emailPessoal = emailPessoal;
        this.emailCorporativo = emailCorporativo;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.estado = estado;
        this.cidade = cidade;
        this.curso = curso;
        this.instituicaoEnsino = instituicaoEnsino;
        this.linkedin = linkedin;
        this.github = github;
        this.observacoes = observacoes;
        this.status = status;
        this.idEstagiario = idEstagiario;
        this.pontuacao = pontuacao;
        this.ativo = ativo;
    }

    public Integer getIdTrilha() {
        return idTrilha;
    }

    public void setIdTrilha(Integer idTrilha) {
        this.idTrilha = idTrilha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmailPessoal() {
        return emailPessoal;
    }

    public void setEmailPessoal(String emailPessoal) {
        this.emailPessoal = emailPessoal;
    }

    public String getEmailCorporativo() {
        return emailCorporativo;
    }

    public void setEmailCorporativo(String emailCorporativo) {
        this.emailCorporativo = emailCorporativo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getInstituicaoEnsino() {
        return instituicaoEnsino;
    }

    public void setInstituicaoEnsino(String instituicaoEnsino) {
        this.instituicaoEnsino = instituicaoEnsino;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIdEstagiario() {
        return idEstagiario;
    }

    public void setIdEstagiario(Integer idEstagiario) {
        this.idEstagiario = idEstagiario;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getTrilha() {
        return trilha;
    }

    public void setTrilha(String trilha) {
        this.trilha = trilha;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    @Override
    public String toString() {
        return "EstagiarioModel{" +
                "idTrilha=" + idTrilha +
                ", trilha='" + trilha + '\'' +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", emailPessoal='" + emailPessoal + '\'' +
                ", emailCorporativo='" + emailCorporativo + '\'' +
                ", telefone='" + telefone + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", estado='" + estado + '\'' +
                ", cidade='" + cidade + '\'' +
                ", curso='" + curso + '\'' +
                ", instituicaoEnsino='" + instituicaoEnsino + '\'' +
                ", linkedin='" + linkedin + '\'' +
                ", github='" + github + '\'' +
                ", observacoes='" + observacoes + '\'' +
                ", status='" + status + '\'' +
                ", idEstagiario=" + idEstagiario +
                ", pontuacao=" + pontuacao +
                ", ativo=" + ativo +
                ", programa='" + programa + '\'' +
                '}';
    }
}
