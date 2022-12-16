package infrastructure.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "PERMISSION")
public class Permission implements Serializable {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ROLE_ID")
	private long roleId;

	@ManyToOne
	@JoinColumn(name = "ROLE_ID", insertable = false, updatable = false)
	private Role role;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Permission that = (Permission) o;

		if (id != that.id) return false;
		if (roleId != that.roleId) return false;
		if (!Objects.equals(name, that.name)) return false;
		return Objects.equals(role, that.role);
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (int) (roleId ^ (roleId >>> 32));
		result = 31 * result + (role != null ? role.hashCode() : 0);
		return result;
	}
}
