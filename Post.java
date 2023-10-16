import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {
    private Integer id;
    private String title;
    private String body;

    public Post(Integer id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Post() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
