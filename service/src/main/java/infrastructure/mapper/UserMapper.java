package infrastructure.mapper;

import infrastructure.dto.UserDTO;
import infrastructure.dto.UserDTO_REST;
import infrastructure.model.Order;
import infrastructure.model.User;
import infrastructure.service.RoleService;
import infrastructure.util.Utils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Mapper(config = MappingConfiguration.class)
public abstract class UserMapper {

    @Autowired
    private RoleService roleService;

    public UserDTO mapToDto(User user) {
        UserDTO dto = new UserDTO();
        return dto.setId(user.getId()).setFirstName(user.getFirstName())
                .setLastName(user.getLastName()).setEmail(user.getEmail())
                .setPaymentCard(user.getPaymentCard()).setPassword(user.getPassword())
                .setAdmin(Utils.hasAdminRole(user.getRoles())).setOrders(user.getOrders().toArray(Order[]::new));
    }

    // TODO set up password security
    public User mapToUser(UserDTO dto) {
        User user = new User();
        String parsePassword = dto.getPassword().contains("{noop}") ? dto.getPassword() :"{noop}" + dto.getPassword();
        HashSet<Order> orders = Objects.isNull(dto.getOrders()) ? null : new HashSet<>(List.of(dto.getOrders()));
        return user.setId(dto.getId()).setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName()).setEmail(dto.getEmail())
                .setPaymentCard(dto.getPaymentCard()).setPassword(parsePassword)
                .setOrders(orders).addRole(dto.isAdmin() ? roleService.getAdminRole() : roleService.getUserRole());
    }

    @Mapping(source = "admin", target = "isAdmin")
    public abstract UserDTO mapToDto(UserDTO_REST userRest);

    public abstract UserDTO_REST mapToRest(User user);
}