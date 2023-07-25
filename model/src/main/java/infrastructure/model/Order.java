package infrastructure.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
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
@Accessors(chain = true)
@Table(name = "BOOKING")
public class Order implements Serializable {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "PRICE")
	private BigDecimal price;

	@Column(name = "DATE")
	private LocalDate date;

	@Column(name = "NUMBER_OF_DAY")
	private int numberOfDay;

	@ManyToMany(mappedBy = "orders")
	@ToString.Exclude
	@Builder.Default
	private Set<Car> cars = new HashSet<>();

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "USER_BOOKING",
			joinColumns = @JoinColumn(name = "BOOKING_ID"),
			inverseJoinColumns = @JoinColumn(name = "USER_ID"))
	@ToString.Exclude
	@Builder.Default
	private Set<User> users = new HashSet<>();

	public void addCar(Car c) {
		cars.add(c);
		c.getOrders().add(this);
	}

	public void addUser(User u) {
		users.add(u);
		u.getOrders().add(this);
	}

	public Car[] getAllCars() {
		return cars.toArray(new Car[cars.size()]);
	}

	public User[] getAllUsers() {
		return users.toArray(new User[users.size()]);
	}

	//region equals & hashCode
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Order order = (Order) o;

		if (id != order.id) return false;
		if (!Objects.equals(price, order.price)) return false;
		if (!Objects.equals(date, order.date)) return false;
		if (!Objects.equals(cars, order.cars)) return false;
		return Objects.equals(users, order.users);
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (price != null ? price.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
//		result = 31 * result + (cars != null ? cars.hashCode() : 0);
//		result = 31 * result + (users != null ? users.hashCode() : 0);
		return result;
	}
	//endregion
}
