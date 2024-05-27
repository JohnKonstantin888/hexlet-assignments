package exercise.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder(toBuilder = true)
public class ProductDTO {
    private long id;
    private String title;
    private int price;
    private long vendorCode;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
