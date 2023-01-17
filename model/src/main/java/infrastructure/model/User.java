package infrastructure.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER")
public class User implements Serializable {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "FIRSTNAME")
	private String firstName;

	@Column(name = "LASTNAME")
	private String lastName;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PAYMENT_CARD")
	private String paymentCard;

	@Column(name = "PASSWORD")
	private String password;

	@ManyToMany(mappedBy = "users")
	@ToString.Exclude
	private Set<Order> orders = new HashSet<>();

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLE",
			joinColumns = @JoinColumn(name = "USER_ID"),
			inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	@ToString.Exclude
	@Builder.Default
	private Set<Role> roles = new HashSet<>();

	public Role[] getAllRoles() {
		return roles.toArray(new Role[roles.size()]);
	}

	public Order[] getAllOrders() {
		return orders.toArray(new Order[orders.size()]);
	}

	public void addRole(Role r) {
		roles.add(r);
		r.getUsers().add(this);
	}

	//region eq & hashCode
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (id != user.id) return false;
		if (!Objects.equals(firstName, user.firstName)) return false;
		if (!Objects.equals(lastName, user.lastName)) return false;
		if (!Objects.equals(email, user.email)) return false;
		if (!Objects.equals(paymentCard, user.paymentCard)) return false;
		if (!Objects.equals(password, user.password)) return false;
		if (!Objects.equals(orders, user.orders)) return false;
		return Objects.equals(roles, user.roles);
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (paymentCard != null ? paymentCard.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
//		result = 31 * result + (orders != null ? orders.hashCode() : 0);
		result = 31 * result + (roles != null ? roles.hashCode() : 0);
		return result;
	}
	//endregion
}
