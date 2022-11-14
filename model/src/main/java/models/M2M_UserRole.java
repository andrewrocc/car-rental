package models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "T_M2M_USERS_ROLES")
public class M2M_UserRole {

	@Id
	@Column(name = "U_ID")
	private long userId;

	@Column(name = "R_ID")
	private long RoleId;

	@ManyToOne
	@JoinColumn(name = "U_ID", insertable = false, updatable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "R_ID", insertable = false, updatable = false)
	private Role role;
}
