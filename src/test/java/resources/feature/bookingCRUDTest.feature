Feature:Booking functionality
  As a user i want to test booking functionality by end to end testing using CRUD operation

  Scenario : I should able to generate a token successfully by entering the valid username and password
    Given    I am in restfull booker application
    When     I create a new post request by entering username and password for token

  Scenario Outline: I should able to perform CRUD operation successfully
    When I get a list of booking ID before creating a new booking ID
    And  I create new booking with firstname"<firstname>" lastname "<lastname>" totalprice"<totalprice>"depositepaid"<depositpaid>""<checkin>""<checkout>" "<additionalneeds>"
    And  I verfiy that the new booking has been created
    And  I update a newly created booking with firstname"<firstname>" lastname "<lastname>" totalprice"<totalprice>"depositepaid"<depositpaid>""<checkin>""<checkout>" "<additionalneeds>"
    And  I verify booking has been updated
    And  I delete a newly created booking
    Then i verify newly created booking has been deleted

    Examples:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Smith2    | john2    | 200        | true        | 2018-01-01 | 2019-01-01 | Breakfast       |