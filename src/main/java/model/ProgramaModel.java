package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProgramaModel {
    public String nome;
    public String descricao;
    public String dataInicio;
    public String dataFim;
    public String status;
    public int idPrograma;
    public boolean ativo;
}
