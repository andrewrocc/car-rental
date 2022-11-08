package models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

	@Column(name = "C_MODEL_ID")
	private long carModelId;

	@Column(name = "C_TYPE_ID")
	private long carTypeId;

	@OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
	private CarType carType;

	@OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
	private CarModel carModel;

	@OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
	private CarBrand carBrand;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Order order;
}
