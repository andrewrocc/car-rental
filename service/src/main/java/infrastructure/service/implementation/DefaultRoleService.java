package infrastructure.service.implementation;

import infrastructure.model.Role;
import infrastructure.repository.RoleRepository;
import infrastructure.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultRoleService implements RoleService {

    private final RoleRepository repository;

    private final String ADMIN_ROLE = "admin";

    private final String USER_ROLE = "user";

    @Override
    public Role getAdminRole() {
        return getRole(ADMIN_ROLE);
    }

    @Override
    public Role getUserRole() {
        return getRole(USER_ROLE);
    }

    private Role getRole(String roleName) {
        return repository.findByNameStartsWith(Objects.equals(roleName, ADMIN_ROLE) ? ADMIN_ROLE : USER_ROLE)
                .orElseThrow(() -> new EntityNotFoundException("Not found role"));
    }
}