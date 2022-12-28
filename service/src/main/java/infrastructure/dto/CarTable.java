package infrastructure.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarTable {

    private long id;

    private String model;

    private String brand;

    private String number;

    private String price;
}
