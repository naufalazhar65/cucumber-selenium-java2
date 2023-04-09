Feature: Checkout

  @validcheckout
  Scenario Outline: User is able to checkout successfully
    Given User has logged in with valid credentials
    When User adds items to cart and goes to checkout
    And User enters checkout information and completes checkout
    Then User should see checkout complete message

  @invalidcheckout
  Scenario: User is unable to checkout with missing information
    Given User has logged in with valid credentials
    When User adds items to cart and goes to checkout
    And User enters incomplete checkout information and tries to complete checkout
    Then User should see error message indicating missing information
