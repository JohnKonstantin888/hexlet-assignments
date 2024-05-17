package exercise.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder(toBuilder = true)
public class PostDTO {
    private int userId;
    private String slug;
    private String title;
    private String body;
}
