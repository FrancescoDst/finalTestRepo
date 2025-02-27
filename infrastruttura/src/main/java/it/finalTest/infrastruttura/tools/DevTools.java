package it.finalTest.infrastruttura.tools;

import it.finalTest.infrastruttura.entities.*;
import it.finalTest.infrastruttura.entities.dto.DettaglioOrdineDTO;
import it.finalTest.infrastruttura.entities.dto.OrdineDTO;
import it.finalTest.infrastruttura.entities.dto.ProdottoDTO;
import it.finalTest.infrastruttura.entities.dto.UtenteDTO;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DevTools {
    // Conversione per Utente
    public static UtenteDTO convertToDTO(Utente utente) {
        if (utente == null) {
            return null;
        }
        return new UtenteDTO(
                utente.getId(),
                utente.getNome(),
                utente.getEmail()
        );
    }

    public static Utente convertToEntity(UtenteDTO utenteDTO) {
        if (utenteDTO == null) {
            return null;
        }
        return Utente.builder()
                .id(utenteDTO.getId())
                .nome(utenteDTO.getNome())
                .email(utenteDTO.getEmail())
                .build();
    }

    // Conversione per Prodotto
    public static ProdottoDTO convertToDTO(Prodotto prodotto) {
        if (prodotto == null) {
            return null;
        }
        return new ProdottoDTO(
                prodotto.getId(),
                prodotto.getNome(),
                prodotto.getPrezzo()
        );
    }

    public static Prodotto convertToEntity(ProdottoDTO prodottoDTO) {
        if (prodottoDTO == null) {
            return null;
        }
        return Prodotto.builder()
                .id(prodottoDTO.getId())
                .nome(prodottoDTO.getNome())
                .prezzo(prodottoDTO.getPrezzo())
                .build();
    }

    public static OrdineDTO convertToDTO(Ordine ordine) {
        if (ordine == null) {
            return null;
        }

        // Converte la lista di DettaglioOrdine in DettaglioOrdineDTO
        List<DettaglioOrdineDTO> dettagliOrdineDTO = Optional.ofNullable(ordine.getDettagli())
                .orElse(Collections.emptyList())
                .stream()
                .map(DevTools::convertToDTO)
                .collect(Collectors.toList());

        return new OrdineDTO(
                ordine.getId(),
                ordine.getData(),
                ordine.getStato(),
                ordine.getTotale(),
                dettagliOrdineDTO // Aggiungi la lista di dettagli ordine
        );
    }

    public static Ordine convertToEntity(OrdineDTO ordineDTO) {
        if (ordineDTO == null) {
            return null;
        }

        // Imposta lo stato direttamente come enum
        StatoOrdine stato = ordineDTO.getStato() != null ? ordineDTO.getStato() : StatoOrdine.IN_ATTESA;

        return Ordine.builder()
                .id(ordineDTO.getId())
                .data(ordineDTO.getData())
                .stato(stato) // Imposta lo stato come enum
                .totale(ordineDTO.getTotale())
                .build();
    }

    // Conversione per Dettaglio
    public static DettaglioOrdineDTO convertToDTO(DettaglioOrdine dettaglioOrdine) {
        if (dettaglioOrdine == null) {
            return null;
        }

        // Assicurati che il prodotto non sia null
        Long prodottoId = (dettaglioOrdine.getProdotto() != null) ? dettaglioOrdine.getProdotto().getId() : null;

        return new DettaglioOrdineDTO(
                dettaglioOrdine.getId(),
                dettaglioOrdine.getQuantita(),
                prodottoId // Assicurati di passare anche l'ID del prodotto
        );
    }

    public static DettaglioOrdine convertToEntity(DettaglioOrdineDTO dettaglioOrdineDTO) {
        if (dettaglioOrdineDTO == null) {
            return null;
        }

        DettaglioOrdine dettaglioOrdine = DettaglioOrdine.builder()
                .id(dettaglioOrdineDTO.getId())
                .quantita(dettaglioOrdineDTO.getQuantita())
                .build();

        return dettaglioOrdine;
    }

    public static DettaglioOrdine convertToEntity(DettaglioOrdineDTO dettaglioOrdineDTO, Prodotto prodotto) {
        if (dettaglioOrdineDTO == null) {
            return null;
        }

        return DettaglioOrdine.builder()
                .id(dettaglioOrdineDTO.getId())
                .quantita(dettaglioOrdineDTO.getQuantita())
                .prodotto(prodotto) // Associa il prodotto
                .build();
    }
}



