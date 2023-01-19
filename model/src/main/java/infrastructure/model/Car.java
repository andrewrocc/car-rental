package infrastructure.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CAR")
public class Car implements Serializable {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "NUMBER")
	private String numberCar;

	@Column(name = "PRICE")
	private BigDecimal price;

	@ManyToOne
	@JoinColumn(name = "BRAND_ID")
	private CarBrand carBrand;

	@ManyToOne
	@JoinColumn(name = "MODEL_ID")
	private CarModel carModel;

	@ToString.Exclude
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "BOOKING_CAR",
			joinColumns = @JoinColumn(name = "CAR_ID"),
			inverseJoinColumns = @JoinColumn(name = "BOOKING_ID"))
	private Set<Order> orders = new HashSet<>();

	@ToString.Exclude
	@OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
	private CarPhoto photo;

	public void removeOrder(Order order) {
		this.orders.remove(order);
		order.getCars().remove(this);
	}

	//region eq & hash
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Car car = (Car) o;

		if (id != car.id) return false;
		if (!Objects.equals(numberCar, car.numberCar)) return false;
		if (!Objects.equals(price, car.price)) return false;
		if (!Objects.equals(carBrand, car.carBrand)) return false;
		if (!Objects.equals(carModel, car.carModel)) return false;
		if (!Objects.equals(orders, car.orders)) return false;
		return Objects.equals(photo, car.photo);
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (numberCar != null ? numberCar.hashCode() : 0);
		result = 31 * result + (price != null ? price.hashCode() : 0);
//		result = 31 * result + (carBrand != null ? carBrand.hashCode() : 0);
//		result = 31 * result + (carModel != null ? carModel.hashCode() : 0);
		result = 31 * result + (orders != null ? orders.hashCode() : 0);
//		result = 31 * result + (photo != null ? photo.hashCode() : 0);
		return result;
	}
	//endregion
}
