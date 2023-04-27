package dataFactory;

import model.EstagiarioModel;
import net.datafaker.Faker;

import java.text.SimpleDateFormat;


public class EstagiarioDataFactory{
    private static Faker faker = new Faker();
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static EstagiarioModel gerarEstagiarioValido() {
        EstagiarioModel estagiario = new EstagiarioModel();
        estagiario.setIdTrilha(1150);
        estagiario.setNome(faker.name().title());
        estagiario.setCpf(faker.cpf().valid().replace(".", "").replace("-", ""));
        estagiario.setEmailPessoal("teste." + faker.internet().emailAddress());
        estagiario.setEmailCorporativo(gerarEmailDbc(estagiario.getEmailPessoal()));
        estagiario.setTelefone(faker.numerify("###########"));
        estagiario.setDataNascimento(dateFormat.format(faker.date().birthday(18, 100)));
        estagiario.setEstado(faker.address().countryCode());
        estagiario.setCidade(faker.address().city());
        estagiario.setCurso(faker.educator().course());
        estagiario.setInstituicaoEnsino(faker.educator().university());
        estagiario.setLinkedin("https://teste.linkedin.com/in/" + estagiario.getNome() + "/");
        estagiario.setGithub("https://teste.github.com/" + estagiario.getNome() + "/");
        estagiario.setObservacoes(faker.lorem().sentence());
        estagiario.setStatus("DISPONIVEL");
        return estagiario;
    }
    private static String gerarEmailDbc(String emailPessoal) {
        return emailPessoal.replace(emailPessoal.substring(emailPessoal.indexOf("@"), emailPessoal.length()), "@dbccompany.com.br");
    }
    public static String gerarEmailInvalido() {
        return faker.name().firstName();
    }
    public static String gerarCpfComPontoEHifen() {
        return faker.cpf().valid();
    }

}
