package infrastructure.service;

import infrastructure.model.Role;
import infrastructure.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService {

    // TODO to enum UserRole
    private final RoleRepository repository;

    public Role getRoleAdmin() {
        return repository.getReferenceById(1L);
    }

    public Role getRoleUser() {
        return repository.getReferenceById(2L);
    }
}