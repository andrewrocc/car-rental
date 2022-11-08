package models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "T_PAYMENTS")
public class Payment {

	@Id
	@Column(name = "P_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "P_DATETIME")
	private Timestamp dtPayment;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Order order;
}
