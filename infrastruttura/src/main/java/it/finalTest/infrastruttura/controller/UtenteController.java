package it.finalTest.infrastruttura.controller;

import it.finalTest.infrastruttura.entities.dto.UtenteDTO;
import it.finalTest.infrastruttura.service.UtenteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController {

    private final UtenteService utenteService;

    public UtenteController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    @PostMapping
    public ResponseEntity<UtenteDTO> createUser(@RequestBody UtenteDTO utenteDTO) {
        UtenteDTO createdUser = utenteService.createUser(utenteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<UtenteDTO>> getAllUsers() {
        List<UtenteDTO> users = utenteService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtenteDTO> getUserById(@PathVariable Long id) {
        UtenteDTO user = utenteService.findUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtenteDTO> updateUser(@PathVariable Long id, @RequestBody UtenteDTO utenteDTO) {
        UtenteDTO updatedUser = utenteService.updateUser(id, utenteDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        utenteService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

