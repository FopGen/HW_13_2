import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        String uriPosts = "https://jsonplaceholder.typicode.com/users/1/posts";
        String uriComments = "https://jsonplaceholder.typicode.com/posts";
        List<Post> posts = new ArrayList<>();

        int id = getIdFinalPost(uriPosts, posts);
        getListComments(uriComments, id);

    }

    private static void getListComments(String uriComments, int id) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest httpRequestGetComment = (HttpRequest) HttpRequest.newBuilder(new URI(uriComments+"/"+id+"/comments"))
                .header("Content-Type", "application/json")
                .GET()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpClient httpClientGet = HttpClient.newHttpClient();
        HttpResponse responceListComment = httpClientGet.send(httpRequestGetComment, HttpResponse.BodyHandlers.ofString());

        String bodyResponce = (String) responceListComment.body();

        FileWriter writer = new FileWriter("user-X-post-Y-comments.json");
        writer.write(bodyResponce);
        writer.flush();

        
    }

    public static int getIdFinalPost(String uri, List<Post> posts) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest httpRequestGet = HttpRequest.newBuilder(new URI(uri))
                .header("Content-Type", "application/json")
                .GET()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpClient httpClientGet = HttpClient.newHttpClient();
        HttpResponse responceGet = httpClientGet.send(httpRequestGet, HttpResponse.BodyHandlers.ofString());

        String bodyResponce = (String) responceGet.body();
        ObjectMapper mapper = new ObjectMapper();
        List<Post> postsLoaded= new ArrayList<>();
        postsLoaded = Arrays.asList(mapper.readValue(bodyResponce, Post[].class));

        int finalPostId = 0;
        for (Post post:postsLoaded){
            if(post.getId()>finalPostId){
                finalPostId = post.getId();
            }
        }
        return finalPostId;

        }
}