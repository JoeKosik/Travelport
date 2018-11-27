~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Hospitality - Travelport 
CODING EXERCISE TO REVIEW AND DISCUSS DURING THE TECHNICAL INTERVIEW
Here is the free public Rest Api that returns all states in US:

http://services.groupkt.com/state/get/USA/all

1.	Consume the above api using Java, Spring , Jersey 2.0 and any other open source tool you want.
2.	Convert the JSON response to Java Object.
3.	Remove all states except for Alabama and Georgia and return it back using below endpoint in step 4.
4.	Expose an endpoint to return above response:
	4.1	The above endpoint should only return Alabama and Georgia.
	4.2	Design your endpoint naming convention and utilize appropriate Rest Method to return the response back.
5.	Add any exception catching, proper naming convention, junit tests.
6.	Create war file so it can be deployed in Tomcat 8.
7.	It has to be a maven project so we can build the project and generate war file.
8.	Send the code, war file and the endpoint uri. 
9.	Bonus: If you can publish in Github so we can download it.
10.	Bonus: If you can pass in the states name in the URL and filter it out based on the input. If no states are passed it should return all the states.		
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Official URLS as examples:  
	1) http://localhost:8080/kosik.joe.solution-0.0.1-SNAPSHOT/states/all
	2) http://localhost:8080/kosik.joe.solution-0.0.1-SNAPSHOT/state/{stateAbbrev}
	3) Line_Item_04_EndPoint_01: http://localhost:8080/kosik.joe.solution-0.0.1-SNAPSHOT/states/1/AL&GA
	4) Line_Item_04_EndPoint_02: http://localhost:8080/kosik.joe.solution-0.0.1-SNAPSHOT/states/2/AL&GA