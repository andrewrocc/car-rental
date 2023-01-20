package infrastructure.model;

import lombok.*;

//import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

//@Getter
//@Setter
//@Entity
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "PAYMENT")
@Deprecated
public class Payment implements Serializable {
//
//	@Id
//	@Column(name = "ID")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private long id;
//
////	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "DATETIME")
//	private Timestamp dateTimePayment;
//
//	@ManyToMany(mappedBy = "payments")
//	@ToString.Exclude
//	private Set<Order> orders;
//
//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (o == null || getClass() != o.getClass()) return false;
//
//		Payment payment = (Payment) o;
//
//		if (id != payment.id) return false;
//		return Objects.equals(dateTimePayment, payment.dateTimePayment);
//	}
//
//	@Override
//	public int hashCode() {
//		int result = (int) (id ^ (id >>> 32));
//		result = 31 * result + (dateTimePayment != null ? dateTimePayment.hashCode() : 0);
//		return result;
//	}
}
