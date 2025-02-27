package it.finalTest.infrastruttura.service.utente;

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
    public UtenteDTO createUser(UtenteDTO userDTO) {
        // Validazione dell'email (puoi aggiungere altre validazioni)
        if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        // Controllo se l'email esiste già
        if (utenteRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Salva e restituisce l'utente
        return saveUser(userDTO);
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
        Utente savedUser = utenteRepository.save(user);
        // Ritorna il DTO convertito dall'entità salvata
        return DevTools.convertToDTO(savedUser);
    }

    @Override
    public UtenteDTO updateUser(Long id, UtenteDTO userDTO) {
        // Controllo se l'utente esiste prima di aggiornare
        if (!utenteRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userDTO.setId(id);
        return saveUser(userDTO);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        // Controllo se l'utente esiste prima di eliminare
        if (!utenteRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        utenteRepository.deleteById(id);
    }

    @Override
    public UtenteDTO findUserById(Long id) {
        Utente user = utenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return DevTools.convertToDTO(user);
    }
}

