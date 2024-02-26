# User authentication and authorization
This repository is example of how to use JWT authentication and authorization with springboot 

Run jar : java -jar user-0.0.1-SNAPSHOT.jar

prerequest
----------
Mysql need to be installed
Need to set mysql properties in application.property file (like db port, username, password) 

CURL Command
------------
1) For Signup
    curl --location --request POST 'localhost:8090/api/auth/signup' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=BE874A9F76E0C29C9437AB5D3E3030AF' \
--data-raw '{
    "email":"Test@Test.com",
    "password":"M@123"}'

   Response
   --------
   {"message":"User registered successfully!"}

 3) For Signin
      curl --location --request POST 'localhost:8090/api/auth/signin' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=BE874A9F76E0C29C9437AB5D3E3030AF' \
--data-raw '{
    "email":"Test@Test.com",
    "password":"M@123"}'

    Response
    --------
    {"token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZXN0QFRlc3QuY29tIiwiaWF0IjoxNzA4NzMyMjM4LCJleHAiOjE3MDg3MzI1Mzh9.
    rRieZKmDqVlEp1_VIkcrv9gmrNAAsqFOQSslVlLc98sYsy6yV_i3CVZfeavBXNoHMI4brAHxfvJgzGZ-VyDXYA","type":"Bearer",
    "refreshToken":"55623a1e-73f7-4f21-8858-    d2a9165e2ddd","email":"Test@Test.com"}  

5) For Sample api test
     curl --location --request GET 'localhost:8090/api/test/user' \
--header 'Authorization: Bearer ADD_TOKEN' \
--header 'Cookie: JSESSIONID=BE874A9F76E0C29C9437AB5D3E3030AF'

    Response
    --------
    User Content.

7) For renew access token using reference token
     curl --location --request POST 'localhost:8090/api/auth/refreshtoken' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=BE874A9F76E0C29C9437AB5D3E3030AF' \
--data-raw '{
    "refreshToken":"ADD_REFERENCE_TOKEN"}'

   Response
   --------
   {"accessToken":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZXN0QFRlc3QuY29tIiwiaWF0IjoxNzA4NzMyNTMxLCJleHAiOjE3MDg3MzI4MzF9.
   roqmr3iMLLtMCzwfha_TrWigRAGhA1yt9bDwlY12ItmQnuwBUsnCTjfesZeiUz-seYjsODhWNEVTo_xC9-rO_g","refreshToken":"55623a1e-73f7-4f21-8858-d2a9165e2ddd","tokenType":"Bearer"}


   Postman Screenshot
   ------------------
   1) User signup

      <img width="1305" alt="signup" src="https://github.com/kingaj/user_registration/assets/2405218/2fe35936-754c-4aac-a8f2-b5e9da35735b">

   2) User signin

      <img width="1302" alt="Screenshot 2024-02-24 at 4 45 53 AM" src="https://github.com/kingaj/user_registration/assets/2405218/fbd35635-9c59-4ab2-8905-2b7e1468a7ac">

   3) Call user sample api

      <img width="1315" alt="user" src="https://github.com/kingaj/user_registration/assets/2405218/308acc6c-285d-474b-837c-81c6e7d944c7">  
      
   5) User sample api with token expire error

      <img width="1322" alt="userApiWithError" src="https://github.com/kingaj/user_registration/assets/2405218/9d21af12-2b7f-4850-a4cb-9bbad5dd2c03">

   6) Renew access token from call reference token api

      <img width="1339" alt="referenceToken" src="https://github.com/kingaj/user_registration/assets/2405218/26be535e-6e1e-4fff-97b2-025f3774e211">

   7) When reference token expire then user need to re-signin

      <img width="1314" alt="Screenshot 2024-02-24 at 4 57 35 AM" src="https://github.com/kingaj/user_registration/assets/2405218/af4a7fd1-e351-4dc5-9353-7c74fc32904a">


   CURL Command with mysqldb screenshot
   ------------------------------------
   1) User signup

      <img width="1229" alt="userSignupCrul" src="https://github.com/kingaj/user_registration/assets/2405218/0c43edc3-bf15-44a3-971a-3b6eab61361c">
      
   2) User signin

      <img width="672" alt="signinCrul" src="https://github.com/kingaj/user_registration/assets/2405218/c5ae4ba0-0271-45c3-8679-dccf542bcff2">

   3) Call user sample api

      <img width="591" alt="userCrul" src="https://github.com/kingaj/user_registration/assets/2405218/ca1b34b8-01c6-4239-a851-b8d22b55b5a8">

   4) User sample api with token expire error

      <img width="596" alt="tokenExpirer" src="https://github.com/kingaj/user_registration/assets/2405218/136cf11b-808f-42d7-b9bd-cd6cf65c33ee">

   5) Renew access token from call reference token api

      <img width="621" alt="referenceTokenCrul" src="https://github.com/kingaj/user_registration/assets/2405218/1560426f-fab4-425c-9c15-437b9517d685">

   6) When reference token expire then user need to re-signin

      <img width="782" alt="Screenshot 2024-02-24 at 5 50 20 AM" src="https://github.com/kingaj/user_registration/assets/2405218/f8b31f20-e9bf-4e2c-8713-ba36eddf6a65">
