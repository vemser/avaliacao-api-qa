package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModuloModel {
    public String nome;
    public String descricao;
    public String status;
    public int idTrilha;
    public int idInstrutor;
    public int idModulo;
    public boolean ativo;
    public InstrutorModel instrutorModel;
    public String message;
}