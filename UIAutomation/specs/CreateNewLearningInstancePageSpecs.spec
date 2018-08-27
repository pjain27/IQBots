Create New Learning Instance Module
===================================
* Open cognitive console logIn page
* When user login into cognitive console with valid user name as "Services" and valid password as "12345678"
* Then dashboard should be displayed for service user
* Click on Learning Instance Tab
* Click on New Instance Button

Create New Learning Instance Page Exist (COG-879)
---------------------------------------
* User Should be landed to Create New Learning Instance page

Verify default selected Value of Domain DropDown (COG-880)
------------------------------------------------
* Verify values of Domain DropDown
* Default value of Domain DropDown is Select Input

Verify default selected Value of Primary Language dropDown (COG-901)
----------------------------------------------------------
* Verify values of Primary language DropDown
* Default Value of Primary language is English

when selecting a domain for a new learning instance fields should be preset (COG-886)
----------------------------------------------------------------------------
* Verify values of Domain DropDown
* Verify values displayed for each dropdown item

Create New Instance (COG-887)
-------------------
* Enter all the details to create New Instance with uploading "3" file and Domain "Invoices" and Primary language "English"
* Then click on create instance and analyze button,uploaded documents should be classified
* Instance is successfully created and user is landed to Analyzing documents page

Create New Learning Instance TextBox Validation (COG-897)
------------------------------------------------
* New Learning Instance TextBox Should give message for Blank Warning,Special Characters Warning,length warning
* Enter all the details to create New Instance with uploading "1" file and Domain "Invoices" and Primary language "English"
* Then click on create instance and analyze button,uploaded documents should be classified
* Instance is successfully created and user is landed to Analyzing documents page
* Click on Learning Instance Tab
* Click on New Instance Button
* Create New Instance With Same Instance Name with uploading "1" file and Domain "Invoices" and primary language "English"
* Then click on create instance and analyze button for Duplicate Instance Name
* Duplicate error message will be displayed

// Validate Instance Name & Description textbox
Check error/warning message for Description Textbox in Create New Learning Instance page (COG-898)
--------------------------------------------------------------------------------------------------
* Description textBox should give warning message for special charecters and length warning message if chracters more than 255

Once the Instance is Created it should be displayed in Learning Instance Page List (COG-881)
----------------------------------------------------------------------------------
* Enter all the details to create New Instance with uploading "1" file and Domain "Invoices" and Primary language "English"
* Then click on create instance and analyze button,uploaded documents should be classified
* Instance is successfully created and user is landed to Analyzing documents page
* Click on Learning Instance Tab
* Select "Instance Name" from search DropDown in LearningInstance Page
* Enter the instance name in LearningInstance Search textbox
* Instance name should be listed on list

Once we click on create instance and analyze button, analyze detail page should be displayed with same Instance name and learning instance detail page should be display with same instance name and description(COG-895)
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 * Enter all the details to create New Instance with uploading "1" file and Domain "Invoices" and Primary language "English"
 * Get the total number of files uploaded
 * Then click on create instance and analyze button,uploaded documents should be classified
 * Check the Instance name in analyze page is same as Instance name was given while creating instance
 * Instance is successfully created and user is landed to Analyzing documents page
 * Ensure uploaded documents should be classified
 * Instance view Details page exist
 * Instance name on learning instance detail page is same as the instance name entered while creating instance
 * Instance description on learning instance detail page is same as the instance description entered while creating instance

Cancel Create New Instance (COG-882)
------------------------------------
* Enter all the details to create New Instance with uploading "1" file and Domain "Invoices" and Primary language "English"
* Click on Cancel Button to cancel the Instance Creation
* Learning Instance Page is displayed

Validate Additional Form Fields & Table Fields for Domain-Invoices
------------------------------------------------------------------
* Enter all the details to create new instance with uploading "1" file and domain "Invoices" primary language "English" and add form field "Name" and table field "Serial Number"
* Then click on create instance and analyze button,uploaded documents should be classified
* Check Instance Details in Analyze Page
* Ensure uploaded documents should be classified
* Instance view Details page exist
* After classification click on Create Bot Link
* Ensure the added form field and table field for Domain "Invoices" are displayed in designing window

Validate Upload file - Type, limit, size, Count
----------------------------------------------
* Click on browse button of create new instance page and upload 151 files,  limit exceed warning message should be displayed
* Refresh the page
* Click on browse button of create new instance page and upload unsupported file, file type error message should be displayed
* Refresh the page
* Click on browse button of create new instance page and upload above the max file size , limit to under 5 MB per file message should be displayed

Validate Additional Form Fields & Table Fields for Domain- Purchase Order
-------------------------------------------------------------------------
* Enter all the details to create new instance with uploading "3" file and domain "Purchase Orders" primary language "English" and add form field "Name" and table field "Serial Number"
* Then click on create instance and analyze button,uploaded documents should be classified
* Check Instance Details in Analyze Page
* Ensure uploaded documents should be classified
* Instance view Details page exist
* After classification click on Create Bot Link
* Ensure the added form field and table field for Domain "Purchase Orders" are displayed in designing window

Validate Additional Form Fields & Table Fields for Domain- Other
-----------------------------------------------------------------
* Enter all the details to create new instance with uploading "3" file and domain "Other" primary language "English" and add form field "Name" and table field "Serial Number"
* Then click on create instance and analyze button,uploaded documents should be classified
* Check Instance Details in Analyze Page
* Ensure uploaded documents should be classified
* Instance view Details page exist
* After classification click on Create Bot Link
* Ensure the added form field and table field for Domain "Other" are displayed in designing window

Validate Duplicate Field Name
------------------------------
* On Create New Instance Page select "Invoices" From Domain DropDown, add "Invoice Number" as "Form" Field
* Error "No duplicate field names allowed" message should be displayed
* Refresh the page
* On Create New Instance Page select "Invoices" From Domain DropDown, add "Quantity" as "Table" Field
* Error "No duplicate field names allowed" message should be displayed

Create Learning Instance with Multi-Language
--------------------------------------------
* Enter all the details to create new instance with uploading "3" file and domain "Invoices" primary language "German" and add form field "Name" and table field "Serial Number"
* Then click on create instance and analyze button,uploaded documents should be classified
* Check Instance Details in Analyze Page
* Ensure uploaded documents should be classified
* Instance view Details page exist
* After classification click on Create Bot Link
* Ensure the added form field and table field for Domain "Invoices" are displayed in designing window
//Validate multi-language detection in designer [i.e Unicode character detection and preview]

