package it.finalTest.infrastruttura.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdottoDTO {
    private Long id;
    private String nome;
    private Double prezzo;
}
