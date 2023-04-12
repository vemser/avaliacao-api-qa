package dataFactory;

import model.EstagiarioModel;
import model.TrilhaModel;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Map;
import java.util.stream.Stream;

public class EstagiarioDataFactory extends DataFactory {
    private static String gerarUsername(String nomeCompleto) {
        return nomeCompleto.toLowerCase().replace(" ", ".").replace("..", ".");
    }
    private static String gerarCpfValido() {
        return faker.cpf().valid().replace(".", "").replace("-", "");
    }
    public static EstagiarioModel gerarEstagiarioValido(Integer idTrilha) {
        EstagiarioModel estagiario = new EstagiarioModel();
        estagiario.setIdTrilha(idTrilha);
        estagiario.setNome(faker.name().nameWithMiddle());
        estagiario.setCpf(gerarCpfValido());
        estagiario.setEmailPessoal(faker.internet().emailAddress("teste"));
        estagiario.setEmailCorporativo(faker.internet().emailAddress("teste"));
        estagiario.setTelefone(faker.numerify("###########"));
        estagiario.setDataNascimento(dateFormat.format(faker.date().birthday(18, 100)));
        estagiario.setEstado(faker.address().countryCode());
        estagiario.setCidade(faker.address().city());
        estagiario.setCurso(faker.educator().course());
        estagiario.setInstituicaoEnsino(faker.educator().university());
        estagiario.setLinkedin("https://teste.linkedin.com/in/" + gerarUsername(estagiario.getNome()) + "/");
        estagiario.setGithub("https://teste.github.com/" + gerarUsername(estagiario.getNome()) + "/");
        estagiario.setObservacoes(faker.lorem().sentence());
        estagiario.setStatus("DISPONIVEL");
        return estagiario;
    }
    public static EstagiarioModel gerarEstagiarioValido(TrilhaModel trilha) {
        return gerarEstagiarioValido(trilha.getIdTrilha());
    }
    public static EstagiarioModel gerarEstagiarioAlterado(EstagiarioModel estagiarioAntigo) {
        EstagiarioModel estagiario = gerarEstagiarioValido(estagiarioAntigo.getIdTrilha());
        return estagiario;
    }
    public static EstagiarioModel copiarEstagiario(EstagiarioModel estagiario) {
        EstagiarioModel estagiarioCopia = new EstagiarioModel();
        estagiarioCopia.setIdTrilha(estagiario.getIdTrilha());
        estagiarioCopia.setNome(estagiario.getNome());
        estagiarioCopia.setCpf(estagiario.getCpf());
        estagiarioCopia.setEmailPessoal(estagiario.getEmailPessoal());
        estagiarioCopia.setEmailCorporativo(estagiario.getEmailCorporativo());
        estagiarioCopia.setTelefone(estagiario.getTelefone());
        estagiarioCopia.setDataNascimento(estagiario.getDataNascimento());
        estagiarioCopia.setEstado(estagiario.getEstado());
        estagiarioCopia.setCidade(estagiario.getCidade());
        estagiarioCopia.setCurso(estagiario.getCurso());
        estagiarioCopia.setInstituicaoEnsino(estagiario.getInstituicaoEnsino());
        estagiarioCopia.setLinkedin(estagiario.getLinkedin());
        estagiarioCopia.setGithub(estagiario.getGithub());
        estagiarioCopia.setObservacoes(estagiario.getObservacoes());
        estagiarioCopia.setStatus(estagiario.getStatus());
        estagiarioCopia.setIdEstagiario(estagiario.getIdEstagiario());
        estagiarioCopia.setAtivo(estagiario.getAtivo());
        return estagiarioCopia;
    }
    public static String gerarCpfComPontoEHifen() {
        return faker.cpf().valid();
    }
    public static Stream<Arguments> provideEmailsInvalidos() {
        return Stream.of(
                Arguments.of("a`" + faker.internet().emailAddress()),
//                Arguments.of("a~" + faker.internet().emailAddress()),
//                Arguments.of("a!" + faker.internet().emailAddress()),
//                Arguments.of("a´" + faker.internet().emailAddress()),
//                Arguments.of("a#" + faker.internet().emailAddress()),
//                Arguments.of("a$" + faker.internet().emailAddress()),
//                Arguments.of("a%" + faker.internet().emailAddress()),
//                Arguments.of("aá" + faker.internet().emailAddress()),
//                Arguments.of("aâ" + faker.internet().emailAddress()),
//                Arguments.of("aã" + faker.internet().emailAddress()),
//                Arguments.of("aé" + faker.internet().emailAddress()),
//                Arguments.of("a@" + faker.internet().emailAddress()),
//                Arguments.of("a.." + faker.internet().emailAddress()),
                Arguments.of(faker.internet().url()),
                Arguments.of(faker.internet().ipV4Address()),
                Arguments.of(faker.internet().ipV6Address()),
                Arguments.of(faker.internet().domainName()),
                Arguments.of("")
        );
    }
    public static Stream<Arguments> provideCpfInvalidos() {
        return Stream.of(
                Arguments.of(faker.numerify("a##########")),
                Arguments.of(faker.numerify("##########!")),
                Arguments.of(faker.numerify("##########à")),
                Arguments.of(faker.numerify("##########á")),
                Arguments.of(faker.numerify("##########â")),
                Arguments.of(faker.numerify("#######.####")),
                Arguments.of(faker.numerify("#######-####")),
                Arguments.of(faker.numerify("###### #####")),
                Arguments.of(faker.numerify("#          #")),
                Arguments.of(gerarCpfInvalidoComEspacoVazio())
        );
    }

    private static String gerarCpfInvalidoComEspacoVazio() {
        String cpfInvalido = gerarCpfValido();
        cpfInvalido = cpfInvalido.replace(cpfInvalido.charAt(2), ' ');
        return cpfInvalido;
    }
}
