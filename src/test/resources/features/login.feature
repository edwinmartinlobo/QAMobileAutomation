@login-feature
Feature: Login
  Scenario Outline: Login with invalid user name
    When User enter username as "<username>"
    And User enter password as "<password>"
    And User click on login button
    Then login should fail with an error "<err>"

    Examples:
      | username        | password     | err           |
      | invalidUsername | secret_sauce | Invalid Email With User |

  Scenario: Verify the warning popup
    Given User is on splash screen
    When Tap on get started button
    And User will land on welcome screen
    And There are two buttons Yes and Don't have a Garment
    And Tap on Yes>>Garment selection screen will appear
    And Tap on the Garment and a warning pop up with Okay and Cancel button will appear on screen
    Then Verify pop up has title: Note to user
    And Verify pop up content as per figma
    And Tapping on cancel will bring the user to Garment selection
    And Tapping Okay will lead the user to new screen

  Scenario Outline: Create an account with new email address
    Given User is on splash screen
    When Tap on get started button
    And Tap on Don't have A Garment button
    And Select a persona on the next screen that appears
    And On the next screen that appears click on Let's Begin! button
    And Enter "<firstname>", "<lastname>", "<email>" and check Terms and Conditions check box details
    And Click on Next button
    And On the next screen appears provide "<password>" and confirm password
    And Click on sign up button
    Then Verify email is sent to user's email address contains code
    And User is taken to the screen asking to input the code

    Examples:
      | firstname        | lastname     | email                   | password  |
      | invalidUsername  | secret_sauce | Invalid Email With User |           |


