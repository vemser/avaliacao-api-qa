package br.com.dbccompany.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaginacaoDTO {

    private Integer totalElementos;
    private Integer quantidadePaginas;
    private Integer pagina;
    private Integer tamanho;
    private List<GeralDTO> elementos;
}
