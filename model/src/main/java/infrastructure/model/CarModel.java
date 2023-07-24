package infrastructure.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "CAR_MODEL")
public class CarModel implements Serializable {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "NAME")
	private String modelName;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BRAND_ID")
	private CarBrand carBrand;

	//region equals & hashCode
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CarModel carModel = (CarModel) o;

		if (id != carModel.id) return false;
		if (!Objects.equals(modelName, carModel.modelName)) return false;
		return Objects.equals(carBrand, carModel.carBrand);
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (modelName != null ? modelName.hashCode() : 0);
		result = 31 * result + (carBrand != null ? carBrand.hashCode() : 0);
		return result;
	}
	//endregion
}
