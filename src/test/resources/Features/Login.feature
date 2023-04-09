Feature: Login Functionality Test

  @loginpage
  Scenario: User opens the login page successfully
    Given User navigates to the login page
    Then The login page should be displayed

  @validlogin
  Scenario Outline: User logs in with valid credentials
    Given User is on the login page
    When User enters valid "<username>" and "<password>"
    And User clicks the login button
    Then The user should be logged in successfully

    Examples: 
      | username      | password     |
      | standard_user | secret_sauce |

  @invalidlogin
  Scenario Outline: User logs in with valid credentials
    Given User is on the login page
    When User enters valid "<username>" and "<password>"
    And User clicks the login button
    Then Failed login and showing message

    Examples: 
      | username | password |
      | xx       | xx       |

  @logout
  Scenario: User logs out successfully
    Given User is logged in
    When User clicks the logout button
    Then The user should be logged out successfully
