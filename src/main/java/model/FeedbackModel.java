package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedbackModel {
    private Integer idAvaliacao;
    private Integer nota;
    private String descricao;
    private String tipoFeedback;
    private String cargoResponsavel;
    private Integer idFeedBack;
    private String status;
    private String loginResponsavel;
    private Boolean ativo;
    private Integer idPrograma;
}
