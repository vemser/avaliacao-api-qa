package dataFactory;

import model.InstrutorModel;
import model.ModuloModel;
import model.TrilhaModel;
import utils.FakerHolder;

import java.util.ArrayList;
import java.util.Random;

public class TrilhaDataFactory {

//region    CENÁRIOS DE CRIAÇÃO DE TRILHA
    static Random random = new Random();
    public static ArrayList<InstrutorModel> getInstrutor(){
        ArrayList<String> role = new ArrayList<>();
        role.add("INSTRUTOR");

        InstrutorModel instrutorModel = new InstrutorModel();
        instrutorModel.setLogin("Rafael.teste" );
        instrutorModel.setNome("Rafael TESTE");
        instrutorModel.setRoles(role);

        ArrayList<InstrutorModel> instrutor = new ArrayList<>();
        instrutor.add(instrutorModel);
        return instrutor;
    }

    public static TrilhaModel gerarTrilhaValida() {
        TrilhaModel trilha = new TrilhaModel();

        trilha.setIdPrograma(1);
        trilha.setNome("FrontEnd");
        trilha.setDescricao("Descrição da " + trilha.getNome());
        trilha.setStatus("FECHADO");
        trilha.setInstrutorRequestDTOS(getInstrutor());

        return trilha;
    }

    public static TrilhaModel gerarTrilhaSemId() {
        TrilhaModel trilha = new TrilhaModel();

        trilha.setNome("BackEnd");
        trilha.setDescricao("Descrição da " + trilha.getNome());
        trilha.setStatus("FECHADO");
        trilha.setInstrutorRequestDTOS(getInstrutor());
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
        trilha.setInstrutorRequestDTOS(getInstrutor());
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
        ArrayList<String> roles = new ArrayList<>();
        roles.add("ROLE_INSTRUTOR");

        InstrutorModel instrutorModel = new InstrutorModel();
        instrutorModel.setId(42);
        instrutorModel.setLogin("Rafael.teste" );
        instrutorModel.setNome("Rafael TESTE");
        instrutorModel.setRoles(roles);
        instrutorModel.setAtivo(true);

        ArrayList<InstrutorModel> instrutor = new ArrayList<>();
        instrutor.add(instrutorModel);

        TrilhaModel trilha = new TrilhaModel();

        trilha.setIdPrograma(idPrograma);
        trilha.setNome("BackEnd");
        trilha.setDescricao("Trilha BackEND INSANA");
        trilha.setInstrutorRequestDTOS(instrutor);
        trilha.setModulos(getModulo());
        trilha.setStatus("ABERTO");
        return trilha;
    }

    public static TrilhaModel gerarTrilhaValidaComModuloSemInformarOId() {
        TrilhaModel trilha = new TrilhaModel();

        trilha.setNome("Trilha");
        trilha.setInstrutorRequestDTOS(getInstrutor());
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

    private static ArrayList<ModuloModel> getModulo(){
        ArrayList<String> roles = new ArrayList<>();
        roles.add("ROLE_INSTRUTOR");

        InstrutorModel instrutorModel = new InstrutorModel();
        instrutorModel.setId(42);
        instrutorModel.setLogin("Rafael.teste" );
        instrutorModel.setNome("Rafael TESTE");
        instrutorModel.setRoles(roles);
        instrutorModel.setAtivo(true);

        TrilhaModel trilha = new TrilhaModel();

        int numeroAleatorio = random.nextInt(1000) + 1;
        ModuloModel modulo = new ModuloModel();
        modulo.setNome("POO " + numeroAleatorio);
        modulo.setDescricao("Programação orientada a funcionamento expresso");
        modulo.setStatus("ABERTO");
        modulo.setInstrutorModel(instrutorModel);

        ArrayList<ModuloModel> modulos = new ArrayList<>();
        modulos.add(modulo);
        return modulos;
    }
}
