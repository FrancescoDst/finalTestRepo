package it.finalTest.infrastruttura.service;

import it.finalTest.infrastruttura.entities.Utente;
import it.finalTest.infrastruttura.entities.dto.UtenteDTO;
import it.finalTest.infrastruttura.repository.UtenteRepository;
import it.finalTest.infrastruttura.tools.DevTools;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UtenteServiceImpl implements UtenteService {

    private final UtenteRepository utenteRepository;

    public UtenteServiceImpl(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    @Override
    public List<UtenteDTO> getAllUsers() {
        List<Utente> utenti = utenteRepository.findAll();
        return utenti.stream()
                .map(DevTools::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UtenteDTO saveUser(UtenteDTO userDTO) {
        // Converte il DTO in entità
        Utente user = DevTools.convertToEntity(userDTO);
        // Se l'utente esiste già, manteniamo la password esistente
        Utente existingUser = user.getId() != null ? utenteRepository.findById(user.getId()).orElse(null) : null;
        if (existingUser != null) {
            user.setEmail(existingUser.getEmail());
        }
        Utente savedUser = utenteRepository.save(user);
        // Ritorna il DTO convertito dall'entità salvata
        return DevTools.convertToDTO(savedUser);
    }

    @Override
    public UtenteDTO updateUser(Long id, UtenteDTO userDTO) {
        userDTO.setId(id);
        return saveUser(userDTO);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        utenteRepository.deleteById(id);
    }

    @Override
    public UtenteDTO findUserById(Long id) {
        Utente user = utenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return DevTools.convertToDTO(user);
    }
}
