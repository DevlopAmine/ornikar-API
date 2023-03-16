$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/api_crud.feature");
formatter.feature({
  "name": "Ornikar testing API",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Testing the API",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "get all employees",
  "keyword": "Given "
});
formatter.match({
  "location": "org.example.StepDefinition.TestReturnAllEmployees()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I add a employee",
  "rows": [
    {}
  ],
  "keyword": "When "
});
formatter.match({
  "location": "org.example.StepDefinition.TestAddEmployee(io.cucumber.datatable.DataTable)"
});
formatter.result({
  "error_message": "java.lang.AssertionError: expected:\u003c200\u003e but was:\u003c429\u003e\r\n\tat org.junit.Assert.fail(Assert.java:89)\r\n\tat org.junit.Assert.failNotEquals(Assert.java:835)\r\n\tat org.junit.Assert.assertEquals(Assert.java:647)\r\n\tat org.junit.Assert.assertEquals(Assert.java:633)\r\n\tat org.example.StepDefinition.printAndAssert(StepDefinition.java:162)\r\n\tat org.example.StepDefinition.TestAddEmployee(StepDefinition.java:78)\r\n\tat ✽.I add a employee(file:///C:/Users/amine%20houri/Documents/Maison/api_ornikar/src/test/resources/features/api_crud.feature:6)\r\n",
  "status": "failed"
});
formatter.step({
  "name": "I update employee id\u003d5 with values",
  "rows": [
    {}
  ],
  "keyword": "When "
});
formatter.match({
  "location": "org.example.StepDefinition.iUpdateEmployeeId(int,io.cucumber.datatable.DataTable)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "I delete employee id\u003d822",
  "keyword": "Then "
});
formatter.match({
  "location": "org.example.StepDefinition.iDeleteEmployeeId(int)"
});
formatter.result({
  "status": "skipped"
});
formatter.scenario({
  "name": "Alternative for Post API \u003d https://jsonplaceholder.typicode.com/posts",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I add a post",
  "rows": [
    {}
  ],
  "keyword": "When "
});
formatter.match({
  "location": "org.example.StepDefinition.TestAddEmployeenInJSONPlaceHoldAPI(io.cucumber.datatable.DataTable)"
});
formatter.result({
  "status": "passed"
});
});