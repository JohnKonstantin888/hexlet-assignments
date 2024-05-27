package exercise.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Getter
@Setter
@Builder(toBuilder = true)
public class PostDTO {
    private long id;
    private String title;
    private String body;
    private List<CommentDTO> comments;
}
// END
