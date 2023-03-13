package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static io.restassured.RestAssured.given;

public class StepDefinition {

    private final Logger log = LoggerFactory.getLogger(StepDefinition.class);

    @Given("get all employees")
    public void TestReturnAllEmployees() {

        Response response;

        // Set the base URI for the API
        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
        response = RestAssured.given().get("/employees");

        try {
            List<LinkedHashMap<String, Object>> list = response.jsonPath().getJsonObject("data");
            List<Employee> newList = new ArrayList<>();

            for (LinkedHashMap<String, Object> singleMap : list) {
                newList.add(new Employee((Integer) singleMap.get("id"),
                        String.valueOf(singleMap.get("employee_name")),
                        (Integer) singleMap.get("employee_salary"),
                        (Integer) singleMap.get("employee_age"),
                        String.valueOf(singleMap.get("profile_image"))));

            }
            for (Employee employee : newList) {
                System.out.println(employee.toString());
            }

            Assert.assertEquals(newList.size(), 24);
        } catch (Exception e) {
            log.error("Exception thrown " + e);
        }

        printAndAssert(response,"GET");

    }

    @When("I add a employee")
    public void TestAddEmployee(DataTable dataTable) throws JsonProcessingException {
        Response response;
        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
        //RestAssured.basePath = "create";
        List<String> data = dataTable.asList();

        Map<String, String> body = new HashMap<>();
        body.put("name", data.get(0));
        body.put("salary", data.get(1));
        body.put("age", data.get(2));

        //JSONObject json =  new JSONObject(body);
        String json = new ObjectMapper().writeValueAsString(body);

        RequestSpecification request = given().body(json);
        response = request.when().post("/create");

        //if the ResponseCode is 429 == Too many requests
        printAndAssert(response,"POST");
    }

    @When("I update employee id={int} with values")
    public void iUpdateEmployeeId(int arg0,DataTable dataTable) throws JsonProcessingException {
        Response response;
        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";

        //RestAssured.basePath = "create";
        List<String> data = dataTable.asList();
        Map<String, String> body = new HashMap<>();
        body.put("name", data.get(0));
        body.put("salary", data.get(1));
        body.put("age", data.get(2));

        String json = new ObjectMapper().writeValueAsString(body);

        RequestSpecification request = given().body(json);
        response = request.when().put("/update/{id}",arg0);

        //if the ResponseCode is 429 == Too many requests
        printAndAssert(response,"PUT");
    }

    @Then("I delete employee id={int}")
    public void iDeleteEmployeeId(int arg0) {
        Response response;
        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";

        response = given().when().delete("/delete/{id}", arg0);

        //if the ResponseCode is 429 == Too many requests
        printAndAssert(response, "DELETE");
    }

    @When("I add a post")
    public void TestAddEmployeenInJSONPlaceHoldAPI(DataTable dataTable) throws JsonProcessingException {
        Response response;
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        //RestAssured.basePath = "create";
        List<String> data = dataTable.asList();

        Map<String, String> body = new HashMap<>();
        body.put("userId", data.get(0));
        body.put("title", data.get(1));
        body.put("body", data.get(2));

        JSONObject json = new JSONObject(body);
        //String json = new ObjectMapper().writeValueAsString(body);

        RequestSpecification request = given().body(json);
        response = request.when().post("/posts");
        Post newPost = new Post(response.getBody().as(Post.class).getId(), response.getBody().as(Post.class).getUserId()
                , response.getBody().as(Post.class).getTitle(), response.getBody().as(Post.class).getBody());
        Assert.assertEquals(201, response.getStatusCode());
        Assert.assertEquals(101, newPost.getId());
        if (response.getStatusCode() == 201)
            System.out.println("Post created Id :" + newPost.getId());

//        System.out.println("Status in Response " + newPost);
//        Assert.assertEquals(2,newPost.getUserId());
//        Assert.assertEquals("title1",newPost.getTitle());
//        Assert.assertEquals("body1",newPost.getBody());

    }

    private void printAndAssert(Response response, String method) {
        String messagePrinted = "";
        switch (method) {
            case "POST":
                messagePrinted = "Successfully! Record has been added.";
                break;
            case "GET":
                messagePrinted = "Successfully! All records has been fetched.";
                break;
            case "PUT":
                messagePrinted = "Successfully! Record has been updated.";
                break;
            case "DELETE":
                messagePrinted = "Successfully! Record has been deleted";
                break;
            default:
                System.out.println("Invalid parameter");
        }
        Assert.assertEquals(200, response.getStatusCode());
        System.out.println("Status in Response " + response.jsonPath().getJsonObject("status").toString());
        System.out.println("Status in Response " + response.jsonPath().getJsonObject("message").toString());
        Assert.assertEquals(response.jsonPath().getJsonObject("status").toString(), "success");
        Assert.assertEquals(response.jsonPath().getJsonObject("message").toString(), messagePrinted);

    }


}
