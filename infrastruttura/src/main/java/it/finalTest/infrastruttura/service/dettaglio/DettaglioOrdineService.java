package it.finalTest.infrastruttura.service.dettaglio;

import it.finalTest.infrastruttura.entities.dto.DettaglioOrdineDTO;
import it.finalTest.infrastruttura.entities.dto.OrdineDTO;

public interface DettaglioOrdineService {
    DettaglioOrdineDTO addProductToOrder(Long ordineId, DettaglioOrdineDTO dettaglioOrdineDTO);
    OrdineDTO updateOrderTotal(Long ordineId);
}
