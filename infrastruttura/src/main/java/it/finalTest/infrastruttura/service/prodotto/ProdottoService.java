package it.finalTest.infrastruttura.service.prodotto;

import it.finalTest.infrastruttura.entities.dto.ProdottoDTO;

import java.util.List;

public interface ProdottoService {
    ProdottoDTO addProduct(ProdottoDTO prodottoDTO);

    List<ProdottoDTO> getAllProducts();

}
