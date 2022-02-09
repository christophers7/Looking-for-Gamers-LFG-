# Looking for Gamers

___

## Project Descriptions


Looking for Gamers (LFG) is a service that users find and join other gamers for short-term collaborative gaming. Users connect their steam profiles to their account and are able to view and join various game groups currently looking for gamers. Players can also choose to host their own groups, allowing them to reject or deny the users based on their stats with the respective game. 

---

## Technologies Used


- Java JDK 8
- Spring Data JPA
- Spring Web
- Spring Boot
- Spring AOP
- Angular
- Jenkins
- JWT
- Mockito
- EC2
- S3 Buckets
- RDS
- JUnit 5
- Jasmine
- Karma
- H2 Database
- Postman
- PostgreSQL

---

## Features


- Third Party API connection through Spring RestTemplates retrieving user Steam Profiles and game data
- Jenkins pipeline with webhook integration with GitHub building on updates to main branch
- Full unit testing using JUnit 5, Mockito, H2 Memory database, Postman automated testing
- Behaviour Driven Development Testing with Jasmine and Karma on Angular
- Real time updating of user activity allowing fast interactions between other clients while still maintaining RESTful systems
- JWT authentication and generation

---

## Getting Started


Git Back clone command:
* git clone https://github.com/christophers7/Looking-for-Gamers-LFG-.git

Environment setup:
* JDK 8 or higher

---

## Usage

Upon accessing the page, users will be brought to the login view.  
![Login](https://user-images.githubusercontent.com/74217280/153306423-74fa6f86-1a58-42b2-baff-00956bcfc1d7.PNG)

If a user has not created a profile, the user may do so by clicking "New User?" and entering in all of the following fields:  
![New user](https://user-images.githubusercontent.com/74217280/153306668-9a82973d-3dea-406b-bb4d-a80c892d87c5.PNG)

Upon logging in for the first time, the user will be asked to link their Steam profile by entering their Steam ID. After which they may view their achievements and be taken to their profile:  
![Profile](https://user-images.githubusercontent.com/74217280/153306854-d312cc18-f693-4975-86fd-540013d4ad23.PNG)

From the profile, a user may change their credentials:  

![Update credentials](https://user-images.githubusercontent.com/74217280/153306963-773bb673-87ae-4b7f-bade-fe66dad81d7c.PNG)  

and update their user information:  

![Update info](https://user-images.githubusercontent.com/74217280/153307004-fb4b3d0b-b150-4b5a-a09e-2e13999143bf.PNG)  

![Profile updated](https://user-images.githubusercontent.com/74217280/153307033-718055ca-c0c1-498f-9c4d-da9a7def5b1c.PNG)  

Users may then access the main page where they can view the available games and see how many groups are looking for more members:  
![Main](https://user-images.githubusercontent.com/74217280/153307193-76dfdd41-b6f5-451b-a134-f097690d1e62.PNG)  

Upon clicking a game, users are brought to the group select view for that game:  
![Group select](https://user-images.githubusercontent.com/74217280/153307284-cf29e54f-2030-427c-92d4-9e1b08f3eac0.PNG)  

A user may click "Request to Join" to be put in a waiting list for the group:  
![Waiting room](https://user-images.githubusercontent.com/74217280/153307427-65ce36a9-6b08-489a-a276-c0e670820510.PNG)  

By clicking the "Create Group" button in the Group Select view, user will be able to create their own group by filling out the following information:  
![Group creation](https://user-images.githubusercontent.com/74217280/153307595-226a44bd-a55f-4d59-9a94-4bc51ee63eb8.PNG)  

The user will then be hosting a group and may view Steam achievements and accept or deny interested applicants:  
![Host view](https://user-images.githubusercontent.com/74217280/153307803-18b0e189-beaf-4b58-9685-2f65182ea098.PNG)  

If the host accepts an applicant, the user will then be added to the group:  
Host view:  
![Host accept](https://user-images.githubusercontent.com/74217280/153307903-0c8f3867-9227-4657-8e8f-cc088905fa95.PNG)  

User view:  
![Group member view](https://user-images.githubusercontent.com/74217280/153307930-fc4e48d4-a117-42d9-8507-149bacd48a34.PNG)  

Once the group is filled, the host will see each applicant's Steam profile, from where the host can then add them on Steam.  


---

## Contributors


- Christopher Scullin - https://github.com/christophers7 
- Brian Arayathel - https://github.com/dementedtiger
- Kymon Williams - https://github.com/Meezy98 
- Mike Maschmeir - https://github.com/mekha454 
