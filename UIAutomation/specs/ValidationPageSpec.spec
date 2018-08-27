Validate Page Specification
============================

* Open cognitive console logIn page
* When user login into cognitive console with valid user name as "Services" and valid password as "12345678"
* When user click on Learning Instance Tab
* Then on learning instance tab selection user should be landed to "My Learning Instances" page
* When user click on New Instance button
* Then user should be landed to "Create new learning instance" page

Validate Files processed, Sent for validation, Validated, Invalid Files & Status of instance.
---------------------------------------------------------------------------------------------
* Enter all the details to create New Instance with uploading "3" file and Domain "Invoices" and Primary language "English"
* Get the total number of files uploaded
* Then click on create instance and analyze button,uploaded documents should be classified
* And user landed into "Analyzing documents..." page
* Check total number of files in analyzing are same as uploaded during creating new instance
* Progress bar exist
* Check Instance Details in Analyze Page
* Close and run in background button exist
* Ensure uploaded documents should be classified

* When user click on CreateBot link visionbot designer should be launched
* Ensure after training data should be extracted in preview & in IQTest
* Click on Bot Tab
* Enter the instance name in search textbox
* Move bot from staging to production
* Click on Learning Instance Tab
* Enter the instance name in search textbox
* Move instance from staging to production
* When instance is created then it should be listed in IQBot Lite command & Success and Invalid path should be listed in IQBot Lite command
* When instance is created then it should be listed in IQBot Lite command & Success and Invalid path should be listed in IQBot Lite command
* Go to Learning Instance Details page and wait for production file upload
* Then uploaded documents should be classified for selected Instance
* Wait for production document processing

* When user click on profile button in home page
* When user click on logout button
* Ensure user should be logout and landed to login page
* When user login into cognitive console with valid user name as "Validator" and valid password as "12345678"

* Check status of Instance, it should be "validating"
* Validate files state before validating, where validated is "0" and invalid is "0"
* Launch validator and validate
* Close validator and refresh validation page.
* Check status of Instance, it should be "reviewed"
* Validate files state after validating, where validated is "1" and invalid is "1"







