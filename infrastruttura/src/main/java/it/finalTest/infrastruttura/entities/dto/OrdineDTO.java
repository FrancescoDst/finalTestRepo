package it.finalTest.infrastruttura.entities.dto;

import it.finalTest.infrastruttura.entities.StatoOrdine;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdineDTO {
    private Long id;
    private LocalDate data = LocalDate.now();
    @Enumerated(EnumType.STRING)
    private StatoOrdine stato = StatoOrdine.IN_ATTESA;
    private Double totale;

    private List<DettaglioOrdineDTO> dettagli = new ArrayList<>();
}
