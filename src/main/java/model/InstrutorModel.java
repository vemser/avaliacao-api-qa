package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstrutorModel {
    public int id;
    public String nome;
    public String login;
    public boolean ativo;
    public ArrayList<String> roles;
    public int idTrilha;
}