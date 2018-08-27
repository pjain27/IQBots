IQBot Test Automation with Gauge
================================
* Open cognitive console logIn page with service role user

Verify that, user is allowed to login to Cognitive platform with Services role
-------------------
* When user login into cognitive console with valid user name as "Services" and valid password as "12345678"
* Then dashboard should be displayed for service user
* Ensure user with service role get all tabs access

Logout Verification
-------------------
* When user login into cognitive console with valid user name as "Services" and valid password as "12345678"
* Then dashboard should be displayed for service user
* When user click on profile button in home page
* Then logged in user name as "Services" should be displayed 
* When user click on logout button
* Ensure user should be logout and landed to login page

Create New Instance
-------------------
* When user login into cognitive console with valid user name as "Services" and valid password as "12345678"
* Then on learning instance tab selection user should be landed to "My Learning Instances" page
* When user click on New Instance button 
* Then user should be landed to "Create new learning instance" page 
* When user user insert all required fields and upload files 
* Then on create instance and analyze button click, uploaded documents should be classified
* And user landed into "Analyzing documents.." page
