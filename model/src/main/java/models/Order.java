package models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

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

	@Column(name = "O_USER_ID")
	private long userId;

	@Column(name = "O_CAR_ID")
	private long carId;

	@Column(name = "O_PAYMENT_ID")
	private long paymentId;

	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private User user;

	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;

	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Car car;
}
