package infrastructure.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "T_CARS")
public class Car {

	@Id
	@Column(name = "C_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "C_NUMBER")
	private String numberCar;

	@Column(name = "C_BRAND_ID")
	private long carBrandId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "C_ID", insertable = false, updatable = false)
	private CarBrand carBrand;

	@OneToMany(mappedBy = "car")
	private Set<Order> order;
}
