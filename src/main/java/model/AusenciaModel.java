package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AusenciaModel {
    public String observacao;
    public String diaDeFalta;
    public String horarioInicio;
    public String horarioFim;
    public Integer idEstagiario;
    public Object link;
    public Integer idAusencia;
    public boolean visto;
    public EstagiarioModel estagiarioModel;
    public String dataCriacao;
}