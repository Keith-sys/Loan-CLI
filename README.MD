# LOAN Calculator
A demo program for calculating and evaluating your loan using the [amortization](https://en.wikipedia.org/wiki/Amortization_(accounting)) in java.

## Requirements
The requirements for the project is:
- [Oracle JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

## Features
The containing feature are:
- Calculate the monthly payments of your mortgage
- Evaluate if it's favorable based on your gross income
- Calculate the monthly payment of your car loan
- View the balance sheet of your loan


## Build Instructions
Step 1) Build source files
```
javac src/main/java/org/keith/core/*.java src/main/java/org/keith/menu/*.java src/main/java/org/keith/menu/enums/*.java  src/main/java/org/keith/*.java -d bin
```

Step 2) Run the program
```
cd bin
java io.keith.Main
```

Optionally, you can create an executable JAR file:
```
cd bin
jar -cmvf ../META-INF/MANIFEST.MF ./loan-cli.jar .

java -jar loan-cli.jar
```

## License
This project contains [MIT license](./LICENSE).
