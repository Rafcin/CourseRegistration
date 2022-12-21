# CourseRegistration

# Table of Contents
1. [Services](#services)
2. [Documents](#documents)
3. [Algorithms](#algorithms)
4. [Database](#database)
5. [How to run](#how-to-run)
6. [How to test](#how-to-test)
7. [Sample Runs](#sample-runs)


## Services
### MongoDB Service
The mongodb service is used to store the data of the application. The data is stored in the following collections:
- **students**: stores the student data
- **faculty**: stores the faculty data
- **courses**: stores the course data
- **sessions**: stores the sessions associated to the courses
The MongoDB client use the offical mongo package under the hood and implements custom methods to easily convert documents to objects, convert objects to documents and perfom CRUD operations.

The Table class exists as a service to print out any Java objects data. Makes it really easy to read.

## Documents
Document contains the following Java classes that in this case act as models for the database items.
- **Student**: represents a student document
- **Faculty**: represents a faculty document
- **Course**: represents a course document
- **Session**: represents a session document

## Algorithms
Algorithm contains the following Java classes that in this case act as models for the database items.
- **Student**: represents a student document
- **Faculty**: represents a faculty document
These classes are used to perform the following operations:
- **Student**: Schedule a student to a course and automagically create the sessions for each course. Students are sorted by GPA and highest GPA receive priority.
- **Faculty**: Schedule a faculty to a course and automagically create the sessions for each course. Faculty are sorted by tenure and those who have tenure teach.

Algorithms extend a base class and can be easily modified and changed in the registrars schedule function. The existing classes
can easily be modified and extended. The student class is a great example of prioritizing students by GPA and the faculty class is a great example of prioritizing faculty by tenure.

## Database
The databse is built on MongoDB. I chose MongoDB because it's a really dead simple document store with great aggregation features. The database is spun up using docker which makes it really easy to persist and deploy anywhere.

## How to run
Just run the test files or the main file and random students and faculty will be generated and scheduled to some hardcoded CS classes.
Fake/generate provides the faker users and classes. Eventually a fake class generator will be added to the project.

## How to test
This application uses JUNIT5 for testing, simple select a class in the test root folder and run to test. It's that easy!

## Sample Runs
Reffer to the `sample-runs` folder in the root directory for sample runs of the application.
Sample runs are labled as S#-F#-C# S# is the number of students, F# is the number of faculty and C# is the number of courses.