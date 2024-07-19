# ABAS ERP Java Developer Interview Project

This project contains the tasks requested during the interview process for the Java Developer position at ABAS ERP. The project includes Java programs that analyze the given order table and a simple API integration example.

## Project Structure

The project consists of the following main components:

1. `Order.java`: Class representing order data
2. `AbasERPCase.java`: Main class containing solutions using the Stream API
3. `AbasERPCaseWithoutStreams.java`: Alternative class containing solutions using traditional loops and collection operations
4. `APIExample.java`: Example class demonstrating HTTP GET and POST requests

## Technologies Used and Reasons

### 1. Java 8+ Stream API

The Stream API is used in the `AbasERPCase.java` class to demonstrate modern Java programming techniques. Reasons for choosing the Stream API:

- More readable and concise code
- Functional programming approach
- Ease of parallel processing
- Lower probability of making errors

### 2. Traditional Loops and Collections

In the `AbasERPCaseWithoutStreams.java` class, the same problems are solved using traditional methods. Reasons for including this approach:

- To demonstrate basic Java knowledge
- To provide an opportunity to compare different solution methods
- To show understanding of pre-Stream API approaches

### 3. Java HTTP Client (Java 11+)

The modern Java HTTP Client is used in the `APIExample.java` class. Reasons for this choice:

- To demonstrate the ability to use current Java features
- To provide an internal solution requiring fewer dependencies
- To support asynchronous operations

## Solution Approach

1. **Data Modeling**: The `Order` class was created to represent each order item. This allows us to process the data in an object-oriented manner.

2. **Data Analysis**: Using the Stream API and traditional methods, the following analyses were performed:
   - Calculating total amount per order
   - Calculating overall average price
   - Calculating average price per product
   - Analyzing quantity by product and order

3. **API Integration**: A simple REST API integration example is provided. This example demonstrates the ability to exchange data in real-world scenarios.



## Conclusion

This project aims to demonstrate my skills in data analysis, object-oriented programming, modern and traditional approaches, and API integration in the Java programming language. The presentation of different solution methods emphasizes my flexibility in approaching problems and ability to adapt to different scenarios.