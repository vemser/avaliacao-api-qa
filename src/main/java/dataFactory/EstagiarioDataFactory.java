package dataFactory;

import model.EstagiarioModel;
import net.datafaker.Faker;
import utils.FakerHolder;

import java.text.SimpleDateFormat;


public class EstagiarioDataFactory{
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static EstagiarioModel gerarEstagiarioValido() {
        EstagiarioModel estagiario = new EstagiarioModel();

        estagiario.setIdTrilha(1150);
        estagiario.setNome(FakerHolder.instance.name().title());
        estagiario.setCpf(FakerHolder.instance.cpf().valid().replace(".", "").replace("-", ""));
        estagiario.setEmailPessoal("teste." + FakerHolder.instance.internet().emailAddress());
        estagiario.setEmailCorporativo(gerarEmailDbc(estagiario.getEmailPessoal()));
        estagiario.setTelefone(FakerHolder.instance.numerify("###########"));
        estagiario.setDataNascimento(dateFormat.format(FakerHolder.instance.date().birthday(18, 100)));
        estagiario.setEstado(FakerHolder.instance.address().countryCode());
        estagiario.setCidade(FakerHolder.instance.address().city());
        estagiario.setCurso(FakerHolder.instance.educator().course());
        estagiario.setInstituicaoEnsino(FakerHolder.instance.educator().university());
        estagiario.setLinkedin("https://teste.linkedin.com/in/" + estagiario.getNome() + "/");
        estagiario.setGithub("https://teste.github.com/" + estagiario.getNome() + "/");
        estagiario.setObservacoes(FakerHolder.instance.lorem().sentence());
        estagiario.setStatus("DISPONIVEL");

        return estagiario;
    }

    private static String gerarEmailDbc(String emailPessoal) {
        return emailPessoal.replace(emailPessoal.substring(emailPessoal.indexOf("@")), "@dbccompany.com.br");
    }

    public static String gerarEmailInvalido() {
        return FakerHolder.instance.name().firstName();
    }

    public static String gerarCpfComPontoEHifen() {
        return FakerHolder.instance.cpf().valid();
    }
}
