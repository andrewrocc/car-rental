package infrastructure.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ROLE")
public class Role implements Serializable {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@OneToMany(mappedBy = "role")
	@ToString.Exclude
	private Set<Permission> permission;

	@ManyToMany(mappedBy = "roles")
	@ToString.Exclude
	private Set<User> users = new HashSet<>();

	//region eq & hashC
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Role role = (Role) o;

		if (id != role.id) return false;
		if (!Objects.equals(name, role.name)) return false;
		if (!Objects.equals(description, role.description)) return false;
		return Objects.equals(permission, role.permission);
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
//		result = 31 * result + (permission != null ? permission.hashCode() : 0);
		return result;
	}
	//endregion
}
