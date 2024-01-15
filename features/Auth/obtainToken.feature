#language: en
#encoding: UTF-8

@GetAuthToken
Feature: Obtain authentication token

  Background:
    Given I have valid authentication route request
    And I have valid data to obtain authentication token

  @CT-001
  Scenario: Obtain token succesfully
    When I do a simple Post request
    Then I receive the token

  @CT-002
  Scenario: Obtain token succesfully 2
    When I do a simple Post request
    Then I receive the token

  @CT-003
  Scenario: Obtain token succesfully 3
    When I do a simple Post request
    Then I receive the token