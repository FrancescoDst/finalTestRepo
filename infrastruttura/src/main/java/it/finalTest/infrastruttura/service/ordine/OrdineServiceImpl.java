package it.finalTest.infrastruttura.service.ordine;

import it.finalTest.infrastruttura.entities.Ordine;
import it.finalTest.infrastruttura.entities.Prodotto;
import it.finalTest.infrastruttura.entities.StatoOrdine;
import it.finalTest.infrastruttura.entities.Utente;
import it.finalTest.infrastruttura.entities.dto.OrdineDTO;
import it.finalTest.infrastruttura.repository.OrdineRepository;
import it.finalTest.infrastruttura.repository.ProdottoRepository;
import it.finalTest.infrastruttura.repository.UtenteRepository;
import it.finalTest.infrastruttura.tools.DevTools;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdineServiceImpl implements OrdineService {

    private final OrdineRepository ordineRepository;
    private final UtenteRepository utenteRepository;
    private final ProdottoRepository prodottoRepository;

    public OrdineServiceImpl(OrdineRepository ordineRepository, UtenteRepository utenteRepository, ProdottoRepository prodottoRepository) {
        this.ordineRepository = ordineRepository;
        this.utenteRepository = utenteRepository;
        this.prodottoRepository = prodottoRepository;
    }

    @Override
    @Transactional
    public OrdineDTO createOrder(Long userId, OrdineDTO ordineDTO) {
        ordineDTO.setData(LocalDate.now());

        // Calcola il totale in base ai dettagli ordine
        Double totale = ordineDTO.getDettagli().stream()
                .mapToDouble(dettaglioDTO -> {
                    // Recupera il prodotto in base all'ID
                    Prodotto prodotto = prodottoRepository.findById(dettaglioDTO.getProdottoId())
                            .orElseThrow(() -> new RuntimeException("Product not found"));
                    return prodotto.getPrezzo() * dettaglioDTO.getQuantita(); // Calcola il totale per ogni prodotto
                })
                .sum();
        ordineDTO.setTotale(totale);

        // Crea l'oggetto Ordine
        Ordine ordine = DevTools.convertToEntity(ordineDTO);

        // Recupera l'utente e associa all'ordine
        Utente utente = utenteRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        ordine.setUtente(utente);

        // Salva l'ordine
        Ordine savedOrder = ordineRepository.save(ordine);
        return DevTools.convertToDTO(savedOrder);
    }

    @Override
    public List<OrdineDTO> getAllOrders() {
        List<Ordine> ordini = ordineRepository.findAll();
        return ordini.stream()
                .map(DevTools::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrdineDTO updateOrderStatus(Long id, StatoOrdine stato) {
        Ordine ordine = ordineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Imposta il nuovo stato
        ordine.setStato(stato);
        Ordine updatedOrder = ordineRepository.save(ordine);
        return DevTools.convertToDTO(updatedOrder);
    }

}
