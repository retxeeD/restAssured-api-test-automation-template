#language: en
#encoding: UTF

@GetTest
Feature: Get test feature

  Background:
    Given I have valid data to do a get request

  Scenario: Test 01
    When do the request
    Then I receive data "" on the path ""