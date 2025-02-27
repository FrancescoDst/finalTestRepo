package it.finalTest.infrastruttura.service;

import it.finalTest.infrastruttura.entities.Ordine;
import it.finalTest.infrastruttura.entities.Utente;
import it.finalTest.infrastruttura.entities.dto.OrdineDTO;
import it.finalTest.infrastruttura.repository.OrdineRepository;
import it.finalTest.infrastruttura.repository.UtenteRepository;
import it.finalTest.infrastruttura.tools.DevTools;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

    @Service
    public class OrdineServiceImpl implements OrdineService {

        private final OrdineRepository ordineRepository;
        private final UtenteRepository utenteRepository;

        public OrdineServiceImpl(OrdineRepository ordineRepository, UtenteRepository utenteRepository) {
            this.ordineRepository = ordineRepository;
            this.utenteRepository = utenteRepository;
        }

        @Override
        @Transactional
        public OrdineDTO createOrder(Long userId, OrdineDTO ordineDTO) {
            // Verifica se l'utente esiste
            Utente utente = utenteRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Converte il DTO in entità e associa l'utente
            Ordine ordine = DevTools.convertToEntity(ordineDTO);
            ordine.setUtente(utente); // Imposta l'utente associato

            // Salva e restituisce l'ordine
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
        public OrdineDTO updateOrderStatus(Long id, String stato) {
            Ordine ordine = ordineRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Order not found"));

            // Controllo stato valido
            if (!isValidStato(stato)) {
                throw new IllegalArgumentException("Invalid order status");
            }

            ordine.setStato(stato);
            Ordine updatedOrder = ordineRepository.save(ordine);
            return DevTools.convertToDTO(updatedOrder);
        }

        private boolean isValidStato(String stato) {
            return stato.equals("IN_ATTESA") || stato.equals("SPEDITO") || stato.equals("CONSEGNATO");
        }
    }
