package it.finalTest.infrastruttura.repository;

import it.finalTest.infrastruttura.entities.Ordine;
import it.finalTest.infrastruttura.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrdineRepository extends JpaRepository<Ordine, Long> {
    List<Ordine> findByUtente(Utente utente);

    List<Ordine> findByDataBetween(LocalDate startDate, LocalDate endDate);
}