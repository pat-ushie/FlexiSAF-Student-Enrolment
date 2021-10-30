Welcome to FlexiSAF Student Enrollment System
==============================================

To run the application, you need a Java application server like Glassfish, JBoss Wildfly, Paraya, etc. 
It has been tested on Glassfish 4.1 and 5. The procedure for running the application is as follows:

(1) Setup a JDBC Connection Pool and a corresponding Resource called: jdbc/student-enrol, to the correspond 
the persistent unit created for the application.  

(2) Pick up the war file at the location <Project-Folder>/dist/StudentEnrolmentSystem.war

(3) Deploy the war file to the server.

(4) Open the project in your IDE - Netbeans preferrably, and locate the Tests at com.flexisaf.ses.tests

(5) Notice that the service URL is pointing to localhost and port 8080. You can change these values as your environment requires.

(6) Start with the StudentCreateTest test as you must get some users in before you can do anything else.

(7) You also need to perform DepartmentCreateTest before you can create students because the service will 
require that you assign a department ID.


Assumptions
============

I had to run with a couple of assumptions:

(1) For a proper balance on my design, I had to create entities for User and Department along with their corresponding services.

(2) Date format over the wire is required to follow the format: YYYY-MM-dd:hh:mm to align with the example given in the challenge.
