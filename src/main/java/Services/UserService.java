package Services;



import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;


public class UserService {
    static String BASE_URL = "https://jsonplaceholder.typicode.com";
    static String BASE_PATH = "/users";
    static RequestSpecification req_spec;


    public UserService() {

        req_spec = given().baseUri(BASE_URL).basePath(BASE_PATH);

    }


    public Response getUsers(){

        return given().spec(req_spec).when().get();

    }

    public Response postUsers(){
        return given().spec(req_spec).contentType(ContentType.JSON).body("""
                        {
                            "name": "Jason Kennedy",
                            "username": "Jac",
                            "email": "Jkennedy@february.biz",
                            "address": {
                                "street": "Vincent Street",
                                "suite": "Apt. 302",
                                "city": "Amsterdam",
                                "zipcode": "45123",
                                "geo": {
                                    "lat": "-41.3159",
                                    "lng": "11.1496"
                                }
                            },
                            "phone": "1-567-234-729",
                            "website": "jasonk.org",
                            "company": {
                                "name": "Jason-Trades",
                                "catchPhrase": "Multi-layered client-server neural-net",
                                "bs": "harness real-time e-markets"
                            }
                        }""")
                .when().post();
    }

    public Response getUsersById(int Id){
        return given().spec(req_spec)
                .when().get(String.valueOf(Id));
    }

    public Response putUsersById(int Id){
        return given().spec(req_spec).contentType(ContentType.JSON).body("""
                        {
                            "name": "Michael Simmens",
                            "username": "Mic",
                            "email": "Michael@february.biz",
                            "address": {
                                "street": "32 Street",
                                "suite": "Apt. 127",
                                "city": "Illinois",
                                "zipcode": "45169",
                                "geo": {
                                    "lat": "-21.3159",
                                    "lng": "91.1496"
                                }
                            },
                            "phone": "1-770-736-8031 x56442",
                            "website": "micrafd.org",
                            "company": {
                                "name": "Simmens-Crona",
                                "catchPhrase": "Multi-layered client-server neural-net",
                                "bs": "harness real-time e-markets"
                            }
                        }""")
                .when().get(String.valueOf(Id));
    }

    public Response deleteUsersById(int Id){
        return given().spec(req_spec)
                .when().delete(String.valueOf(Id));
    }

    public Response getPostsbyUserId(int Id) {

        return given().spec(req_spec)
                .when().get(Id + "/posts");
    }

    public Response getTodosbyUserId(int Id) {

        return given().spec(req_spec)
                .when().get(Id + "/todos");
    }
}
