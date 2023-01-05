package infrastructure.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDTO {

    private long id;

    private String price;

    private String date;

    private String numberOfDay;

    private String brand;

    private String model;

    private String login;
}
