package infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
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
@Table(name = "CAR_BRAND")
public class CarBrand implements Serializable {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "NAME")
	private String brandName;

	@OneToMany(mappedBy = "carBrand")
	@ToString.Exclude
	private Set<Car> car;

	@OneToMany(mappedBy = "carBrand")
	@ToString.Exclude
	private Set<CarModel> carModels;


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CarBrand carBrand = (CarBrand) o;

		if (id != carBrand.id) return false;
		if (!Objects.equals(brandName, carBrand.brandName)) return false;
		if (!Objects.equals(car, carBrand.car)) return false;
		return Objects.equals(carModels, carBrand.carModels);
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (brandName != null ? brandName.hashCode() : 0);
		result = 31 * result + (car != null ? car.hashCode() : 0);
		result = 31 * result + (carModels != null ? carModels.hashCode() : 0);
		return result;
	}
}
