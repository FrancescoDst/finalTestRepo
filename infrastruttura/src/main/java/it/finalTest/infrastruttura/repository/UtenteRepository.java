package it.finalTest.infrastruttura.repository;

import it.finalTest.infrastruttura.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
    boolean existsByEmail(String email);
}
