# Submission from Khairul Hilmi bin Sidek

## Solution
UI Automation tools : Selenium  
Language            : JAVA  
Framework           : TestNG  
CI tools            : GItHub Actions  
Build tools         : Maven

## Test structure: 

	Root-------Planit-------src----test --------------------------- mainTest.java   }   Main test class  
					|------------testdata --------item.java         } ITEM CLASS  
					|
					|----------- page ------------cart.java         }  
							|-------------contact.java      } PAGE OBJECT MODEL  
				 			|-------------homepage.java     }   
							|-------------shop.java         }  

Main test class   : This is my main test class, with test cases using testNG annotations.  
ITEM CLASS        : This is my test object (item to buy) class. Item name, item quantity and item price declared here.  
PAGE OBJECT MODEL : This is page object classes. All web element and methods for each page are declared here.

## Test strategy:
1. Page object model
2. Object as test data

## Walkthrough: 
Running **mvn test** search for mainTest class. Test Case declared using testNG annotations  
Test Case 1:  
1. From main test class, create a contact page object then call method to click contact page menu item
2. Call chechMandatoryError method which then calls submitForm() method to submit form without entering any field
3. Validate error message using assertion
4. Call fillMandatory() method to fill all mandatory field
5. Validate error is gone using assertion

Test Case 2:  
1. From main test class, create a contact page object then call method to click contact page menu item
2. Call fillMAndatory() method to fill all mandatory field
3. Call submitForm() method to submit form
4. Call checkSubmissionStatus method
5. Wait for progress bar element presence
6. Wait until progress bar element not presence
7. Validate Successful submission message with variable forename in message
8. Run this test case 5 times using testNG invocationCount

Test Case 3:  
1. From main test class, create new item class objects for each item to buy with item name, quantity and item price
2. Create a shop page object then call method to click shop page menu item
3. Call method buyITem for each item to buy passing item object as parameter
4. Click on "Buy" button for item with itemName, loop itemCount times and validate cart item count total
5. Create a cart page object then call method to click on cart menu item
6. Call method checkSubTotal to validate each item subtotal passing item object as parameter, returns subtitle value and saved in local variable
7. Call method checkPrice to validate each item price passing item object as parameter
8. Calculate expected total using local subtotal variables
9. Call checkTotal method to validate total passing expected total

