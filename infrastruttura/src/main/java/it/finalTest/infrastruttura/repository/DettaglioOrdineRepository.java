package it.finalTest.infrastruttura.repository;

import it.finalTest.infrastruttura.entities.DettaglioOrdine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DettaglioOrdineRepository extends JpaRepository<DettaglioOrdine, Long> {
    List<DettaglioOrdine> findByOrdineId(Long ordineId);

}
