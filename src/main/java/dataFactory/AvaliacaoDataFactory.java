package dataFactory;

import model.AvaliacaoModel;
import model.ProgramaModel;
import model.TrilhaModel;
import model.AcompanhamentoModel;
import model.EstagiarioModel;
import net.datafaker.Faker;
import utils.FakerHolder;

import java.text.SimpleDateFormat;


public class AvaliacaoDataFactory {
    //    region CRIAR AVALIACAO
    public static AvaliacaoModel gerarAvaliacaoValida(Integer idAcompanhamento, Integer idEstagiario) {
        AvaliacaoModel avaliaco = new AvaliacaoModel();

        avaliaco.setIdAcompanhamento(idAcompanhamento);
        avaliaco.setIdEstagiario(idEstagiario);
        avaliaco.setObjetivoProfissional(FakerHolder.instance.name().nameWithMiddle());
        avaliaco.setRecomendacaoMelhorias(FakerHolder.instance.name().nameWithMiddle());
        avaliaco.setStatus("ABERTO");

        return avaliaco;
    }

    public static AvaliacaoModel gerarAvaliacaoValida(AcompanhamentoModel acompanhamento, EstagiarioModel estagiario) {
        return gerarAvaliacaoValida(acompanhamento.getIdAcompanhamento(), estagiario.getIdEstagiario());
    }

    //    endregion
//region ALTERAR AVALIACAO
    public static AvaliacaoModel gerarAvaliacaoComIdInvalido(Integer idAcompanhamento, Integer idEstagiario) {
        return getAvaliacaoModel(idAcompanhamento, idEstagiario);
    }

    public static AvaliacaoModel gerarAvaliacaoValidaComIdInvalido(AcompanhamentoModel acompanhamento, EstagiarioModel estagiario) {
        return gerarAvaliacaoComIdInvalido(acompanhamento.getIdAcompanhamento(), estagiario.getIdEstagiario());
    }

    public static AvaliacaoModel alterarAvaliacao(Integer idAcompanhamento, Integer idEstagiario) {
        return getAvaliacaoModel(idAcompanhamento, idEstagiario);
    }

    public static AvaliacaoModel alterarAvaliacao(AcompanhamentoModel acompanhamento, EstagiarioModel estagiario) {
        return alterarAvaliacao(acompanhamento.getIdAcompanhamento(), estagiario.getIdEstagiario());
    }

    //    endregion
//region BUSCAR AVALIACAO POR ID DO ACOMPANHAMENTO
    public static AvaliacaoModel buscarAvaliacaoPorIdDoAcompanhamento(Integer idAcompanhamento) {
        AvaliacaoModel avaliacao = new AvaliacaoModel();

        avaliacao.setIdAcompanhamento(idAcompanhamento);
        return avaliacao;
    }

    public static AvaliacaoModel buscarAvaliacaoComIdInvalido() {
        AvaliacaoModel avaliacao = new AvaliacaoModel();

        avaliacao.setIdAcompanhamento(0);
        return avaliacao;
    }

    //endregion
//region  BUSCAR AVALIACAO POR ID DA AVALIACAO
    public static AvaliacaoModel buscarAvaliacaoPorIdDaAvaliacao(Integer idAvaliacao) {
        AvaliacaoModel avaliacao = new AvaliacaoModel();

        avaliacao.setIdAvaliacao(idAvaliacao);
        return avaliacao;
    }

    public static AvaliacaoModel buscarAvaliacaoComIdInvalidodaAvaliacao() {
        AvaliacaoModel avaliacao = new AvaliacaoModel();

        avaliacao.setIdAvaliacao(0);
        return avaliacao;
    }

    //endregion
//region DESATIVAR AVALIACAO
    public static AvaliacaoModel desativarAvaliacaoPorIdDaAvaliacao(Integer idAvaliacao) {
        AvaliacaoModel avaliacao = new AvaliacaoModel();

        avaliacao.setIdAvaliacao(idAvaliacao);
        return avaliacao;
    }

    public static AvaliacaoModel desativarAvaliacaoComIdInvalidodaAvaliacao() {
        AvaliacaoModel avaliacao = new AvaliacaoModel();

        avaliacao.setIdAvaliacao(0);
        return avaliacao;
    }

    //endregion
// region DELETAR AVALIACAO
    public static AvaliacaoModel deletarAvaliacaoPorIdDaAvaliacao(Integer idAvaliacao) {
        AvaliacaoModel avaliacao = new AvaliacaoModel();

        avaliacao.setIdAvaliacao(idAvaliacao);
        return avaliacao;
    }

    public static AvaliacaoModel deletarAvaliacaoComIdInvalidodaAvaliacao() {
        AvaliacaoModel avaliacao = new AvaliacaoModel();

        avaliacao.setIdAvaliacao(0);
        return avaliacao;
    }
//endregion

    private static AvaliacaoModel getAvaliacaoModel(Integer idAcompanhamento, Integer idEstagiario) {
        AvaliacaoModel avaliaco = new AvaliacaoModel();

        avaliaco.setIdAcompanhamento(idAcompanhamento);
        avaliaco.setIdEstagiario(idEstagiario);
        avaliaco.setObjetivoProfissional(FakerHolder.instance.name().nameWithMiddle());
        avaliaco.setRecomendacaoMelhorias(FakerHolder.instance.name().nameWithMiddle());
        avaliaco.setStatus("ABERTO");
        return avaliaco;
    }
}
