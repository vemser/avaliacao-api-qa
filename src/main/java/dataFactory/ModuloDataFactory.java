package dataFactory;

import model.ModuloModel;
import model.ProgramaModel;
import model.TrilhaModel;

import static dataFactory.DataFactory.faker;

public class ModuloDataFactory {
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
}
