package it.finalTest.infrastruttura.controller;

import it.finalTest.infrastruttura.entities.dto.ProdottoDTO;
import it.finalTest.infrastruttura.service.prodotto.ProdottoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prodotti")
public class ProdottoController {

    private final ProdottoService prodottoService;

    public ProdottoController(ProdottoService prodottoService) {
        this.prodottoService = prodottoService;
    }

    @PostMapping
    public ResponseEntity<ProdottoDTO> addProduct(@RequestBody ProdottoDTO prodottoDTO) {
        ProdottoDTO createdProduct = prodottoService.addProduct(prodottoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping
    public ResponseEntity<List<ProdottoDTO>> getAllProducts() {
        List<ProdottoDTO> products = prodottoService.getAllProducts();
        return ResponseEntity.ok(products);
    }
}

