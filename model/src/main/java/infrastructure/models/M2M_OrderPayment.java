package infrastructure.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "T_M2M_ORDERS_PAYMENTS")
public class M2M_OrderPayment {

	@Id
	@Column(name = "O_ID")
	private long orderId;

	@Column(name = "P_ID")
	private long paymentId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "P_ID", insertable = false, updatable = false)
	private Payment payment;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "O_ID", insertable = false, updatable = false)
	private Order order;
}
