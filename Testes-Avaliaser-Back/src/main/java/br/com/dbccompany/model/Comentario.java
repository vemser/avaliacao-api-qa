package br.com.dbccompany.model;

import lombok.Data;

@Data
public class Comentario {

    private String comentario;
    private Integer statusComentario;

    public Comentario(String comentario, Integer statusComentario) {
        this.comentario = comentario;
        this.statusComentario = statusComentario;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "comentario='" + comentario + '\'' +
                ", statusComentario='" + statusComentario + '\'' +
                '}';
    }
}
