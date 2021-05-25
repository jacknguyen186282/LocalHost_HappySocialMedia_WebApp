# LocalHost_HappySocialMedia_WebApp
  I/ INTRODUCTION

In recent years, with the development of society and information technology, the internet and social networking sites have had a great influence on all aspects of people's daily life. In particular, social media network gives people many conveniences such as instantly information, various types of contents and also allows them to connect, exchange, share stories, personal ideas and activities over the network. Hence, our team, as a group of students from RMIT University, have decided to build a simple Happy Social Media web application for users to express their feelings and thoughts on the platform. This web can allow users to create an account to sign in to the home page by using individual email and be able to post stories on the main page as well as like, comment on other user's posts. 

This is the final project of Enterprise Application Development course in RMIT University.

 II/ PREREQUISITES
  
In order to successfully run the Happy Socical web app, there are some recommended insallation that are listed below:

1) IDE:
  
  - IntelliJ IDEA: https://www.jetbrains.com/idea/promo/?gclid=CjwKCAjw-qeFBhAsEiwA2G7Nl0qQ6XY2m1MtMQEMQR7O-I_uVOjQZjchYHz9Jhxe6a0qPzvdRF2gCBoCQJEQAvD_BwE
  - Visual Studio Code: https://code.visualstudio.com/

2) Front end 

  - React JS: This is a JavaScript library for building user interfaces. In this project, we will use version 17.0.2. Thus, to prepare the UI with React JS, first, you need to install the npm and Node.js. Then, you can create a React application by installing the create-react-app via your terminal by running the command "npm install -g create-react-app". Finally, you can create a project based on React with the command "npx create-react-app myfirstreact" in the terminal (myfirsreact is an example of the name of the project).

3) Back end 

  - Spring Boot: This is an open-source Java-based framework that is used to create a microservice. In this project, Spring Boot is the backbone for our backend, hence, you need to visit the link (https://start.spring.io/) to start initializing the project as same as the picture below:

![image](https://user-images.githubusercontent.com/58252249/119272486-524a8680-bc30-11eb-8b2d-b5ab7194b133.png)

  - Maven: We use maven to build and manage dependencies, the recommended maven version for this project is 3.8.1.

4) Database 
  - PostgreSQL: In this project, we will a Postgre as a database to store all the data of our web application, you can download Postgre by this link (https://www.postgresql.org/download/). Then, in the server PostgreSQL 13, you should create the database with the name HappyTwitter with password "rmit" for stroring data. 

  III/ RUNNING APPLICATION
  
Firstly, downloading the front end and back end code on the github and extract two zip files on your computer. Then open the back end code with IntelliJ, you should check the spring.datasource.password is "rmit" or not. Then normally run the code on the IntelliJ.

Secondly, open the front end code with Visual Studio, next in the terminal of the IDE, enter the command the "npm install" and wait for a moment, then enter "npm start".

Finally, you can visit (http://localhost:3000/) and enjoy our Happy Social Media!!!

  IV/FIX COMMON BUGS (IF IT APPEARS)
  
  If you run the back end code on IntelliJ and encounter the error "Did you mean null?" in PostMapper.java and CommentMapper.java, there are 3 recommended ways to fix this in order to smoothly run our websites
  
  - Method 1: In the PostMapper.java, you should comment out all the code lines that have the annotation Mapping (@Mapping). Then repeat the same with the CommentMapper.java
    
![image](https://user-images.githubusercontent.com/58252249/119513109-20efc900-bd9e-11eb-9584-e58055747a62.png)
    
   Then you can run the code, ignore all the error in the IDE's console. Finally, you can uncomment those @Mapping and normally run the code again. It should work this time.
   
   - Method 2: In IntelliJ, recompile (key shortcut CTRL + SHIFT + F9) every single file in folders: Model, Dto (a file CommentDto and files in two subfolders request, response), Repository, and then PostMapper, and CommentMapper. Later, after successful recompiling, there are two files PostMapperImpl, and CommentMapperImpl in a folder annotation (from folder target > generated-sources > annotation).

![image](https://user-images.githubusercontent.com/58252249/119515554-2bab5d80-bda0-11eb-87ed-2792488971cc.png)

![image](https://user-images.githubusercontent.com/58252249/119515710-4d0c4980-bda0-11eb-969a-ab18f7cbdca2.png)
   
   - Method 3: In the pom.xml, you can change the version of lombok from 1.18.20 to 1.18.8.

![image](https://user-images.githubusercontent.com/58252249/119513928-d7ec4480-bd9e-11eb-82c4-a51a96f194ae.png)

  V/ AUTHORS
  
  - Nguyen Tran Phu
  - Nguyen Van Tong
  - Nguyen Minh Nhat
  - Tran Thien Van

  VI/ ACKNOWLEDGEMENTS
  
  [1] FreeCodeCamp, Spring Boot and Angular Tutorial - Build a Reddit Clone (Coding Project), Accessed: May-15-2021, Available [Online]: https://www.youtube.com/watch?v=DKlTBBuc32c&ab_channel=freeCodeCamp.org
  
  [2] Jwt.io ,"JWT.IO - JSON Web Tokens Introduction", 2021. [Online]. Available: 
https://jwt.io/introduction.

  [3] Mapstruct.org ,"MapStruct – Java bean mappings, the easy way!", 2021. [Online]. 
Available: https://mapstruct.org/.

  [4] React-redux.js.org ,"Hooks | React Redux", 2021. [Online]. Available: https://reactredux.js.org/api/hooks.
