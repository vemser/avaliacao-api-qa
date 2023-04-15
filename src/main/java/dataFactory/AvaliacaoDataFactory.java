package dataFactory;

import model.AvaliacaoModel;
import model.ProgramaModel;
import model.TrilhaModel;
import model.AcompanhamentoModel;
import model.EstagiarioModel;

import static dataFactory.DataFactory.faker;

public class AvaliacaoDataFactory {
//    region CRIAR AVALIACAO
    public static AvaliacaoModel gerarAvaliacaoValida(Integer idAcompanhamento, Integer idEstagiario) {
        AvaliacaoModel avaliaco = new AvaliacaoModel();
        avaliaco.setIdAcompanhamento(idAcompanhamento);
        avaliaco.setIdEstagiario(idEstagiario);
        avaliaco.setObjetivoProfissional(faker.name().nameWithMiddle());
        avaliaco.setRecomendacaoMelhorias(faker.name().nameWithMiddle());
        avaliaco.setLink(faker.internet().url().replace(" ",""));
        avaliaco.setStatus("ABERTO");
        return avaliaco;
    }
    public static AvaliacaoModel gerarAvaliacaoValida(AcompanhamentoModel acompanhamento,EstagiarioModel estagiario) {
        return gerarAvaliacaoValida(acompanhamento.getIdAcompanhamento(), estagiario.getIdEstagiario());
    }
//    endregion
//region ALTERAR AVALIACAO
    public static AvaliacaoModel gerarAvaliacaoComIdInvalido(Integer idAcompanhamento, Integer idEstagiario) {
        AvaliacaoModel avaliaco = new AvaliacaoModel();
        avaliaco.setIdAcompanhamento(idAcompanhamento);
        avaliaco.setIdEstagiario(idEstagiario);
        avaliaco.setObjetivoProfissional(faker.name().nameWithMiddle());
        avaliaco.setRecomendacaoMelhorias(faker.name().nameWithMiddle());
        avaliaco.setLink(faker.internet().url().replace(" ",""));
        avaliaco.setStatus("ABERTO");
        return avaliaco;
    }
    public static AvaliacaoModel gerarAvaliacaoValidaComIdInvalido(AcompanhamentoModel acompanhamento,EstagiarioModel estagiario) {
        return gerarAvaliacaoComIdInvalido(acompanhamento.getIdAcompanhamento(), estagiario.getIdEstagiario());
    }

    public static AvaliacaoModel alterarAvaliacao(Integer idAcompanhamento, Integer idEstagiario) {
        AvaliacaoModel avaliaco = new AvaliacaoModel();
        avaliaco.setIdAcompanhamento(idAcompanhamento);
        avaliaco.setIdEstagiario(idEstagiario);
        avaliaco.setObjetivoProfissional(faker.name().nameWithMiddle());
        avaliaco.setRecomendacaoMelhorias(faker.name().nameWithMiddle());
        avaliaco.setLink(faker.internet().url().replace(" ",""));
        avaliaco.setStatus("ABERTO");
        return avaliaco;
    }
    public static AvaliacaoModel alterarAvaliacao(AcompanhamentoModel acompanhamento,EstagiarioModel estagiario) {
        return alterarAvaliacao(acompanhamento.getIdAcompanhamento(), estagiario.getIdEstagiario());
    }
//    endregion
//region BUSCAR AVALIACAO POR ID DO ACOMPANHAMENTO
    public static AvaliacaoModel buscarAvaliacaoPorIdDoAcompanhamento(Integer idAcompanhamento) {
        AvaliacaoModel avaliacao = new AvaliacaoModel();
        avaliacao.setIdAcompanhamento(idAcompanhamento);
        return avaliacao;
    }
    public static AvaliacaoModel buscarAvaliacaoComIdInvalido(Integer idAcompanhamento) {
        AvaliacaoModel avaliacao = new AvaliacaoModel();
        avaliacao.setIdAcompanhamento(00000000000000);
        return avaliacao;
    }
//endregion
//region  BUSCAR AVALIACAO POR ID DA AVALIACAO
    public static AvaliacaoModel buscarAvaliacaoPorIdDaAvaliacao(Integer idAvaliacao) {
        AvaliacaoModel avaliacao = new AvaliacaoModel();
        avaliacao.setIdAvaliacao(idAvaliacao);
        return avaliacao;
    }
    public static AvaliacaoModel buscarAvaliacaoComIdInvalidodaAvaliacao(Integer idAcompanhamento) {
        AvaliacaoModel avaliacao = new AvaliacaoModel();
        avaliacao.setIdAvaliacao(00000000000000);
        return avaliacao;
    }
//endregion
//region DESATIVAR AVALIACAO
    public static AvaliacaoModel desativarAvaliacaoPorIdDaAvaliacao(Integer idAvaliacao) {
        AvaliacaoModel avaliacao = new AvaliacaoModel();
        avaliacao.setIdAvaliacao(idAvaliacao);
        return avaliacao;
    }
        public static AvaliacaoModel desativarAvaliacaoComIdInvalidodaAvaliacao(Integer idAcompanhamento) {
            AvaliacaoModel avaliacao = new AvaliacaoModel();
            avaliacao.setIdAvaliacao(00000000000000);
            return avaliacao;
    }
//endregion
// region DELETAR AVALIACAO
    public static AvaliacaoModel deletarAvaliacaoPorIdDaAvaliacao(Integer idAvaliacao) {
        AvaliacaoModel avaliacao = new AvaliacaoModel();
        avaliacao.setIdAvaliacao(idAvaliacao);
        return avaliacao;
    }
        public static AvaliacaoModel deletarAvaliacaoComIdInvalidodaAvaliacao(Integer idAcompanhamento) {
            AvaliacaoModel avaliacao = new AvaliacaoModel();
            avaliacao.setIdAvaliacao(00000000000000);
            return avaliacao;
    }
//endregion

}
