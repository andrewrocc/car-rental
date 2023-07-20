package infrastructure.service;

import infrastructure.model.Role;

public interface RoleService {

    Role getAdminRole();

    Role getUserRole();
}
