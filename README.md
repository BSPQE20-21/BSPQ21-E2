# BSPQ21-E2

[GitHub Pages](https://bspqe20-21.github.io/BSPQ21-E2/)


## Needed commands to execute the project:

1. Remove previous binary files and compile the project:
```bash
  mvn clean compile
```

2. Open MySQLWorkbench and connect to the database


3. Run the Web Server:
```bash
  mvn jetty:run
```

In another cmd window, run the client

4. Run Client App:
```bash
  mvn exec:java -Pclient
```
