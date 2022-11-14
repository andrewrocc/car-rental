package models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "T_PERMISSIONS")
public class Permission {

	@Id
	@Column(name = "PRMS_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "PRMS_NAME")
	private String name;

	@Column(name = "PRMS_R_ID")
	private long roleId;
}
