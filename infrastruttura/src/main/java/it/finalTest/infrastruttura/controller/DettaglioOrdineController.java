package it.finalTest.infrastruttura.controller;

import it.finalTest.infrastruttura.entities.dto.DettaglioOrdineDTO;
import it.finalTest.infrastruttura.entities.dto.OrdineDTO;
import it.finalTest.infrastruttura.service.dettaglio.DettaglioOrdineService;
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
        DettaglioOrdineDTO createDettaglio = dettaglioOrdineService.addProductToOrder(ordineId, dettaglioOrdineDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createDettaglio);
    }

    @PutMapping("/ordine/{ordineId}/totale")
    public ResponseEntity<OrdineDTO> updateOrderTotal(@PathVariable Long ordineId) {
        OrdineDTO updatedOrdineDTO = dettaglioOrdineService.updateOrderTotal(ordineId);
        return ResponseEntity.ok(updatedOrdineDTO);
    }
}


