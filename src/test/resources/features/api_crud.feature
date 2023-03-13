Feature: Ornikar testing API


  Scenario: Testing the API
    Given get all employees
    When I add a employee
      | fff | 325 | 30 |
    When I update employee id=5 with values
      | AAA | 4000 | 25 |
    Then I delete employee id=822

      #IF HTTP STATUS CODE IS 429 == Too many calls

  Scenario: Alternative for Post API = https://jsonplaceholder.typicode.com/posts

    When I add a post
      | 2 | title1 | body1 |

