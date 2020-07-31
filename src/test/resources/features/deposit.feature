Feature: Account deposit
  In order to save money
  As a bank client
  I want to make a deposit in my account

  Scenario: Depositing money into an empty account
    Given There is an account with the following operations
      | type | amount | date |
    When I deposit an amount of 42.0 at date 2020-07-30 16:10
    Then The operation should be accepted
    Then The account should have the following operations
      | type    | amount | date             |
      | DEPOSIT | 42.0   | 2020-07-30 16:10 |

  Scenario: Depositing a negative amount of money
    Given There is an account with the following operations
      | type | amount | date |
    When I deposit an amount of -42.0 at date 2020-07-30 16:10
    Then The operation should be rejected
    Then The account should have the following operations
      | type    | amount | date             |

  Scenario: Depositing money at a date that is before the last operation shouldn't work
    Given There is an account with the following operations
      | type    | amount | date             |
      | DEPOSIT | 42.0   | 2020-07-30 16:10 |
    When I deposit an amount of 42.0 at date 2020-07-30 15:10
    Then The operation should be rejected
    Then The account should have the following operations
      | type    | amount | date             |
      | DEPOSIT | 42.0   | 2020-07-30 16:10 |