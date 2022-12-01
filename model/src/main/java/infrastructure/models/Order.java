package infrastructure.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "T_ORDERS")
public class Order {

	@Id
	@Column(name = "O_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "O_PRICE")
	private BigDecimal price;

	@Column(name = "O_DATETIME")
	private Timestamp dateTime;

	@Column(name = "O_CAR_ID")
	private long carId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "O_ID", insertable = false, updatable = false)
	private Car car;

	@OneToMany(mappedBy = "order")
	private Set<M2M_OrderPayment> orderPayment;

	@OneToMany(mappedBy = "order")
	private Set<M2M_UserOrder> userOrder;
}
