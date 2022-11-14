package models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "t_cars_brand")
public class CarBrand {

	@Id
	@Column(name = "CB_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "CB_NAME")
	private String brandName;

	@OneToMany(mappedBy = "carBrand", fetch = FetchType.EAGER)
	private Set<Car> car;
}
