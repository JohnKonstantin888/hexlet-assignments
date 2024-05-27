package exercise.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Getter
@Setter
@Builder(toBuilder = true)
public class ProductUpdateDTO {
    private String title;
    private int price;
}
// END
