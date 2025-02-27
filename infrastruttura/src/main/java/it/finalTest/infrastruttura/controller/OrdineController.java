package it.finalTest.infrastruttura.controller;

import it.finalTest.infrastruttura.entities.StatoOrdine;
import it.finalTest.infrastruttura.entities.dto.OrdineDTO;
import it.finalTest.infrastruttura.service.ordine.OrdineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordini")
public class OrdineController {

    private final OrdineService ordineService;

    public OrdineController(OrdineService ordineService) {
        this.ordineService = ordineService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<OrdineDTO> createOrder(@PathVariable Long userId, @RequestBody OrdineDTO ordineDTO) {
        OrdineDTO createdOrder = ordineService.createOrder(userId, ordineDTO);
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping
    public ResponseEntity<List<OrdineDTO>> getAllOrders() {
        List<OrdineDTO> ordini = ordineService.getAllOrders();
        return ResponseEntity.ok(ordini);
    }

    @PutMapping("/{ordineId}/stato")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable Long ordineId, @RequestBody StatoOrdine nuovoStato) {
        ordineService.updateOrderStatus(ordineId, nuovoStato);
        return ResponseEntity.noContent().build();
    }
}

