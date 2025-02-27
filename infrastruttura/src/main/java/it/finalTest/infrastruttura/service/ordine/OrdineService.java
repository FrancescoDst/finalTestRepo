package it.finalTest.infrastruttura.service.ordine;

import it.finalTest.infrastruttura.entities.StatoOrdine;
import it.finalTest.infrastruttura.entities.dto.OrdineDTO;

import java.util.List;

public interface OrdineService {
    OrdineDTO createOrder(Long userId, OrdineDTO ordineDTO);

    List<OrdineDTO> getAllOrders();

    OrdineDTO updateOrderStatus(Long id, StatoOrdine stato);
}
