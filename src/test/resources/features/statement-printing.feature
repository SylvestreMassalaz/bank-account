Feature: Statement printing
  In order to check my operations
  As a bank client
  I want to see the history (operation, date, amount, balance) of my operations

  Scenario: Statement printing of an empty account
    Given There is an account with the following operations
      | type    | amount | date             |
    When The user prints it's statements
    Then The statement printing should have the following lines
      | type    | amount | date             | balance |


  Scenario: Statement printing of a non empty account
    Given There is an account with the following operations
      | type       | amount | date             |
      | DEPOSIT    | 2350.0 | 2020-07-02 09:55 |
      | WITHDRAWAL | 770.0  | 2020-07-03 15:43 |
      | WITHDRAWAL | 86.52  | 2020-07-03 18:12 |
      | DEPOSIT    | 100.0  | 2020-07-10 09:05 |
    When The user prints it's statements
    Then The statement printing should have the following lines
      | type       | amount | date             | balance |
      | DEPOSIT    | 100.0  | 2020-07-10 09:05 | 1593.48 |
      | WITHDRAWAL | 86.52  | 2020-07-03 18:12 | 1493.48 |
      | WITHDRAWAL | 770.0  | 2020-07-03 15:43 | 1580.0  |
      | DEPOSIT    | 2350.0 | 2020-07-02 09:55 | 2350.0  |
