package dataFactory;

import model.ModuloModel;
import model.TrilhaModel;
import utils.FakerHolder;

import java.util.ArrayList;

public class TrilhaDataFactory {

//region    CENÁRIOS DE CRIAÇÃO DE TRILHA

    public static TrilhaModel gerarTrilhaValida() {
        TrilhaModel trilha = new TrilhaModel();

        trilha.setIdPrograma(1346);
        trilha.setNome("FrontEnd");
        trilha.setDescricao("Descrição da " + trilha.getNome());
        trilha.setStatus("FECHADO");
        trilha.setNome(FakerHolder.instance.name().firstName());

        return trilha;
    }

    public static TrilhaModel gerarTrilhaSemId() {
        TrilhaModel trilha = new TrilhaModel();

        trilha.setNome("BackEnd");
        trilha.setDescricao("Descrição da " + trilha.getNome());
        trilha.setStatus("FECHADO");
        return trilha;
    }

    public static TrilhaModel gerarTrilhaComStatusInexistente(Integer idPrograma) {
        TrilhaModel trilha = new TrilhaModel();

        trilha.setIdTrilha(idPrograma);
        trilha.setNome("Trilha " + FakerHolder.instance.name().firstName());
        trilha.setDescricao("Descrição da " + trilha.getNome());
        trilha.setStatus("Não sei");
        return trilha;
    }

    //endregion
//region    CENÁRIOS DE ATUALIZAÇÃO DE TRILHA
    public static TrilhaModel atualizarTrilhaValida(Integer idPrograma) {
        TrilhaModel trilha = new TrilhaModel();

        trilha.setIdPrograma(idPrograma);
        trilha.setNome("QA");
        trilha.setDescricao("Descrição da " + trilha.getNome());
        trilha.setStatus("ABERTO");
        return trilha;
    }

    public static TrilhaModel atualizarTrilhaSemId() {
        TrilhaModel trilha = new TrilhaModel();

        trilha.setNome("Trilha");
        trilha.setDescricao("Descrição da " + trilha.getNome());
        trilha.setStatus("ABERTO");
        return trilha;
    }

    public static TrilhaModel atualizarTrilhSemCorpo() {
        return new TrilhaModel();
    }

    //endregion
//region    CENÁRIO DE BUSCA POR ID
    public static TrilhaModel buscarTrilhaComId(Integer idTrilha) {
        TrilhaModel trilha = new TrilhaModel();

        trilha.setIdTrilha(idTrilha);
        return trilha;
    }

    //endregion
// region CENÁRIOS PARA  DESATIVAR TRILHA POR ID DA TRILHA
    public static TrilhaModel desativarTrilhaComIdInexistente() {
        TrilhaModel trilha = new TrilhaModel();

        trilha.setIdTrilha(12321564);
        return trilha;
    }

    //endregion
//region   CENÁRIOS PARA DELETAR TRILHA POR ID DA TRILHA
    public static TrilhaModel deletarTrilhaComIdInexistente() {
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
//region CRIAR TRILHA COM MODULO
    public static TrilhaModel gerarTrilhaValidaComModulo(Integer idPrograma) {
        TrilhaModel trilha = new TrilhaModel();

        trilha.setIdPrograma(idPrograma);
        trilha.setNome("BackEnd");
        return getTrilhaModel(trilha);
    }

    public static TrilhaModel gerarTrilhaValidaComModuloSemInformarOId() {
        TrilhaModel trilha = new TrilhaModel();

        trilha.setNome("Trilha");
        return getTrilhaModel(trilha);
    }
//endregion

    private static TrilhaModel getTrilhaModel(TrilhaModel trilha) {
        trilha.setDescricao("Descrição da " + trilha.getNome());
        trilha.setStatus("FECHADO");
        trilha.setNome(FakerHolder.instance.name().firstName());
        // array do modulo
        ModuloModel modulo = new ModuloModel();
        modulo.setNome(FakerHolder.instance.name().firstName());
        modulo.setDescricao("Descrição da " + trilha.getNome());
        modulo.setStatus("ABERTO");

        ArrayList<ModuloModel> modulos = new ArrayList<>();
        modulos.add(modulo);

        trilha.setModulos(modulos);
        return trilha;
    }
}
