Feature: Testing booking functionality with different request

  Scenario: Check if the booking application can be accessed by users
    When User send a GET request to booking endpoints
    Then User must get list of booking and valid status code 200

  Scenario: Create a new user and verify if user is added
    When I create a new user by providing all the information
    Then I verfiy that the new user is created

  Scenario: Update a newly created user information
    When I update a newly created user
    Then I verify new user information is updated

  Scenario: Delete a newly created user
    When I delete a newly created user
    Then I verify user is deleted


     #This Scenario is for store api endpoint
  Scenario: Check if the best buy applications store api can be accessed by GET request
    When User sends a GET request to stores endpoint
    Then User must get back a valid status code 200

  Scenario: Check if the best buy applications store api can be accessed by POST request
    When User sends a POST request by providing the information to stores endpoint
    Then User must get back valid status code 201 for post request

  Scenario: Check if the best buy applications store api can be accessed by PUT request
    When User sends a PUT request by providing the information to stores/id endpoint
    Then User get back valid status code 200

  Scenario: Check if the best buy applications store api can be accessed by DELETE request
    When User sends a DELETE request to stores/id endpoint
    Then User get back a valid status code 200