package models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "t_users")
public class User {

	@Id
	@Column(name = "U_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "U_USERNAME")
	private String name;

	@Column(name = "U_USEREMAIL")
	private String email;

	@Column(name = "U_PAYMENT_CARD")
	private String paymentCard;
}
