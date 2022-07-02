# Project3: UI tests

## Description

* The Project3 includes UI tests for [Stellar Burgers](https://stellarburgers.nomoreparties.site/);
* tests located at ```/src/test/```;
* object models located at ```/src/main/java/pageobjects/```;
* test data creates before and removes after running by API requests;
* allure report (available at: ```target/allure-results/```) was added.

## List of Checks 
### User Creation 
* create new user;
* error for creation user with invalid password.

### User Authorization
* authorization by "login" button on main page;
* authorization by "my account" button;
* authorization by button on registration form;
* authorization by button on password recovery page.
 
### Transition to My Account
* transition to my account by link.

### Transition to Constructor from My Account
* transition to constructor by link; 
* transition to constructor by logo.

### Logout
* logout by button in my account.

### Constructor
* transition to "Bun" tab;
* transition to "Sauce" tab;
* transition to "Filling" tab.

## Stack
* Java 11; 
* Amazon Coretto JDK;
* Maven;
* Selenide;
* JUnit 4;
* RestAssured (for Test Data);
* Allure.

## How to Get Project Locally?
1. Clone repository: 
```git clone git@github.com:ilyastepanovs/Project3.git```;
2. Open project in IDE;
3. Run the tests from the following location ```/src/test/```

## How to Open Allure Report?
1. Open project directory;
2. Enter ```mvn allure:serve``` in terminal.

