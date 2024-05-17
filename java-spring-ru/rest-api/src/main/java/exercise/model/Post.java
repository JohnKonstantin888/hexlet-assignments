package exercise.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Accessors(chain = true)
@Setter
@Getter
public class Post {
    private int userId;
    private String slug;
    private String title;
    private String body;
}
