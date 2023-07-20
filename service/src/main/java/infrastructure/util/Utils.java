package infrastructure.util;

import infrastructure.model.Role;

import java.util.Collection;

import static infrastructure.service.implementation.DefaultUserService.ADMIN_ROLE;

public abstract class Utils {

    public static final String EMPTY_STRING = "";

    public static boolean hasAdminRole(Collection<Role> list) {
        return list.stream().anyMatch(x -> x.getName().equals(ADMIN_ROLE));
    }
}
