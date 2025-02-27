package it.finalTest.infrastruttura.controller;

import it.finalTest.infrastruttura.entities.dto.OrdineDTO;
import it.finalTest.infrastruttura.service.OrdineService;
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

    @PutMapping("/{id}/stato")
    public ResponseEntity<OrdineDTO> updateOrderStatus(@PathVariable Long id, @RequestParam String stato) {
        OrdineDTO updatedOrder = ordineService.updateOrderStatus(id, stato);
        return ResponseEntity.ok(updatedOrder);
    }
}

