Feature: Statement printing
  In order to check my operations
  As a bank client
  I want to see the history (operation, date, amount, balance) of my operations

  Scenario: Statement printing of an empty account
    Given There is an account with the following operations
      | type    | amount | date             |
    Then The statement printing should have the following lines
      | type    | amount | date             | balance |
