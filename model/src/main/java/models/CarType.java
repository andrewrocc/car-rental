package models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "T_CARS_TYPE")
public class CarType {

	@Id
	@Column(name = "CT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "CT_NAME")
	private String typeName;
}
