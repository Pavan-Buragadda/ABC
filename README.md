# Assignment Project Setup Guide

## Prerequisites

- Java 17 JDK
- Maven
- IntelliJ IDEA (recommended IDE)

## Setup Steps

1. Install Java 17

   - Download and install Java 17 from Oracle or OpenJDK
   - Set JAVA_HOME environment variable

2. Install Maven

   - Download Maven from https://maven.apache.org/
   - Add Maven to system PATH

3. IDE Setup

   - Download and install IntelliJ IDEA
   - Open project in IntelliJ IDEA
   - Wait for automatic Maven sync
   - Alternatively, refresh Maven dependencies manually:
     - Right-click on `pom.xml`
     - Select "Maven -> Reload project"

4. Run Application
   - Locate the main class
   - Click the green play button in IntelliJ
   - Or use the Maven run configuration

## Alternative Run Methods

Using Maven command line:

```bash
mvn clean install
mvn spring-boot:run
```
