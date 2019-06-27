HelloWorld

1. User should be able to add a person to this app by sending request to backend
   POST /users?name=parameter
   
   DB             parameter     DB change
   [A]              A           [A]
   [A]              1           [A]
   [A]              a           [A]
   [A]              B           [A, B]
   [A, B]           B           [A, B]
   [A, B]           b           [A, B]
   [A, B]           1           [A, B]
   [A, B]           C           [A, B, C]

2. The server should return the list of person's name and time (greeting)
   GET /
   Assumption: the sequence of name is not sorted, just as it is returned from db

   DB           output
   [A]          Hello A, the time on the server is 10:59pm on 14 March 2018
   [A]          Hello A, the time on the server is 02:05pm on 2 May 2019
   [A, B]       Hello A and B, the time on the server is 10:59pm on 14 March 2018
   [A, B, C]    Hello A, B and C, the time on the server is 10:59pm on 14 March 2018

3. User should be able to remove a person, not yourself, from the app
   DELETE /users?name=parameter
   
   DB             parameter     DB change     output
   [A]              A           [A]         Sorry, you cannot delete yourself from your world
   [A]              a           [A]         Sorry, you cannot delete yourself from your world
   [A]              B           [A]         Sorry, you cannot delete, user B does not exist
   [A, B]           A           [A, B]      Sorry, you cannot delete yourself from your world
   [A, B]           B           [A]         You have successfully deleted user B
   [A, B]           b           [A]         You have successfully deleted user B
   [A, C]           B           [A, C]      Sorry, you cannot delete, user B does not exist
   
4. User should be able to get a list of the users' names without greeting
   GET /users
   
   DB               output
   [A]              A
   [A, B]           A, B
   
5. User should be able to change the name of person on the list
   PUT /users?name=para&newName=parameter

   DB               parameter        DB change     output
   [A]              A->A             [A]           Sorry, you cannot change default user's name
   [A]              A->1             [A]           Sorry, you cannot change default user's name
   [A]              A->a             [A]           Sorry, you cannot change default user's name
   [A]              A->B             [A]           Sorry, you cannot change default user's name
   [A]              B->C             [A]           Sorry, user B does not exist, you cannot change the name
   [A, B]           B->A             [A, B]        Sorry, user A is already in the list
   [A, B]           B->C             [A, C]        You have successfully changed user name from B to C
   [A, B]           B->b             [A, B]        Sorry, user B is already in the list
   [A, B]           C->B             [A, B]        Sorry, user C does not exist, you cannot change the name
   [A, B]           C->D             [A, B]        Sorry, user C does not exist, you cannot change the name
   [A, B, C]        B->C             [A, B, C]     Sorry, user C is already in the list

6. User cannot remove himself from the list

7. Every name on the list is unique, user cannot add another person who has the same name and cannot edit the name to any existing name
