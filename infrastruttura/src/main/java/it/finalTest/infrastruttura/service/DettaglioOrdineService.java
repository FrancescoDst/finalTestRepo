package it.finalTest.infrastruttura.service;

import it.finalTest.infrastruttura.entities.dto.DettaglioOrdineDTO;

public interface DettaglioOrdineService {
    DettaglioOrdineDTO addProductToOrder(Long ordineId, DettaglioOrdineDTO dettaglioOrdineDTO);
    void updateOrderTotal(Long ordineId);
}
