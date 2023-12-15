package Services;



import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class PostService {
    static String BASE_URL = "https://jsonplaceholder.typicode.com";
    static String BASE_PATH = "/posts";
    static RequestSpecification req_spec;

    public PostService() {
        req_spec = given().baseUri(BASE_URL).basePath(BASE_PATH);
    }


    public Response getposts(){
        return given().spec(req_spec).when().get();
    }

    public Response createposts(){
        return given().spec(req_spec).contentType(ContentType.JSON).body("""
                        {
                             "userId": 1,
                             "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                             "body": "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"
                         }""")
                .when().post();
    }

    public Response getpostsById(int Id){
        return given().spec(req_spec)
                .when().get(String.valueOf(Id));
    }

    public Response putpostsById(int Id){
        return given().spec(req_spec).contentType(ContentType.JSON).body("""
                        {
                             "userId": 1,
                             "id": 1,
                             "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                             "body": "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"
                         }""")
                .when().get(String.valueOf(Id));
    }

    public Response deletepostsById(int Id){
        return given().spec(req_spec)
                .when().delete(String.valueOf(Id));
    }

    public Response filterPostByUserId(int Id) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("userId", String.valueOf(Id));
        return given().spec(req_spec).when().queryParams(queryParams).get(BASE_URL + BASE_PATH);
    }

    public Response getAllCommentsOfPostById(int Id) {
        return given().spec(req_spec)
                .when().get(Id + "/comments");
    }


}
