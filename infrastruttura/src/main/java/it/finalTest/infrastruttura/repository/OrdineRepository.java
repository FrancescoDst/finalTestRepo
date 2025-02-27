package it.finalTest.infrastruttura.repository;

import it.finalTest.infrastruttura.entities.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdineRepository extends JpaRepository<Ordine, Long> {}
