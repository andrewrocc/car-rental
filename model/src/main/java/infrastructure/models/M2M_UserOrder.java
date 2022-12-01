package infrastructure.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "T_M2M_USERS_ORDERS")
public class M2M_UserOrder {

	@Id
	@Column(name = "U_ID")
	private long userId;

	@Column(name = "O_ID")
	private long orderId;

	@ManyToOne
	@JoinColumn(name = "U_ID", nullable = false, insertable = false, updatable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "O_ID", nullable = false, insertable = false, updatable = false)
	private Order order;
}
