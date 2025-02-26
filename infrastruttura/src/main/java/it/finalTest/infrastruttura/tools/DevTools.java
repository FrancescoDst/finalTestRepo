package it.finalTest.infrastruttura.tools;

import it.finalTest.infrastruttura.entities.DettaglioOrdine;
import it.finalTest.infrastruttura.entities.Ordine;
import it.finalTest.infrastruttura.entities.Prodotto;
import it.finalTest.infrastruttura.entities.Utente;
import it.finalTest.infrastruttura.entities.dto.DettaglioOrdineDTO;
import it.finalTest.infrastruttura.entities.dto.OrdineDTO;
import it.finalTest.infrastruttura.entities.dto.ProdottoDTO;
import it.finalTest.infrastruttura.entities.dto.UtenteDTO;

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
    public static ProdottoDTO convertToDTO (Prodotto prodotto){
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
    // Conversione per Ordine
    public static OrdineDTO convertToDTO (Ordine ordine) {
        if (ordine == null) {
            return null;
        }
        return new OrdineDTO(
                ordine.getId(),
                ordine.getData(),
                ordine.getStato(),
                ordine.getTotale()
        );
    }

    public static Ordine convertToEntity (OrdineDTO ordineDTO) {
        if (ordineDTO == null) {
            return null;
        }
        return Ordine.builder()
                .id(ordineDTO.getId())
                .data(ordineDTO.getData())
                .stato(ordineDTO.getStato())
                .totale(ordineDTO.getTotale())
                .build();
    }

    // Conversione per Dettaglio
    public static DettaglioOrdineDTO convertToDTO (DettaglioOrdine dettaglioOrdine) {
        if (dettaglioOrdine == null) {
            return null;
        }
        return new DettaglioOrdineDTO(
                dettaglioOrdine.getId(),
                dettaglioOrdine.getQuantita(),
                dettaglioOrdine.getPrezzoTotale()
        );
    }

    public static DettaglioOrdine convertToEntity (DettaglioOrdineDTO dettaglioOrdineDTO) {
        if (dettaglioOrdineDTO == null) {
            return null;
        }
        return DettaglioOrdine.builder()
                .id(dettaglioOrdineDTO.getId())
                .quantita(dettaglioOrdineDTO.getQuantita())
                .prezzoTotale(dettaglioOrdineDTO.getPrezzoTotale())
                .build();
    }
}

