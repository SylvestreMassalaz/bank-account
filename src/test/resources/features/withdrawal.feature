Feature: Account withdrawal
  In order to retrieve some or all of my savings
  As a bank client
  I want to make a withdrawal from my account

  Scenario: Withdrawing money from an account
    Given There is an account with the following operations
      | type    | amount | date             |
      | DEPOSIT | 42.0   | 2020-07-30 16:05 |
    When I withdraw an amount of 42.0 at date 2020-07-30 16:10
    Then The operation should be accepted
    Then The account should have the following operations
      | type       | amount | date             |
      | DEPOSIT    | 42.0   | 2020-07-30 16:05 |
      | WITHDRAWAL | 42.0   | 2020-07-30 16:10 |

  Scenario: Withdrawing money from an account without sufficient funds
    Given There is an account with the following operations
      | type    | amount | date             |
      | DEPOSIT | 42.0   | 2020-07-30 16:05 |
    When I withdraw an amount of 42.1 at date 2020-07-30 16:10
    Then The operation should be rejected
    Then The account should have the following operations
      | type       | amount | date             |
      | DEPOSIT    | 42.0   | 2020-07-30 16:05 |

  Scenario: Withdrawing a negative amount of money shouldn't work
    Given There is an account with the following operations
      | type    | amount | date             |
      | DEPOSIT | 42.0   | 2020-07-30 16:05 |
    When I withdraw an amount of -42.0 at date 2020-07-30 16:10
    Then The operation should be rejected
    Then The account should have the following operations
      | type       | amount | date             |
      | DEPOSIT    | 42.0   | 2020-07-30 16:05 |

  Scenario: Withdrawing money at a date that is before the last operation shouldn't work
    Given There is an account with the following operations
      | type    | amount | date             |
      | DEPOSIT | 42.0   | 2020-07-30 16:10 |
    When I withdraw an amount of 42.0 at date 2020-07-30 15:10
    Then The operation should be rejected
    Then The account should have the following operations
      | type    | amount | date             |
      | DEPOSIT | 42.0   | 2020-07-30 16:10 |