package models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "T_ROLES")
public class Role {

	@Id
	@Column(name = "R_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "R_NAME")
	private String name;

	@Column(name = "R_DESCRIPTION")
	private String description;
}
