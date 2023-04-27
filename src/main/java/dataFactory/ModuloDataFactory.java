package dataFactory;

import model.ModuloModel;
import net.datafaker.Faker;

public class ModuloDataFactory {
    private static Faker faker = new Faker();
    public static ModuloModel gerarModuloModel(Integer idTrilha) {
        ModuloModel modulo = new ModuloModel();
        modulo.setNome(faker.name().firstName());
        modulo.setDescricao("Descrição do modulo" + modulo.getNome());
        modulo.setStatus("ABERTO");
        modulo.setIdTrilha(idTrilha);
        return modulo;
    }
    public static ModuloModel gerarModuloModel(ModuloModel modulo) {
        return gerarModuloModel(modulo.getIdTrilha());
    }

    public static ModuloModel atualizarModulo(Integer idTrilha) {
        ModuloModel modulo = new ModuloModel();
        modulo.setNome(faker.name().firstName());
        modulo.setDescricao("Descrição do modulo" + modulo.getNome());
        modulo.setStatus("ABERTO");
        modulo.setIdTrilha(idTrilha);
        return modulo;
    }

}
