package infrastructure.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "CAR_MODEL")
public class CarModel implements Serializable {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "NAME")
	private String modelName;

	// TODO required to delete this
	@Column(name = "BRAND_ID")
	private long carBrandId;

	@ManyToOne
	@JoinColumn(name = "BRAND_ID", insertable = false, updatable = false)
	private CarBrand carBrand;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CarModel carModel = (CarModel) o;

		if (id != carModel.id) return false;
		if (carBrandId != carModel.carBrandId) return false;
		if (!Objects.equals(modelName, carModel.modelName)) return false;
		return Objects.equals(carBrand, carModel.carBrand);
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (modelName != null ? modelName.hashCode() : 0);
		result = 31 * result + (int) (carBrandId ^ (carBrandId >>> 32));
		result = 31 * result + (carBrand != null ? carBrand.hashCode() : 0);
		return result;
	}
}
