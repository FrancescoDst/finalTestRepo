package it.finalTest.infrastruttura.service.dettaglio;

import it.finalTest.infrastruttura.entities.DettaglioOrdine;
import it.finalTest.infrastruttura.entities.Ordine;
import it.finalTest.infrastruttura.entities.Prodotto;
import it.finalTest.infrastruttura.entities.dto.DettaglioOrdineDTO;
import it.finalTest.infrastruttura.entities.dto.OrdineDTO;
import it.finalTest.infrastruttura.repository.DettaglioOrdineRepository;
import it.finalTest.infrastruttura.repository.OrdineRepository;
import it.finalTest.infrastruttura.repository.ProdottoRepository;
import it.finalTest.infrastruttura.tools.DevTools;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DettaglioOrdineServiceImpl implements DettaglioOrdineService {

    private final DettaglioOrdineRepository dettaglioOrdineRepository;
    private final OrdineRepository ordineRepository;
    private final ProdottoRepository prodottoRepository;

    public DettaglioOrdineServiceImpl(DettaglioOrdineRepository dettaglioOrdineRepository, OrdineRepository ordineRepository, ProdottoRepository prodottoRepository) {
        this.dettaglioOrdineRepository = dettaglioOrdineRepository;
        this.ordineRepository = ordineRepository;
        this.prodottoRepository = prodottoRepository;
    }

    public DettaglioOrdineDTO addProductToOrder(Long ordineId, DettaglioOrdineDTO dettaglioOrdineDTO) {
        Ordine ordine = ordineRepository.findById(ordineId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Recupera il prodotto in base all'ID
        Prodotto prodotto = prodottoRepository.findById(dettaglioOrdineDTO.getProdottoId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Crea il dettaglio ordine
        DettaglioOrdine dettaglioOrdine = DevTools.convertToEntity(dettaglioOrdineDTO);
        dettaglioOrdine.setOrdine(ordine);
        dettaglioOrdine.setProdotto(prodotto);

        // Aggiungi il dettaglio ordine alla lista dell'ordine
        ordine.getDettagli().add(dettaglioOrdine);

        // Salva il dettaglio ordine nel database
        DettaglioOrdine savedDettaglio = dettaglioOrdineRepository.save(dettaglioOrdine);

        // Aggiorna il totale dell'ordine
        updateOrderTotal(ordineId);

        // Restituisci il DTO del dettaglio ordine appena creato
        return DevTools.convertToDTO(savedDettaglio);
    }

    // Totale basato sui dettagli ordine
    @Override
    public OrdineDTO updateOrderTotal(Long ordineId) {
        Ordine ordine = ordineRepository.findById(ordineId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        List<DettaglioOrdine> dettagli = dettaglioOrdineRepository.findByOrdineId(ordineId);
        double totale = dettagli.stream()
                .mapToDouble(dettaglio -> {
                    Prodotto prodotto = dettaglio.getProdotto();
                    return prodotto.getPrezzo() * dettaglio.getQuantita();
                })
                .sum();

        ordine.setTotale(totale);
        ordineRepository.save(ordine);

        return DevTools.convertToDTO(ordine);
    }

}

