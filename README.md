# Katalon Automation Project

## WebUI Automation

This project automates a shopping site using Katalon Studio. It demonstrates a **data-driven approach** by reading product data from an Excel file, adding those products to the cart, and proceeding with checkout. The automation also uses **advanced techniques** like parameterized XPaths for robust and reusable test objects.

### Best practices covered
1. Data-driven testing for scalability and maintainability.
2. Use of parameterized XPaths for reusable and robust Test Objects.
4. Clear separation of test data and test logic.
5. Modular test case design for reusability.

---

### Test Cases Automated

- **Login:** Authenticates user using credentials from global variables.
- **Validate Product Count:** Checks if the expected number of products is displayed.
- **Add Single Product to Cart:** Adds a specific product to the cart.
- **Add Multiple Products to Cart:** Reads product names from Excel and adds each to the cart.
- **Logout:** Logs out and closes the browser.

---

### Test scenario automated

1. **Login:**  
   Uses a custom keyword to log in with provided credentials.
2. **Validate Product Count:**  
   Verifies the number of products displayed matches the expected count.
3. **Add Products:**  
   - **Single Product:** Adds a hardcoded product.
   - **Multiple Products:** Iterates through Excel data and adds each product.
4. **Logout:**  
   Logs out and closes the browser.

---

### Data-Driven Approach

- Product names and details are stored in an Excel file.
- The test case `addMultipleProducts` reads each row and adds the corresponding product.
- This allows easy scaling: just update the Excel file to change test coverage.

### Advanced Automation Techniques

#### Parameterized XPaths in Test Objects

- Test Objects use parameterized XPaths to dynamically locate elements based on test data.
- Example: The XPath for the "Add to Cart" button is parameterized with the product name, allowing the same Test Object to work for any product.

```groovy
// Example parameterized XPath in Katalon
div[text()='${productName}']/ancestor::div[@class='inventory_item']//button[text()='Add to cart']
```

During test execution, ${productName} is replaced with the actual product name from the Excel data.

Also using the product as a link we navigate to it's ancestor inventory item and then we are finding the "Add to cart" button for the product

![xapth-axes](/Docs/img/Screenshot%202025-07-27%20212802.png)

### Test Execution and details

1. This project uses [saucedemo](https://www.saucedemo.com/) practice site, The dev profile has been selected as default and this profile contains all the Global variables, refer if you want to change any key-value pairs

2. A sample test suite [validateProducts](/Test%20Suites/validateProducts.ts) has been created. Use this test suite to run the test cases

3. Reports of the test execution are stored under [reports](/Reports/) folder. Example: [HTML](/Reports/20250727_121435/validateProducts/20250727_121435/20250727_121435.html) is usefull as it captures steps details with embeded screenshots