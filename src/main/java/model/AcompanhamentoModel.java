package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.restassured.http.ContentType;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AcompanhamentoModel {
    public Integer idPrograma;
    public String titulo;
    public String dataInicio;
    public String dataFim;
    public String horarioInicio;
    public String horarioFim;
    public Integer duracao;
    public String descricao;
    public Integer numeroResponsaveis;
    public String status;
    public String[] Emailresponsaveis;
    public ArrayList<ResponsavelModel> responsaveis;
    public Integer idAcompanhamento;
    public boolean ativo;
}
