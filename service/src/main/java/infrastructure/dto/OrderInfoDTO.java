package infrastructure.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderInfoDTO {

    private long id;

    private String model;

    private String brand;

    private String number;

    private String price;
}
