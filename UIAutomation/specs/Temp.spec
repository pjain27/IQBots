Created on 8th September - 2017

IQBot Smoke Test Automation Suit
================================
1.Log In with IQBotService Role
Verify that, user is allowed to login into IQBot platform with IQBotServices role
---------------------------------------------------------------------------------
* Open cognitive console logIn page
* When user login into cognitive console with valid user name as "Services" and valid password as "12345678"
* Then dashboard should be displayed for service user
* Ensure user with service role get all tabs access

2.Create New Instance & 3.Analyzing Documents
Instance creation verification for the English language with Invoice Domain, using 3 documents
----------------------------------------------------------------------------------------------
* When user click on Learning Instance Tab
* Then on learning instance tab selection user should be landed to "My Learning Instances" page
* When user click on New Instance button
* Then user should be landed to "Create new learning instance" page
* Enter all the details to create New Instance with uploading "3" file and Domain "Invoices" and Primary language "English"
* Get the total number of files uploaded
* Then click on create instance and analyze button,uploaded documents should be classified
* And user landed into "Analyzing documents..." page
* Check total number of files in analyzing are same as uploaded during creating new instance
* Progress bar exist
* Check Instance Details in Analyze Page
* Close and run in background button exist

3. Classification
Document Classification Verification
------------------------------------
* Open cognitive console logIn page
* When user login into cognitive console with valid user name as "Services" and valid password as "12345678"
* Click on Learning Instance Tab
* Select "Instance Name" from search DropDown in LearningInstance Page
* Enter the instance name in LearningInstance Search textbox
* On Learning Instance page click on instance name
* Instance view Details page exist
* Sorting Validation for the header - "# of Training Files"
* Sorting Validation for the header - "Training Success"
* Sorting Validation for the header - "Training Unprocessed"
* Sorting Validation for the header - "# of Production Files"
* Sorting Validation for the header - "Production Success"
* Sorting Validation for the header - "Production Unprocessed"
* Sorting Validation for the header - "Priority"