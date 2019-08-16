# flipkart-automation
Search for a product, apply filter, select nth result => add to Cart if available, else add to Wish List

Problem statements:
Add Product to cart
- 
- [ ]	Open https://www.flipkart.com/ and Search for a product “Samsung Mobile”
- [ ]   Filter price by 10000 and above
- [ ]   Select on the third result
- [ ]   On the product description page, validate the name is same as the product selected
- [ ]	If validation is success, add the product to the cart if it is available
- [ ]	Add to Wish list if product is out of stock


Instructions to run the tests:
-
This is a Java Maven project that contains dependencies for Selenium, TestNG, Extnt Report(for reporting) and Apache POI(for reading Excel file)

Please follow the following steps to execute the Test Cases
- 
-	Import the project to IDE and run a fresh maven build to import all the dependencies defined in pom.xml in project root folder
-	Tests can be run in either of the 2 ways:
        Either right-click on testng.xml present in the root folder and select run. This executes all the test methods specified in the testing.xml file present inside project root folder Or
        Right-click and run TestRunner.java class. TestRunner.java class contains the Java main method. This will pick up Test Cases from the excel sheet “testcases.xlsx” placed under resources folder. Only those Test Cases that has Execution Flag = Y will be picked up; it will dynamically create “customTestNG.xml" file with only those Test Cases that has Execution Flag = Y in the excel sheet; it will run “customTestNG.xml file


Features of this framework are as follows:
-
-	Framework is of **Page Object Model** design pattern
-	Supports **cross-browser execution** for Chrome, Firefox and IE browsers
-	Has Fluent Interface mechanism that chains the page-related operations
-	**Extent Report** has been used to generate logs of Test Case execution. This log can be found in **test-output/FlipkartTest.html** inside the project root folder.
-	In case of Test Case failure, it will capture the screenshot after that particular step where the test failed and store it inside **test-output/ErrorScreenshots** folder
