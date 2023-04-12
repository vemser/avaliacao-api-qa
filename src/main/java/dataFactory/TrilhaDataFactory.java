package dataFactory;

import model.ProgramaModel;
import model.TrilhaModel;
import service.TrilhaService;

public class TrilhaDataFactory extends DataFactory{
    private static TrilhaService trilhaService = new TrilhaService();

//region    CENÁRIOS DE CRIAÇÃO DE TRILHA

    public static TrilhaModel gerarTrilhaValida(Integer idPrograma) {
        TrilhaModel trilha = new TrilhaModel();
        trilha.setIdPrograma(idPrograma);
        trilha.setNome("Trilha " + faker.name().firstName());
        trilha.setDescricao("Descrição da " + trilha.getNome());
        trilha.setStatus("FECHADO");
        return trilha;
    }
    public static TrilhaModel gerarTrilhaValida(ProgramaModel programa) {
        return gerarTrilhaValida(programa.getIdPrograma());
    }

    public static TrilhaModel gerarTrilhaSemId() {
        TrilhaModel trilha = new TrilhaModel();
        trilha.setNome("Trilha " + faker.name().firstName());
        trilha.setDescricao("Descrição da " + trilha.getNome());
        trilha.setStatus("FECHADO");
        return trilha;
    }
    public static TrilhaModel gerarTrilhaComStatusInexistente(Integer idPrograma) {
        TrilhaModel trilha = new TrilhaModel();
        trilha.setIdTrilha(idPrograma);
        trilha.setNome("Trilha " + faker.name().firstName());
        trilha.setDescricao("Descrição da " + trilha.getNome());
        trilha.setStatus("Não sei");
        return trilha;
    }
//endregion
//region    CENÁRIOS DE ATUALIZAÇÃO DE TRILHA
    public static TrilhaModel atualizarTrilhaValida(Integer idPrograma) {
        TrilhaModel trilha = new TrilhaModel();
        trilha.setIdPrograma(idPrograma);
        trilha.setNome("Trilha " + faker.name().firstName());
        trilha.setDescricao("Descrição da " + trilha.getNome());
        trilha.setStatus("ABERTO");
        return trilha;
    }
    public static TrilhaModel atualizarTrilhaSemId() {
        TrilhaModel trilha = new TrilhaModel();
        trilha.setNome("Trilha " + faker.name().firstName());
        trilha.setDescricao("Descrição da " + trilha.getNome());
        trilha.setStatus("ABERTO");
        return trilha;
    }
    public static TrilhaModel atualizarTrilhSemCorpo() {
        TrilhaModel trilha = new TrilhaModel();
        return trilha;
    }
//endregion
//region    CENÁRIO DE BUSCA POR ID
    public static TrilhaModel buscarTrilhaComIdInexistente(){
        TrilhaModel trilha = new TrilhaModel();
        trilha.setIdTrilha(12321564);
        return trilha;
    }
//endregion
// region CENÁRIOS PARA  DESATIVAR TRILHA POR ID DA TRILHA
    public static TrilhaModel desativarTrilhaComIdInexistente(){
        TrilhaModel trilha = new TrilhaModel();
        trilha.setIdTrilha(12321564);
        return trilha;
    }
//endregion
//region   CENÁRIOS PARA DELETAR TRILHA POR ID DA TRILHA
    public static TrilhaModel deletarTrilhaComIdInexistente(){
        TrilhaModel trilha = new TrilhaModel();
        trilha.setIdTrilha(12321564);
        return trilha;
    }
//endregion
//region CENÁRIOS PARA BUSCAR TODAS AS TRILHAS
    public static TrilhaModel buscarProgramasPorId(Integer idPrograma) {
        TrilhaModel trilha = new TrilhaModel();
        trilha.setIdPrograma(idPrograma);
        return trilha;
    }
    public static TrilhaModel buscarProgramasSemId() {
        TrilhaModel trilha = new TrilhaModel();
        trilha.setIdPrograma(-123);
        return trilha;
    }
//endregion
}
