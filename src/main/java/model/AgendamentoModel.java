package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgendamentoModel {
    public Integer idAvaliacao;
    public String dataHorario;
    public String responsavel;
    public Integer idAgendamento;
    public boolean ativo;
}