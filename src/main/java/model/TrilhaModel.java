package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrilhaModel {
    public int idPrograma;
    public String nome;
    public String descricao;
    public String status;
    public int idTrilha;
    public boolean ativo;
    public String link;
    public ArrayList<ModuloModel> modulos;
    public ArrayList<InstrutorModel> instrutores;
}
