/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curated_lists.interviews;

/**
 *
 * @author vshanmugham
 */
public class clear_tax_aug_2020 {
  
}


/*

Problem - Build Twitter

--


Functionaliies
--

users
profile

followers
following

tweets
likes, comments
contents: text, image, video


timeline - tweets from following, 


Expectations
--
High Level Design
	Components
	Their interactions -> Data flow between them

Low Level Design
	Specific Components

----

user has many followers(users)
user has manu following(users)

tweet
user id, information

comments




high level:

load balancer
global routing table
app servers
timeline service
sharded relational database 
caching
s3

Components

App Servers
1,2,3
Timeline Service
4


APIs
user apis - crud
follow api - user_id
tweet api - crud, likes comments
timeline API


TimeLine Population
--
fetch tweets from following(users)

1,2

users
id, name, email

tweets
id, user_id, tweet_text, tweet_id

likes
id tweet_id user_id



tweet_attachments
id tweet_id type type_id

polls

image
id attachment_id
video
id attachment_id

attachments
id s3_url type



following
id user_id following_id updated_at
1     1            2
2     3            2


*/
