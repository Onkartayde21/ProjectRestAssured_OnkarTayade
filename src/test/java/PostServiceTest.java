import Models.PostObj;
import Models.CommentObj;

import Services.PostService;

import org.testng.asserts.SoftAssert;

import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

import org.testng.annotations.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PostServiceTest {

    SoftAssert softAssert = new SoftAssert();
    static ResponseSpecification res_spec;
    PostService postService_obj = new PostService();


    @Test
    public void getPostsTest(){

        Response response = postService_obj.getposts().then().log().all().extract().response();
        PostObj[] postObj = response.as(PostObj[].class);
        assertThat(response.statusCode(), is(200));
        for (int i = 0; i < Arrays.asList(postObj).size(); i++) {
            assertThat(String.valueOf(postObj[i].userId), is(notNullValue()));
            assertThat(postObj[i].id,is(equalTo(i +1)));
            assertThat(postObj[i].title, is(notNullValue()));
            assertThat(postObj[i].body, is(notNullValue()));
        }
    }

    @Test
    public void createtPostsTest(){
        Response response =postService_obj.createposts().then().log().body().extract().response();
        PostObj postObj = response.as(PostObj.class);
        assertThat(response.statusCode(), is(201));
        assertThat(postObj.userId, is(equalTo(1)));
        assertThat(String.valueOf(postObj.id), is(notNullValue()));
        assertThat(postObj.title, is(notNullValue()));
        assertThat(postObj.body, is(notNullValue()));


    }

    @Test
    public void getPostsByIdTest(){
        Response response = postService_obj.getpostsById(1).then().extract().response();
        PostObj postObj = response.as(PostObj.class);
        assertThat(response.statusCode(), is(200));
        assertThat(String.valueOf(postObj.userId), is(notNullValue()));
        assertThat(postObj.id, is(equalTo(1)));
        assertThat(postObj.title, is(notNullValue()));
        assertThat(postObj.body, is(notNullValue()));
    }

    @Test
    public void putPostsByIdTest(){
        Response response = postService_obj.putpostsById(1).then().log().body()
                .extract().response();
        PostObj postObj = response.as(PostObj.class);
        assertThat(response.statusCode(), is(200));
        assertThat(String.valueOf(postObj.userId), is(notNullValue()));
        assertThat(postObj.id, is(equalTo(1)));
        assertThat(postObj.title, is(notNullValue()));
        assertThat(postObj.body, is(notNullValue()));
    }

    @Test
    public void deletePostsByIdTest(){
        Response response = postService_obj.deletepostsById(3).then()
                .extract().response();
        PostObj postObj = response.as(PostObj.class);
        assertThat(response.statusCode(), is(200));

    }

    @Test
    public void getPostsbyUserId(){

        Response response = postService_obj.filterPostByUserId(3).then()
                .log().all().extract().response();
        PostObj[] postObj = response.as(PostObj[].class);
        assertThat(response.statusCode(), is(200));
        for(int i=0; i < Arrays.asList(postObj).size(); i++){
            assertThat(postObj[i].userId, is(equalTo(3)));
            assertThat(String.valueOf(postObj[i].id), is(notNullValue()));
            assertThat(postObj[i].title, is(notNullValue()));
            assertThat(postObj[i].body, is(notNullValue()));
        }
    }

    @Test
    public void getAllCommentsOfPost() {
        Response response = postService_obj.getAllCommentsOfPostById(1).then()
                .log().all().extract().response();
        CommentObj[] commentObj = response.as(CommentObj[].class);

        assertThat(response.statusCode(), is(200));
        for (int i = 0; i < Arrays.asList(commentObj).size(); i++) {
            assertThat(String.valueOf(commentObj[i].id), is(notNullValue()));
            assertThat(commentObj[i].postId, is(equalTo(1)));
            assertThat(commentObj[i].name, is(notNullValue()));
            assertThat(commentObj[i].email, is(notNullValue()));
            assertThat(commentObj[i].body, is(notNullValue()));
        }

    }

}