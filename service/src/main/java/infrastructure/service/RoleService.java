package infrastructure.service;

import infrastructure.model.Role;
import infrastructure.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repository;

    public Role getAdminRole() {
        return repository.findByName("admin");
    }

    public Role getUserRole() {
        return repository.findByName("user");
    }
}