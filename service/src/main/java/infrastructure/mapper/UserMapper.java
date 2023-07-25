package infrastructure.mapper;

import infrastructure.dto.OrderDto;
import infrastructure.dto.UserDto;
import infrastructure.dto.UserDtoRest;
import infrastructure.dto.UserSignUpDto;
import infrastructure.model.Order;
import infrastructure.model.User;
import infrastructure.service.RoleService;
import infrastructure.util.Utils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Mapper(config = MappingConfiguration.class)
public abstract class UserMapper {

    @Autowired
    private RoleService roleService;

    @Autowired
    private OrderMapper orderMapper;

    public UserDto mapToDto(User user) {
        return builder(user).setOrders(user.getOrders().toArray(Order[]::new));
    }

    public UserDto mapToDtoByOrderDto(User user) {
        return builder(user).setOrderDtos(Arrays.stream(user.getAllOrders())
                                                  .map(orderMapper::mapToDto)
                                                  .toArray(OrderDto[]::new));
    }

    private UserDto builder(User user) {
        UserDto dto = new UserDto();
        return dto.setId(user.getId()).setFirstName(user.getFirstName())
                .setLastName(user.getLastName()).setEmail(user.getEmail())
                .setPaymentCard(user.getPaymentCard()).setPassword(user.getPassword())
                .setAdmin(Utils.hasAdminRole(user.getRoles()));
    }

    // TODO set up password security
    public User mapToUser(UserDto dto) {
        User user = new User();
        String parsePassword = dto.getPassword().contains("{noop}") ? dto.getPassword() :"{noop}" + dto.getPassword();
        HashSet<Order> orders = Objects.isNull(dto.getOrders()) ? null : new HashSet<>(List.of(dto.getOrders()));
        return user.setId(dto.getId()).setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName()).setEmail(dto.getEmail())
                .setPaymentCard(dto.getPaymentCard()).setPassword(parsePassword)
                .setOrders(orders).addRole(dto.isAdmin() ? roleService.getAdminRole() : roleService.getUserRole());
    }

    @Mapping(source = "admin", target = "isAdmin")
    public abstract UserDto mapToDto(UserDtoRest userRest);

    public abstract UserDtoRest mapToRest(User user);

    public abstract UserDto mapToDto(UserSignUpDto userDto);
}