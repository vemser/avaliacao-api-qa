package br.com.dbccompany.model;

public class ComentarioBuilder {

    private String comentario;
    private Integer statusComentario;

    public ComentarioBuilder comentario(String comentario) {
        this.comentario = comentario;
        return this;
    }

    public ComentarioBuilder statusComentario(Integer statusComentario) {
        this.statusComentario = statusComentario;
        return this;
    }

    public Comentario build() {
        return new Comentario(comentario, statusComentario);
    }
}
