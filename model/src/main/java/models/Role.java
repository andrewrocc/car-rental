package models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

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

	@OneToMany(mappedBy = "role")
	private Set<Permission> permission;

	@OneToMany(mappedBy = "role")
	private Set<M2M_UserRole> userRoles;
}
