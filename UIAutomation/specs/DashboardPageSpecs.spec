Dashboard Specification
=======================
* Open cognitive console logIn page
* When user login into cognitive console with valid user name as "services" and valid password as "12345678"

Check File Processed on Dashboard Page
--------------------------------------
* Then dashboard should be displayed for service user
* Get the total number of files processed on dashboard page
* Get all the file processed from each domain
* Check total number of files Processed should be same as sum of file processed in particular domain which are in production

Check STP on Dashboard Page
---------------------------
* Get the STP value from Dashboard page
* Click on each instance which are in production and get sum of file successfully Processed and sum of files uploaded and calculate STP

Validate Learning Instance summary when instance is in staging
------------------------------------------------------------------
* Then dashboard should be displayed for service user
* Click on Learning Instance Tab
* Click on New Instance Button
* Enter all the details to create New Instance with uploading "1" file and Domain "Invoices" and Primary language "English"
* Then click on create instance and analyze button,uploaded documents should be classified
* Instance is successfully created and user is landed to Analyzing documents page
* Click on Close and Run In Background button
* Wait for documents processing in Learning Instance Details Page
* Instance view Details page exist
* Check for number of groups created and click on Create Bot with highest priority
* Ensure after training data should be extracted in preview & in IQTest for all pass fields
* Click on Bot Tab
* User Landed to Bot Page
* Select "Instance Name" from search DropDown in Bot Page
* Enter the instance name in search textbox
* Run the bot once
* Wait for bot run process to be completed
* Move bot from staging to production
* Click on Dashboard tab
* Validate values of learning instance when instance is in staging