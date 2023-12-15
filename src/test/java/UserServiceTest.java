import Models.PostObj;
import Models.UserObj;
import Models.TodosObj;
import Services.UserService;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.Arrays;

import static io.restassured.RestAssured.expect;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserServiceTest {
    static ResponseSpecification res_spec;
    UserService userService_obj = new UserService();



    @Test
    public void getUsersTest(){

        Response response = userService_obj.getUsers().then().log().all().extract().response();
        UserObj[] userObj = response.as(UserObj[].class);
        assertThat(response.statusCode(), is(200));
    }

    @Test
    public void postUsersTest(){
        Response response =userService_obj.postUsers().then().log().all().extract().response();
        UserObj userObj = response.as(UserObj.class);
        assertThat(response.statusCode(), is(201));
        assertThat(String.valueOf(userObj.id), is(notNullValue()));
        assertThat(userObj.name, is(notNullValue()));
        assertThat(userObj.username, is(notNullValue()));
        assertThat(userObj.email, is(notNullValue()));
        assertThat(userObj.address, is(notNullValue()));
        assertThat(userObj.phone, is(notNullValue()));
        assertThat(userObj.company, is(notNullValue()));
    }

    @Test
    public void getUsersByIdTest(){
        Response response = userService_obj.getUsersById(1).then().extract().response();
        UserObj userObj = response.as(UserObj.class);
        assertThat(response.statusCode(), is(200));
        assertThat(String.valueOf(userObj.id), is(equalTo("1")));
        assertThat(userObj.name, is(notNullValue()));
        assertThat(userObj.username, is(notNullValue()));
        assertThat(userObj.email, is(notNullValue()));
        assertThat(userObj.address, is(notNullValue()));
        assertThat(userObj.phone, is(notNullValue()));
        assertThat(userObj.company, is(notNullValue()));
    }

    @Test
    public void putUsersByIdTest(){
        Response response = userService_obj.putUsersById(10).then().log().body()
        .extract().response();
        UserObj userObj = response.as(UserObj.class);
        assertThat(response.statusCode(), is(200));

    }

    @Test
    public void deleteUsersByIdTest(){
        Response response = userService_obj.deleteUsersById(3).then()
                .extract().response();
        UserObj userObj = response.as(UserObj.class);
        assertThat(response.statusCode(), is(200));

    }

    @Test
    public void getPostsbyUserId() {
        Response response = userService_obj.getPostsbyUserId(1)
                .then().log().all().extract().response();
        PostObj[] postObj = response.as(PostObj[].class);

        for (int i = 0; i < Arrays.asList(postObj).size(); i++) {
            assertThat((postObj[i].userId), is(equalTo(1)));
            assertThat(String.valueOf(postObj[i].id), is(notNullValue()));
            assertThat(postObj[i].title, is(notNullValue()));
            assertThat(postObj[i].body, is(notNullValue()));
        }
    }

    @Test
    public void getTodosbyUserId() {
        Response response = userService_obj.getTodosbyUserId(1)
                .then().log().all().extract().response();
        TodosObj[] todosObj = response.as(TodosObj[].class);

        for (int i = 0; i < Arrays.asList(todosObj).size(); i++) {
            assertThat((todosObj[i].userId), is(equalTo(1)));
            assertThat(String.valueOf(todosObj[i].id), is(notNullValue()));
            assertThat(todosObj[i].title, is(notNullValue()));
            assertThat(todosObj[i].completed, is(notNullValue()));
        }
    }

}