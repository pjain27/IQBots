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
* Ensure uploaded documents should be classified

4.Learning Instance Detail Page [Staging file count]
Verify documents count in Learning Instance Detail page, When Instance is in Staging
------------------------------------------------------------------------------------
* On learning instance details page documents should be classified in groups
* Then classified documents count should be match with uploaded documents count
* Ensure that Training Summary is also updated based on staging data

5.Edit Instance [Analyzing Document]
Verify edit Instance functionality by uploading 2 documents
-----------------------------------------------------------
* When user edit instance and upload files
* Then on save button click uploaded files should be classified

6.Close & Run In Background
Close & Run In Background Button functionality Verification
-----------------------------------------------------------
* Click on Close and Run In Background button
* Wait for documents processing in Learning Instance Details Page

7.Learning Instance Detail Page [updated count]
In Learning Instance Detail page, verify staging documents counts after edit instance
-------------------------------------------------------------------------------------
* Ensure that after document classification total files and groups should be updated
* Ensure that Training Summary is also updated based on staging data after edit Instance

8.Train Visionbot
Visionbot Training Verification (Train, Preview & IQTest Run)
-------------------------------------------------------------
* When user click on CreateBot link visionbot designer should be launched
* Ensure after training data should be extracted in preview & in IQTest

9.Bot Listing Page [Status Check]
Search with Instance Name and check the status of the bot
---------------------------------------------------------
* Click on Bot Tab
* User Landed to Bot Page
* Select "Instance Name" from search DropDown in Bot Page
* Enter the instance name in search textbox
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
* Click on Learning Instance Tab
* Select "Instance Name" from search DropDown in LearningInstance Page
* Enter the instance name in LearningInstance Search textbox
* Move instance from production to staging
* Click on Bot Tab
* Select "Instance Name" from search DropDown in Bot Page
* Enter the instance name in search textbox
* Move bot from production to staging

10.Run once Bot [Group staging document processing]
Run trained bot and verify document processing using Run Once functionality
---------------------------------------------------------------------------
* When user go to bot listing page and validate bot run summary then all details should be available
* And when user click on bot run, details should be updated after bot processing

11.Learning Instance Listing page [Updated Count]
Learning Instances Listing page Verification after Bot run in staging
---------------------------------------------------------------------
* Validate all data in Learning Instance Listing Page after Bot run

12.Bot Listing Page [Move bot to production]
Bot Details Verification after Moving Bot from Staging to Production
--------------------------------------------------------------------
* Validate all data after group move from Staging to production

13.Learning Instance Listing Page [Move LI to pro]
Learning Instance Listing page varification after moving instance from Staging to Production
--------------------------------------------------------------------------------------------
* When user click on profile button in home page
* Then logged in user name as "Services" should be displayed
* When user click on logout button
* Ensure user should be logout and landed to login page
* Open cognitive console logIn page with service role user between executing scripts
* When user login into cognitive console with valid user name as "Services" and valid password as "12345678"
* Validate Learning Instance Listing data after move instance from staging to production

14.IQBot Lite command & Learning Instance Details page [Production values]
IQBot Lite Command & Production File Upload Verification
--------------------------------------------------------
* When instance is created then it should be listed in IQBot Lite command & Success and Invalid path should be listed in IQBot Lite command
* Go to Learning Instance Details page and wait for production file upload
* Then uploaded documents should be classified for selected Instance
* Ensure that uploaded production files should be classified for selected Instance
* Ensure that Training Summary is also updated based on production data

15.Dashboard
Total Processed Files and STP Verification on Dashboard
-------------------------------------------------------
* Click on Dashboard tab
* Get the total number of files processed on dashboard page
* Get all the file processed from each domain
* Check total number of files Processed should be same as sum of file processed in particular domain which are in production
* Get the STP value from Dashboard page
* Click on each instance which are in production and get sum of file successfully Processed and sum of files uploaded and calculate STP

16.Performance Report
Total Processed Files, STP & Accuracy Verification for Performance Report
-------------------------------------------------------------------------
* Get all the instance name which are in production
* Click on instance name
* User will be landed to Performance Report Page
* Validate Processing result values for "Smoke Spec"
* Click on Dashboard tab
* Click on each instance which are in production to validate STP Accuracy and Total files processed

17. Output Folder Verification
Output folder creation Verification for Production instance
-----------------------------------------------------------
* Check output folder for created instance and validate unclassified files

18. Validator Verification
Validator Launch, Document count, Error count & Image load Verification
-----------------------------------------------------------------------
* Click on Learning Instance Tab
* Select "Instance Name" from search DropDown in LearningInstance Page
* Enter the instance name in LearningInstance Search textbox
* Launch Validator from Learning Instance Listing Page
* Validate total number of files, error count and image view in validator

19. Delete Instance
Delete Instance Verification
-----------------------------
* When user click on profile button in home page
* When user click on logout button
* Open cognitive console logIn page
* When user login into cognitive console with valid user name as "Services" and valid password as "12345678"
* Click on Learning Instance Tab
* Get the total number of instance created
* Select "Instance Name" from search DropDown in LearningInstance Page
* Enter the instance name in LearningInstance Search textbox
* On Learning Instance page click on instance name
* Instance view Details page exist
* By clicking on Edit button Delete Instance button should be displayed
* By clicking on deleteInstance button content modal should appear with enabled Cancel button and disabled I understand,please delete button
* Enter the instance name on the Textbox which you want to delete
* Click on I understand, please delete
* If no more instance created before "No current learning instances." message will be displayed else search the instance name on search textbox and validate the result

20. Logout
Logout Verification
-------------------
* When user click on profile button in home page
* Then logged in user name as "Services" should be displayed
* When user click on logout button
* Ensure user should be logout and landed to login page

21.Log In with IQBotValidator Role
Verify that, user is allowed to login to IQBot platform with IQBotValidator role
--------------------------------------------------------------------------------
* Open cognitive console logIn page
* When user login into cognitive console with valid user name as "Validator" and valid password as "12345678"
* Then user with Validator role get only Learning Instance tab access
* When user click on profile button in home page
* When user click on logout button
* Ensure user should be logout and landed to login page
* Quit browser
