package dataFactory;

import model.AvaliacaoModel;
import model.ProgramaModel;
import model.TrilhaModel;
import model.AcompanhamentoModel;
import model.EstagiarioModel;

import static dataFactory.DataFactory.faker;

public class AvaliacaoDataFactory {
    public static AvaliacaoModel gerarAvaliacaoValida(Integer idAcompanhamento, Integer idEstagiario) {
        AvaliacaoModel avaliaco = new AvaliacaoModel();
        avaliaco.setIdAcompanhamento(idAcompanhamento);
        avaliaco.setIdEstagiario(idEstagiario);
        avaliaco.setObjetivoProfissional(faker.name().nameWithMiddle());
        avaliaco.setRecomendacaoMelhorias(faker.name().nameWithMiddle());
        avaliaco.setLink(faker.internet().url());
        avaliaco.setStatus("ABERTO");
        return avaliaco;
    }
    public static AvaliacaoModel gerarAvaliacaoValida(AcompanhamentoModel acompanhamento,EstagiarioModel estagiario) {
        return gerarAvaliacaoValida(acompanhamento.getIdAcompanhamento(), estagiario.getIdEstagiario());
    }
}
