HelloWorld

1. User should be able to add a person to this app by sending request to backend
   POST /users
   
   DB             input       DB change
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
   [A]          [Hello A, the time on the server is 10:59pm on 14 March 2018]
   [A]          [Hello A, the time on the server is 02:05pm on 2 May 2019]
   [A, B]       [Hello A and B, the time on the server is 10:59pm on 14 March 2018]
   [A, B, C]    [Hello A, B and C, the time on the server is 10:59pm on 14 March 2018]

3. User should be able to remove a person, not yourself, from the app
   DELETE /users/{name}
   
   DB             input       DB change     output
   [A]              A           [A]         [Hello A, the time on the server is 10:59pm on 14 March 2018]
   [A]              1           [A]         [Hello A, the time on the server is 10:59pm on 14 March 2018]
   [A]              a           [A]         [Hello A, the time on the server is 10:59pm on 14 March 2018]
   [A, B]           A           [A, B]      [Hello A and B, the time on the server is 10:59pm on 14 March 2018]
   [A, B]           B           [A]         [Hello A, the time on the server is 10:59pm on 14 March 2018]
   [A, B]           b           [A]         [Hello A, the time on the server is 10:59pm on 14 March 2018]
   [A, C]           B           [A, C]      [Hello A and C, the time on the server is 10:59pm on 14 March 2018]
   
4. User should be able to get a list of the users' names without greeting
   GET /users
   
   DB               output
   [A]              A
   [A, B]           A, B
   
5. User should be able to change the name of person on the list
   PUT /users/{name}

   DB               input          DB change
   [A]              A->A           [A]
   [A]              A->1           [A]
   [A]              A->a           [A]
   [A]              A->B           [A]
   [A]              B->C           [A]
   [A, B]           B->A           [A, B]
   [A, B]           B->C           [A, C]
   [A, B]           B->b           [A, B]
   [A, B]           C->B           [A, B]
   [A, B]           C->D           [A, B]

6. User cannot remove himself from the list

7. Every name on the list is unique, user cannot add another person who has the same name and cannot edit the name to any existing name
