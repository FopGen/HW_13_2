import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {

    private Integer userId;
    private Integer id;
    private String title;
    private String completed;

    public Task(Integer userId, Integer id, String title, String completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public Task() {
    }

    @Override
    public String toString() {
        return "Task #" + id +
                ", title= " + title ;
    }
}
