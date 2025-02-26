package it.finalTest.infrastruttura.service;

import it.finalTest.infrastruttura.entities.dto.UtenteDTO;

import java.util.List;

public interface UtenteService {

    List<UtenteDTO> getAllUsers();

    UtenteDTO saveUser(UtenteDTO user);

    UtenteDTO updateUser(Long id, UtenteDTO user);

    void deleteUser(Long id);

    UtenteDTO findUserById(Long id);
}
