package infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "CAR_PHOTO")
public class CarPhoto {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @OneToOne
    @JoinColumn(name = "CAR_ID")
    private Car car;

    @Lob
    @Column(name = "CAR_PHOTO", columnDefinition = "MEDIUMBLOB NOT NULL")
    private byte[] photo;

    //region equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarPhoto carPhoto = (CarPhoto) o;

        if (!Objects.equals(id, carPhoto.id)) return false;
        if (!Objects.equals(car, carPhoto.car)) return false;
        return Arrays.equals(photo, carPhoto.photo);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (car != null ? car.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }
    //endregion
}
