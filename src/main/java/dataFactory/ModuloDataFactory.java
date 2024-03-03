package dataFactory;

import model.ModuloModel;
import net.datafaker.Faker;


public class ModuloDataFactory {
    static Faker faker = new Faker();
    public static ModuloModel gerarModuloModel(Integer idTrilha) {
        ModuloModel modulo = new ModuloModel();
        modulo.setNome(faker.internet().slug());
        modulo.setDescricao("Descrição do modulo" + modulo.getNome());
        modulo.setStatus("ABERTO");
        modulo.setIdTrilha(idTrilha);
        modulo.setIdInstrutor(2);
        return modulo;
    }

    public static ModuloModel gerarModuloModel(ModuloModel modulo) {
        return gerarModuloModel(modulo.getIdTrilha());
    }

    public static ModuloModel atualizarModulo(Integer idTrilha) {
        ModuloModel modulo = new ModuloModel();

        modulo.setNome("Javascript");
        modulo.setDescricao("Descrição do modulo" + modulo.getNome());
        modulo.setStatus("ABERTO");
        modulo.setIdTrilha(idTrilha);
        modulo.setIdInstrutor(2);
        return modulo;
    }

    public static ModuloModel buscarModuloComId(Integer idModulo) {
        ModuloModel modulo = new ModuloModel();

        modulo.setIdModulo(idModulo);
        return modulo;
    }

}
