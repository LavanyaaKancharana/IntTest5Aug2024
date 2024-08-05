@SamsungPhoneSearchOnAmazon

Feature: Look for mobile phones on amazon

  Scenario Outline: To get a list of samsung phones on amazon based on the search criteria

    Given User goes to the amazon all menu
    And User chooses "Electronics & Computers" to shop by department
    And User chooses "Phones & Accessories" from electronics
    And User goes to mobiles section to choose "SIM-Free" phones in the menu
    When the user sets the search criteria with the brand "<brand>", the camResolution "<cameraResolution>", the modelYear "<modelYear>" and the priceRange "<priceRange>"
    Then the user should see the results based on their search

    Examples:
      |brand  |cameraResolution|modelYear|priceRange|
      |Samsung|20 MP & above   |2023     |£50-£100  |