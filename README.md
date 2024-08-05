Cucumber + Selenium + Spring Boot Sample:
This repository contains the base setup of an UI testing project, using Cucumber + Selenium (Page Factory Model) + Spring Boot

Requirements
JDK 18
Maven 3.8.6

Test Execution
Download or clone the repository
Open a terminal
From the project root directory run: mvn clean test

Configuration
By default, tests will be executed in Chrome (headless mode).
Preferences can be changed in "application.properties" file
This values can also be defined from the command line when launching the tests without the need of modify the properties file.
mvn clean test -Dselenium.browser.headless=false

Results
To check the report, open the '/results/cucumber-reports.html' file once the execution has finished.
