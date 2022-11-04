package models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
}
