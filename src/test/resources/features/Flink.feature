@Flink
Feature: Shop for the Moisturizers (if the temperature is bellow 19 degree) or Sunscreens (if the temperature is above 34 degree
  

@shop
  Scenario: Verify Shoping experience w r t weather 
    Given User launch "https://weathershopper.pythonanywhere.com/"
    And User verifies the temperature for shopping
    When User Shop for the Moisturizers (if the temperature is bellow 19 degree) or Sunscreens (if the temperature is above 34 degree 
    And User adds items to carts
    Then User completes the payment 
    And verifies user placed order sucessfully
    
 

    
    