package exercise.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Getter
@Setter
@Builder(toBuilder = true)
public class CommentDTO {
    private long id;
    private String body;
}
// END
