package it.finalTest.infrastruttura.repository;

import it.finalTest.infrastruttura.entities.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
}
