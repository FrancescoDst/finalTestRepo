package it.finalTest.infrastruttura.service;

import it.finalTest.infrastruttura.entities.dto.OrdineDTO;

import java.util.List;

public interface OrdineService {
    OrdineDTO createOrder(Long userId, OrdineDTO ordineDTO);

    List<OrdineDTO> getAllOrders();

    OrdineDTO updateOrderStatus(Long id, String stato);
}
