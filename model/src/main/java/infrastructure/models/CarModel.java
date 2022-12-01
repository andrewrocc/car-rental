package infrastructure.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "T_CARS_MODEL")
public class CarModel {

	@Id
	@Column(name = "CM_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "CM_NAME")
	private String modelName;

	@Column(name = "CM_CBID")
	private long carBrandId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CM_CBID", insertable = false, updatable = false)
	private CarBrand carBrands;
}
