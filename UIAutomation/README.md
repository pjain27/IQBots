# README #

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###

* Quick summary : IQBot Test Automation created using Selenium, Java & Gauge.
* Version : 1.0
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)

### How do I get set up? ###

* We are using IntelliJ IDEA for running specifications. [IntelliJ](https://www.jetbrains.com/idea/download/download-thanks.html?platform=windows&code=IIC)
* Test Steps & Scenarios are written in specifications using Gauge Markdown language, To know more please visit gauge website. [https://getgauge.io/]
* Install Gauge for windows. [Gauge](https://getgauge.io/get-started.html)
* Configuration : After installing IntelliJ, go to File menu > Settings > Plugins : Search for gauge plugins and install it.
* Dependencies : Gauge Plugins, Selenium, Assert Libraries
* Database configuration : NA
* How to run tests : Tests can be executed by specifications > Go to Specs folder and execute specifications.
* Deployment instructions : [Confluence](https://automationanywhere.atlassian.net/wiki/spaces/CD/pages/848332/UI+Test+Framework)

### Setup Gauge Sample Test Application if you are using Maven / Eclipse ###
## Installing Gauge template

    gauge --install java_maven_selenium

## Building on top of this template

### Defining Specifications

* This template includes a sample specification which opens up a browser and navigates to `Get Started` page of Gauge.
* Add more specifications on top of sample specification.

Read more about [Specifications](http://getgauge.io/documentation/user/current/specifications/README.html)

### Contribution guidelines ###

* Writing tests
This is where the java implementation of the steps would be implemented. Since this is a Selenium based project, the java implementation would invoke Selenium APIs as required.

_We recommend considering modelling your tests using the [Page Object](https://github.com/SeleniumHQ/selenium/wiki/PageObjects) pattern, and the [Webdriver support](https://github.com/SeleniumHQ/selenium/wiki/PageFactory) for creating them._

- Note that every Gauge step implementation is annotated with a `Step` attribute that takes the Step text pattern as a parameter.
Read more about [Step implementations in Java](http://getgauge.io/documentation/user/current/test_code/java/java.html)
* Code review
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact