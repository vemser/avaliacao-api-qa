package dataFactory;

import model.EstagiarioModel;

public class EstagiarioDataFactory extends DataFactory {
    private static String gerarUsername(String nomeCompleto) {
        return nomeCompleto.toLowerCase().replace(" ", ".");
    }
    public static EstagiarioModel gerarEstagiarioValido() {
        EstagiarioModel estagiario = new EstagiarioModel();
        estagiario.setIdTrilha(faker.number().numberBetween(1, 3));
        estagiario.setNome(faker.name().nameWithMiddle());
        estagiario.setCpf(faker.cpf().valid());
        estagiario.setEmailPessoal(gerarUsername(estagiario.getNome()) + "@teste.com");
        estagiario.setEmailCorporativo(gerarUsername(estagiario.getNome()) + ".teste@dbccompany.com.br");
        estagiario.setTelefone(faker.phoneNumber().cellPhone());
        estagiario.setDataNascimento(faker.date().birthday(18, 30).toString());
        estagiario.setEstado(faker.address().state());
        estagiario.setCidade(faker.address().city());
        estagiario.setCurso(faker.educator().course());
        estagiario.setInstituicaoEnsino(faker.educator().university());
        estagiario.setLinkedin(faker.internet().url());
        estagiario.setGithub(faker.internet().url());
        estagiario.setObservacoes(faker.lorem().sentence());
        estagiario.setStatus("DISPONIVEL");
        return estagiario;
    }
}
