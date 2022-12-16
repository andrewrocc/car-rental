package infrastructure.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "USER")
public class User implements Serializable {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "USERNAME")
	private String name;

	@Column(name = "USEREMAIL")
	private String email;

	@Column(name = "PAYMENT_CARD")
	private String paymentCard;

	@ManyToMany(mappedBy = "users")
	@ToString.Exclude
	private Set<Order> orders;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLE",
			joinColumns = @JoinColumn(name = "USER_ID"),
			inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	@ToString.Exclude
	private Set<Role> roles;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (id != user.id) return false;
		if (!Objects.equals(name, user.name)) return false;
		if (!Objects.equals(email, user.email)) return false;
		return Objects.equals(paymentCard, user.paymentCard);
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (paymentCard != null ? paymentCard.hashCode() : 0);
		return result;
	}
}
