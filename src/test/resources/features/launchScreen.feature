@splash-screen-feature

Feature: launch-screen

  Scenario Outline: Verify Get Started button navigation
    Given User is on Splash screen
    When Tap on get started button
    Then It will navigate the user to Welcome screen asking "<message>"

    Examples:
      | message        |
      | Would you like to connect to your Skiin Garment? |

#  Scenario: Swipe carousel to left and verify animations
#    Given User is on Splash screen
#    When User swipe to left
#    Then Carousel should swipe to left and correct animation should be displayed
#
#  Scenario Outline: Verify new splash screen with internet ON
#    Given User is on Splash screen
#    Then Verify the app title "<title>" and 4 carousel sliders
#    Then Verify second section which contains welcome screen
#
#    Examples:
#      |title|
#      |SKIIN|
