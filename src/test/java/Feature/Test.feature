@Feature:Amazon
Feature: Test

  Scenario: Amazon Navigation

    Given Navigate to amazon
    When search for headphones
    Then Select the second product and get page title