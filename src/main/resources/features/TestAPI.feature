Feature: Test API


  Scenario: Get Api
    Given I send GET request to endpoint "/name/Nikola Jokić" with "nba" "unsecure" url
    Then I validate the status code 200
    Then I validate the response contains following:
    | count | 8 |



  Scenario: Post api and validate response
    Given I send POST request to endpoint "/api/users" with "reqres" "unsecure" url:
      | Body | {"name": "abc_test","job": "leader1"} |
    Then I validate the status code 201
    Then I validate the response contains following:
      | name | abc_test |
    Then I validate the response contains following:
      | job | leader1 |


