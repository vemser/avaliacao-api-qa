package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AvaliacaoModel {
    public Integer idAcompanhamento;
    public Integer idEstagiario;
    public String objetivoProfissional;
    public String recomendacaoMelhorias;
    public String status;
    public Integer idAvaliacao;
    public boolean ativo;
    public String responsavelFeedback;
}
