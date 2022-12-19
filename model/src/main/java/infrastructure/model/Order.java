package infrastructure.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "BOOKING")
public class Order implements Serializable {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "PRICE")
	private BigDecimal price;

	@Column(name = "DATETIME")
	private Timestamp dateTime;

	@ManyToOne
	@JoinColumn(name = "CAR_ID")
	private Car car;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "BOOKING_PAYMENT",
			joinColumns = @JoinColumn(name = "BOOKING_ID"),
			inverseJoinColumns = @JoinColumn(name = "PAYMENT_ID"))
	@ToString.Exclude
	private Set<Payment> payments;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "USER_BOOKING",
			joinColumns = @JoinColumn(name = "BOOKING_ID"),
			inverseJoinColumns = @JoinColumn(name = "USER_ID"))
	@ToString.Exclude
	private Set<User> users;


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Order order = (Order) o;

		if (id != order.id) return false;
		if (!Objects.equals(price, order.price)) return false;
		if (!Objects.equals(dateTime, order.dateTime)) return false;
		if (!Objects.equals(car, order.car)) return false;
		if (!Objects.equals(payments, order.payments)) return false;
		return Objects.equals(users, order.users);
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (price != null ? price.hashCode() : 0);
		result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
		result = 31 * result + (car != null ? car.hashCode() : 0);
		result = 31 * result + (payments != null ? payments.hashCode() : 0);
		result = 31 * result + (users != null ? users.hashCode() : 0);
		return result;
	}
}
