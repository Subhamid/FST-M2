package LiveProject;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.oauth2;

public class GitHubProject {
    RequestSpecification requestspec;
    String sshkey = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCQaOR/reM256EPeKEtm7/yCMVY1bCD1J26BK/13vBrgYJ0D2Xb+GKXuNAx7FA5WtqhYlbvENqrVAsTZzj+yQuDgpZKmpu17I/+vT0jUDi4O3c6qd1yj6ZYpu8jGWF+pFEri2vBawwDkasb07STzuxWCAlOQDq5ygLoKCXTMt/ofpGhT0bbZg9fOlfsBHNojgS025IgnolFfZagVDRpBZinUE4H2yfja797coGyny0dOnr5xCsbNcw8l6zusLJb9qtQahOme5ssBM7A8zlQ2pMucuvBoV4lOD9E/G4xW8dP3pjlO2P1UWtXaJgSbr13xpUv1MF+4M7rctgQYa3Ml3l3";
    int id;

    @BeforeClass
    public void setUp() {
        requestspec = new RequestSpecBuilder()
                .setBaseUri("https://api.github.com")
                .setContentType(ContentType.JSON)
                .setAuth(oauth2("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"))
                .build();

    }

    @Test(priority = 1)
    public void addKey() {
        Map<String, String> reqBody = new HashMap<String, String>();
        reqBody.put("title", "TestAPIKey");
        reqBody.put("key", sshkey);
        Response response = given().spec(requestspec)
                .body(reqBody)
                .when().post("/user/keys");
        id = response.then().extract().path("id");

        System.out.println("The id of the key is : " + id);
        System.out.println("The status received: " + response.statusLine());
        response.then().statusCode(201);
    }
    @Test(priority = 2)
    public void getKey() {
        Response response =  given().spec(requestspec).pathParam("keyId", id).
                when().get("/user/keys/{keyId}");
        System.out.println(response.asPrettyString());
        response.then().statusCode(200);
    }
    @Test(priority = 3)
    public void deleteKey() {
        Response response =  given().spec(requestspec).pathParam("keyId", id).
                when().delete("/user/keys/{keyId}");
        System.out.println(response.asPrettyString());
        response.then().statusCode(204);
    }


}
