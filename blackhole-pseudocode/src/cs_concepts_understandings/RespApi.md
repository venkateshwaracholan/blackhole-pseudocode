
Rest Api: - Representational state transfer

CRUD - create read update delete

read:
GET - read
collection GET /calls
member GET /call/1

create:
POST - create
POST /calls - (collection only)
body: {}

update:
PUT - Full updates
PUT /call/1
body:{} all attributes
PUT /calls
body:{} all attributes of all calls

PATCH - partial updates - (member only)
PATCH /call/1
body:{} only modified attributes

delete:
Delete - remove
member DELETE /call/1
collection DELETE /calls

post is collection only
pathc is member only
member and collection applies for everything except POST and PATCH.


Rest API:

Idempotent
client server architecture
layered system
Cacheability
resourceful
stateless
uniform interface



200+ means the request has succeeded.
300+ means the request is redirected to another URL
400+ means an error that originates from the client has occurred
500+ means an error that originates from the server has occurred



idempotency and caching: describe
   
https://pradeeploganathan.com/rest/rest-idempotency-safety/

HTTP Method	Safe	Idempotent
 GET	        Yes	 Yes
 POST	        No	 No
 PUT	        No	 Yes
 PATCH          NO       NO
 DELETE	        No	 Yes
 HEAD	        Yes	 Yes
 OPTIONS	Yes	 Yes


