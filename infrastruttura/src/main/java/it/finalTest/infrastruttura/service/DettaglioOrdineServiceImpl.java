package it.finalTest.infrastruttura.service;

import it.finalTest.infrastruttura.entities.DettaglioOrdine;
import it.finalTest.infrastruttura.entities.Ordine;
import it.finalTest.infrastruttura.entities.dto.DettaglioOrdineDTO;
import it.finalTest.infrastruttura.repository.DettaglioOrdineRepository;
import it.finalTest.infrastruttura.repository.OrdineRepository;
import it.finalTest.infrastruttura.tools.DevTools;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DettaglioOrdineServiceImpl implements DettaglioOrdineService {

    private final DettaglioOrdineRepository dettaglioOrdineRepository;
    private final OrdineRepository ordineRepository;

    public DettaglioOrdineServiceImpl(DettaglioOrdineRepository dettaglioOrdineRepository, OrdineRepository ordineRepository) {
        this.dettaglioOrdineRepository = dettaglioOrdineRepository;
        this.ordineRepository = ordineRepository;
    }

    @Override
    public DettaglioOrdineDTO addProductToOrder(Long ordineId, DettaglioOrdineDTO dettaglioOrdineDTO) {
        Ordine ordine = ordineRepository.findById(ordineId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        DettaglioOrdine dettaglioOrdine = DevTools.convertToEntity(dettaglioOrdineDTO);
        dettaglioOrdine.setOrdine(ordine); // Associa l'ordine al dettaglio
        DettaglioOrdine savedDettaglio = dettaglioOrdineRepository.save(dettaglioOrdine);
        updateOrderTotal(ordineId); // Aggiorna il totale dell'ordine
        return DevTools.convertToDTO(savedDettaglio);
    }

    @Override
    public void updateOrderTotal(Long ordineId) {
        Ordine ordine = ordineRepository.findById(ordineId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Calcola il totale basato sui dettagli ordine
        List<DettaglioOrdine> dettagli = dettaglioOrdineRepository.findByOrdineId(ordineId);
        double totale = dettagli.stream()
                .mapToDouble(DettaglioOrdine::getPrezzoTotale)
                .sum();

        ordine.setTotale(totale);
        ordineRepository.save(ordine);
    }
}
