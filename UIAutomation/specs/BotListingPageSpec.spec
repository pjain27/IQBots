Verify Bot Listing Page
=======================
* Open cognitive console logIn page
* When user login into cognitive console with valid user name as "Services" and valid password as "12345678"
* Then dashboard should be displayed for service user
* Click on Learning Instance Tab
* Click on New Instance Button
* Enter all the details to create New Instance with uploading "1" file and Domain "Invoices" and Primary language "English"
* Get the total number of files uploaded
* Then click on create instance and analyze button,uploaded documents should be classified
* Instance is successfully created and user is landed to Analyzing documents page
* Click on Close and Run In Background button
* Wait for documents processing in Learning Instance Details Page
* Instance view Details page exist
* Check for number of groups created and click on Create Bot with highest priority

Verify values of Search field DropDown
--------------------------------------
* Verify values of search field DropDown

Search with Instance Name and check the status of the bot
---------------------------------------------------------
* Close the Designer window
* Instance name on learning instance detail page is same as the instance name entered while creating instance
* Click on Bot Tab
* User Landed to Bot Page
* Select "Instance Name" from search DropDown in Bot Page
* Enter the instance name in search textbox
* Group name should be same as the group name for which bot was created for that particular instance
* Bot should be in "training" status
* Move bot from staging to production
* Bot should be in "ready" status
* Click on Learning Instance Tab
* Select "Instance Name" from search DropDown in LearningInstance Page
* Enter the instance name in LearningInstance Search textbox
* Move instance from staging to production
* Click on Bot Tab
* Select "Instance Name" from search DropDown in Bot Page
* Enter the instance name in search textbox
* Bot should be in "active" status

Validate Search Functionality
------------------------------
* Close the Designer window
* Instance name on learning instance detail page is same as the instance name entered while creating instance
* Click on Bot Tab
* User Landed to Bot Page

* Select "All Fields" from search DropDown in Bot Page
* Enter the instance name in search textbox
* Instance name should be listed on Bot page list
* Search result should include the Group name too
* Refresh the page

* Select "Environment" from search DropDown in Bot Page
* Select Instance which are in "Staging"
* Instance name should be listed on Bot page list
* Search result should include the Group name too
* Refresh the page
* Enter the instance name in search textbox
* Move bot from staging to production
* Refresh the page

* Select "Environment" from search DropDown in Bot Page
* Select Instance which are in "Production"
* Instance name should be listed on Bot page list
* Search result should include the Group name too

* Refresh the page
* Select "Instance Name" from search DropDown in Bot Page
* Enter the instance name in search textbox
* Instance name should be listed on Bot page list
* Group name should be same as the group name for which bot was created for that particular instance

* Refresh the page
* Select "Instance Name" from search DropDown in Bot Page
* Enter the instance name in search textbox
* Move bot from production to staging
* Refresh the page

* Select "Status" from search DropDown in Bot Page
* Select Instance which are in "training"
* Instance name should be listed on Bot page list
* Search result should include the Group name too

* Refresh the page
* Select "Instance Name" from search DropDown in Bot Page
* Enter the instance name in search textbox
* Move bot from staging to production
* Refresh the page

* Select "Status" from search DropDown in Bot Page
* Select Instance which are in "ready"
* Instance name should be listed on Bot page list
* Search result should include the Group name too

* Click on Learning Instance Tab
* Select "Instance Name" from search DropDown in LearningInstance Page
* Enter the instance name in LearningInstance Search textbox
* Move instance from staging to production
* Click on Bot Tab

* Select "Status" from search DropDown in Bot Page
* Select Instance which are in "active"
* Instance name should be listed on Bot page list
* Search result should include the Group name too

Validate warning message on bot run
------------------------------------
* Ensure after training data should be extracted in preview & in IQTest for all pass fields
* Click on Bot Tab
* User Landed to Bot Page
* Select "Instance Name" from search DropDown in Bot Page
* Enter the instance name in search textbox
* Run the bot once
* Run the bot once
* Error message should be displayed "Staging document for this visionbot is already in running state."

Validate warning message on set bot to production
-------------------------------------------------
* Ensure after training data should be extracted in preview & in IQTest for all pass fields
* Click on Bot Tab
* User Landed to Bot Page
* Select "Instance Name" from search DropDown in Bot Page
* Enter the instance name in search textbox
* Run the bot once and move bot from staging to production
* Error message should be displayed "Visionbot is in running state."

Validate Success count of documents
------------------------------------
* Ensure after training data should be extracted in preview & in IQTest for all pass fields
* Click on Bot Tab
* User Landed to Bot Page
* Select "Instance Name" from search DropDown in Bot Page
* Enter the instance name in search textbox
* Run the bot once
* Wait for bot run process to be completed
* Refresh the page
* Select "Instance Name" from search DropDown in Bot Page
* Enter the instance name in search textbox
* Count of file successfully processed should be "1"
* Move bot from staging to production
* Click on Learning Instance Tab
* Select "Instance Name" from search DropDown in LearningInstance Page
* Enter the instance name in LearningInstance Search textbox
* Move instance from staging to production
* Upload documents from IQBot Lite command
* Go to Learning Instance Details page and wait for production file upload
* Then uploaded documents should be classified for selected Instance
* Wait for production document processing
* Refresh the page
* Click on Bot Tab
* Select "Instance Name" from search DropDown in Bot Page
* Enter the instance name in search textbox
* Count of file successfully processed should be "1"
//-------------------------------------------------------------

Validate warning message on launch designer
--------------------------------------------
* Ensure after training data should be extracted in preview & in IQTest for all pass fields
* Click on Bot Tab
* User Landed to Bot Page
* Select "Instance Name" from search DropDown in Bot Page
* Enter the instance name in search textbox
* Run the bot once
* Click on edit button of bot page
* Close the Designer window
7. Validate warning message for bot run in progress