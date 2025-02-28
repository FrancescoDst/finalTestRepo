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

    // Calcola il totale in base ai dettagli ordine
    @Override
    @Transactional
    public OrdineDTO createOrder(Long userId, OrdineDTO ordineDTO) {
        ordineDTO.setData(LocalDate.now());

        Double totale = ordineDTO.getDettagli().stream()
                .mapToDouble(dettaglioDTO -> {
                    // Recupera il prodotto in base all'ID
                    Prodotto prodotto = prodottoRepository.findById(dettaglioDTO.getProdottoId())
                            .orElseThrow(() -> new RuntimeException("Product not found"));
                    return prodotto.getPrezzo() * dettaglioDTO.getQuantita();
                })
                .sum();
        ordineDTO.setTotale(totale);

        Ordine ordine = DevTools.convertToEntity(ordineDTO);

        Utente utente = utenteRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        ordine.setUtente(utente);

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

    // Imposta il nuovo stato
    @Override
    @Transactional
    public OrdineDTO updateOrderStatus(Long id, StatoOrdine stato) {
        Ordine ordine = ordineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        ordine.setStato(stato);
        Ordine updatedOrder = ordineRepository.save(ordine);
        return DevTools.convertToDTO(updatedOrder);
    }

    // Somma i totali degli ordini dell'utente
    @Transactional
    public Double calcolaTotaleSpeso(Long userId) {
        Utente utente = utenteRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return utente.getOrdini().stream()
                .mapToDouble(Ordine::getTotale)
                .sum();
    }

    public List<OrdineDTO> getOrdiniInIntervallo(LocalDate startDate, LocalDate endDate) {
        List<Ordine> ordini = ordineRepository.findByDataBetween(startDate, endDate);
        return ordini.stream()
                .map(DevTools::convertToDTO)
                .collect(Collectors.toList());
    }
}
