package infrastructure.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
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

	@Column(name = "BRAND_ID")
	private long carBrandId;

	@ManyToOne
	@JoinColumn(name = "BRAND_ID", insertable = false, updatable = false)
	private CarBrand carBrand;

	@OneToMany(mappedBy = "car")
	@ToString.Exclude
	private Set<Order> order;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Car car = (Car) o;

		if (id != car.id) return false;
		if (carBrandId != car.carBrandId) return false;
		if (!Objects.equals(numberCar, car.numberCar)) return false;
		if (!Objects.equals(carBrand, car.carBrand)) return false;
		return Objects.equals(order, car.order);
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (numberCar != null ? numberCar.hashCode() : 0);
		result = 31 * result + (int) (carBrandId ^ (carBrandId >>> 32));
		result = 31 * result + (carBrand != null ? carBrand.hashCode() : 0);
		result = 31 * result + (order != null ? order.hashCode() : 0);
		return result;
	}
}
