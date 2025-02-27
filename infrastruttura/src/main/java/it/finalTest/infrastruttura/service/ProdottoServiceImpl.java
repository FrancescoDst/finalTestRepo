package it.finalTest.infrastruttura.service;

import it.finalTest.infrastruttura.entities.Prodotto;
import it.finalTest.infrastruttura.entities.dto.ProdottoDTO;
import it.finalTest.infrastruttura.repository.ProdottoRepository;
import it.finalTest.infrastruttura.tools.DevTools;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdottoServiceImpl implements ProdottoService {

    private final ProdottoRepository prodottoRepository;

    public ProdottoServiceImpl(ProdottoRepository prodottoRepository) {
        this.prodottoRepository = prodottoRepository;
    }

    @Override
    public ProdottoDTO addProduct(ProdottoDTO prodottoDTO) {
        Prodotto prodotto = DevTools.convertToEntity(prodottoDTO);
        Prodotto savedProduct = prodottoRepository.save(prodotto);
        return DevTools.convertToDTO(savedProduct);
    }

    @Override
    public List<ProdottoDTO> getAllProducts() {
        List<Prodotto> prodotti = prodottoRepository.findAll();
        return prodotti.stream()
                .map(DevTools::convertToDTO)
                .collect(Collectors.toList());
    }
}

