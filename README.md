Version 0 of Movie Review System.

Movie Review Platform
We have a requirement to implement a platform related to Movie reviews, and following is the description of it.

Platform Overview:
Movie review platform collects reviews for Movies from its users. Later these movie reviews are used to derive insights which helps in enriching the lives of its customers with entertainment. 

Platform Internal Capabilities:
Users of the Platform can review only Movies which are released so far, they cannot review upcoming movies. 
Users can give a review-score between 1 to 10. (Higher the score the better the liking for the movie). Currently we are not allowing a user to review the same Movie multiple times.
Platform by default on-boards a user as a ‘viewer’. Later a ‘viewer’ can be upgraded to a ‘critic’ after he/she published more than 3 reviews for various movies. 
Critics are considered as experts in the judgement here, so critics reviews will be captured with more weightage. i.e. 6 review rating of a critic will be considered as 12 (2x) NOTE: Older reviews by users as `viewer` shall not be affected .
[Good To Have] Based on the users behaviour Platform should give capability to plugin more user upgradation policies from. Eg. User ->Critic -> Expert ->Admin

Requirements:
Each of the following features needs to be implemented:
Adding Users and Movies.
User to review a Movie.
List top n movies by total review score by ‘viewer’ in a particular year of release.
List top n movies by total review score by ‘critics’ in a particular genre.
[Good To Have] Average review score in a particular year of release.
[Good To Have] Average review score in a particular genre.

Expectations:
Make sure that you have working and demonstrable code for all the above requirements.
Feature requirements should be strictly followed. Work on the expected output first and then add good-to-have features of your own.
Use of proper abstraction, separation of concerns is required.
Code should easily accommodate new requirements with minimal changes. Use of strategies for filtering criteria is encouraged. 
Proper exception handling is required.
Code should be modular, readable and unit-testable.

Important Notes: 
Do not use any database store, use in-memory data structure. 
Do not create any UI for the application.
Do not build a Command line interface, Executing test cases or simple Main function should be sufficient

Sample Test Cases:
Onboard 10 movies onto your platform in 3 different years.
Add Movie("Don" released in Year 2006 for Genres “Action” & “Comedy”)
Add Movie("Tiger" released in Year 2008 for Genre “Drama”)
Add Movie("Padmaavat" released in Year 2006 for Genre “Comedy”)
Add Movie("Lunchbox-2" released in Year 2030 for Genre “Drama”)
Add Movie("Guru" released in Year 2006 for Genre “Drama”)
Add Movie("Metro" released in Year 2006 for Genre “Romance”)
Add users to the system:
Add User(“SRK”)
Add User(“Salman”)
Add User(“Deepika”)
Add Reviews:
Add Review(“SRK”, “Don”, 2)
Add Review(“SRK”, “Padmavaat”, 8)
Add Review(“Salaman”, “Don”, 5)
Add Review(“Deepika”, “Don”, 9)
Add Review(“Deepika”, “Guru”, 6)
Add Review(“SRK”,”Don”, 10)  - Exception multiple reviews not allowed
Add Review(“Deepika”, “Lunchbox-2”, 5) - Exception movie yet to be released
Add Review(“SRK”, “Tiger”, 5). Since ‘SRK’ has published 3 reviews, he is promoted to ‘critic’ now.
Add Review(“SRK”, “Metro”, 7)
List top 1 movie by review score in “2006” year:
Top in Year “2006”:
	Output: Don - 16 ratings (sum of all ratings of Deepika, Salman & SRK)
List top 1 movie by review score in “Drama” genre:
	Output: Guru - 6 ratings

