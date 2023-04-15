package dataFactory;

import model.AvaliacaoModel;
import model.FeedbackModel;
import static dataFactory.DataFactory.faker;

public class FeedbackDataFactory {
//region GERAR FEEDBACK
    public static FeedbackModel gerarFeedbackValido(Integer idAvaliacao){
        FeedbackModel feedback = new FeedbackModel();
        feedback.setIdAvaliacao(idAvaliacao);
        feedback.setNota(faker.number().numberBetween(40, 100));
        feedback.setDescricao("Descrição da " + faker.name().title());
        feedback.setTipoFeedback("POSITIVO");
        feedback.setCargoResponsavel("INSTRUTOR");
        feedback.setStatus("FINALIZADO");

        return feedback;
    }
    public static FeedbackModel gerarFeedbackValido(AvaliacaoModel avaliacao) {
        return gerarFeedbackValido(avaliacao.getIdAvaliacao());
    }
    public static FeedbackModel gerarFeedbackSemId(Integer idAvaliacao){
        FeedbackModel feedback = new FeedbackModel();
        feedback.setIdAvaliacao(0000000000000000000);
        feedback.setNota(faker.number().numberBetween(40, 100));
        feedback.setDescricao("Descrição da " + faker.name().title());
        feedback.setTipoFeedback("POSITIVO");
        feedback.setCargoResponsavel("INSTRUTOR");
        feedback.setStatus("FINALIZADO");

        return feedback;
    }
    public static FeedbackModel gerarFeedbackSemId(AvaliacaoModel avaliacao) {
        return gerarFeedbackSemId(00000000000000);
    }
//endregion
//region ATUALIZAR FEEDBACK
    public static FeedbackModel atualizarFeedback(Integer idAvaliacao){
        FeedbackModel feedback = new FeedbackModel();
        feedback.setIdAvaliacao(idAvaliacao);
        feedback.setNota(faker.number().numberBetween(40, 100));
        feedback.setDescricao("Descrição da " + faker.name().title());
        feedback.setTipoFeedback("POSITIVO");
        feedback.setCargoResponsavel("INSTRUTOR");
        feedback.setStatus("FINALIZADO");
        feedback.setLoginResponsavel("pietro.bordin");
        feedback.setAtivo(true);

        return feedback;
    }
        public static FeedbackModel atualizarFeedback(FeedbackModel feedback) {
            return atualizarFeedback(feedback.getIdFeedBack());
    }
    public static FeedbackModel atualizarFeedbackComidErrado(Integer avaliar) {
            return atualizarFeedback(000000000000);
    }
//endregion
//region BUSCAR FEEDBACK
    public static FeedbackModel buscarFeedback(Integer idFeedback) {
        FeedbackModel feedback = new FeedbackModel();
        feedback.setIdFeedBack(idFeedback);
        return feedback;
    }
//endregion
//region BUSCAR FEEDBACK POR ID DE AVALIACAO
    public static FeedbackModel buscarFeedbackPorIdDeAvaliacao(Integer idPrograma) {
        FeedbackModel feedback = new FeedbackModel();
        feedback.setIdPrograma(idPrograma);
        return feedback;
    }
//endregion
//region DELETAR/DESATIVAR FEEDBACK
    public static FeedbackModel deletarFeedbackPorId(Integer idFeedBack) {
        FeedbackModel feedBack = new FeedbackModel();
        feedBack.setIdFeedBack(idFeedBack);
        return feedBack;
    }
    public static FeedbackModel deletarFeedbackComIdErrado(Integer idFeedBack) {
        FeedbackModel feedBack = new FeedbackModel();
        feedBack.setIdFeedBack(000000000000);
        return feedBack;
    }
//endregion
}
