package exercise.daytime;
import jakarta.annotation.PostConstruct;
import lombok.Getter;

@Getter
public class Day implements Daytime {
    private final String name = "day";

    // BEGIN
    @PostConstruct
    public void postConstruct() {
        System.out.println("Bean Day was created");
    }
    // END
}
