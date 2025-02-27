package it.finalTest.infrastruttura.controller;

import it.finalTest.infrastruttura.entities.dto.DettaglioOrdineDTO;
import it.finalTest.infrastruttura.service.DettaglioOrdineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dettagli-ordine")
public class DettaglioOrdineController {

    private final DettaglioOrdineService dettaglioOrdineService;

    public DettaglioOrdineController(DettaglioOrdineService dettaglioOrdineService) {
        this.dettaglioOrdineService = dettaglioOrdineService;
    }

    @PostMapping("/ordine/{ordineId}/prodotto")
    public ResponseEntity<DettaglioOrdineDTO> addProductToOrder(@PathVariable Long ordineId, @RequestBody DettaglioOrdineDTO dettaglioOrdineDTO) {
        DettaglioOrdineDTO createdDettaglio = dettaglioOrdineService.addProductToOrder(ordineId, dettaglioOrdineDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDettaglio);
    }

    @PutMapping("/ordine/{ordineId}/totale")
    public ResponseEntity<Void> updateOrderTotal(@PathVariable Long ordineId) {
        dettaglioOrdineService.updateOrderTotal(ordineId);
        return ResponseEntity.noContent().build();
    }
}

