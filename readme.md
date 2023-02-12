# SocialNetworkPosts #

App uses H2 database to store Users and their posts.

When we run application it is initialized with 2 users and 13 posts for them.</br>
#### Accessing h2 console
&nbsp;&nbsp; url: http://localhost:8080/console </br>
&nbsp;&nbsp; login: sa </br>
&nbsp;&nbsp; login: pass </br>

#### Endpoints
baseUrl=http://localhost:8080
* PostsController
  * `${baseUrl}/posts/rank/max/{limit}` </br>
  &nbsp;&nbsp; It returns List of posts having most views. </br>
  &nbsp;&nbsp; Parametr `limit` specifies how many posts we want to get. </br>
  </br>

