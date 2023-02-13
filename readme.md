# SocialNetworkPosts #

App uses H2 database to store Users and their posts.

When we run application it is initialized with 2 users and 13 posts for them.</br>
#### Accessing h2 console
&nbsp;&nbsp; url: http://localhost:8080/console </br>
&nbsp;&nbsp; login: sa </br>
&nbsp;&nbsp; login: pass </br>

#### Endpoints
`baseUrl` = http://localhost:8080
* PostsController
  * `${baseUrl}/posts/rank/max/{limit}` </br>
  &nbsp;&nbsp; It returns List of posts having most views. </br>
  &nbsp;&nbsp; Parametr `limit` specifies how many posts we want to get. </br>
  </br>
* UsersController
  * `${baseUrl}/users/delete/{id}` </br>
&nbsp;&nbsp; It deletes user in database. </br>
&nbsp;&nbsp; Parametr `id` specifies id of the user. </br>
  * `${baseUrl}/users/all` </br>
&nbsp;&nbsp; Returns all users from database. </br>
  * `${baseUrl}/users/save/{newUserJson}` </br>
&nbsp;&nbsp; It saves new user into database. </br>
&nbsp;&nbsp; Parametr `newUserJson` specifies new user object to be saved in database. </br>
  * `${baseUrl}/users/update/{existingUserJson}` </br>
&nbsp;&nbsp; It updates existing user in database. </br>
&nbsp;&nbsp; Parametr `existingUserJson` specifies existing user object to be saved in database. </br>


#### Example of Json Object used as parameter for deletion, update, saving operations </br>
`{"userId":1,
"login":"loginUserOne",
"name":"nameUserOne",
"surname":"surnameUsrOne",
"nick":"nickUserOne"}`