# yahtzee

## Requirements
- Java 1.8

## Running
1. Export as a JAR (in this example, the file is named yahtzee.jar).
2. Navigate to the directory where the JAR was saved.
3. Run these commands (replace 8002 with your desired port number):
```
java -cp yahtzee.jar com.yahtzee.core.Server 8002
java -cp yahtzee.jar com.yahtzee.core.Client localhost 8002
```

## Running Cucumber Tests
Instructions assume use of Eclipse IDE.
1. Right Click on RunCucumberTest.java
2. Run As > JUnit Test
3. Profit!
