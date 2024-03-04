package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsavelModel {
    public String nome;
    public String login;
    public String cargo;
    public boolean ativo;
}
