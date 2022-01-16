FILE NAME: Makale_HashtagUserIDs
There are 2 #TEDU users.
Their detailed information can be found.
Sample data from file:
<USERID> (User ID of the hashtag owner [lets say 1]) </USERID>
-----------------------------------------
FILE NAME: Makale_FriendsIDs
The data of the friends of the 3 Hashtag Owners
The detailed information of the friends of the hashtag owners can be found.
Sample data from the file:
<FRIENDSUSERID> (User ID [1] of the hashtag owner) </FRIENDSUSERID> 
<USERID> (User ID [lets say 2] of a friend of the hashtag owner [1]) </USERID>
-----------------------------------------
FILE NAME: Makale_FollowersIDs
The data of the followers of the 3 Hashtag Owners
The detailed information of the friends of the hashtag owners can be found.
Sample data from the file:
<FOLLOWERUSERID> (User ID [1] of the hashtag owner) </FOLLOWERUSERID> 
<USERID> (User ID [lets say 3] of a friend of the hashtag owner [1]) </USERID>
-----------------------------------------
According to this example:
1 is the user who used #TEDU
2 is a friend of 1
3 is a follower of 1

3 --> 1 --> 2
----------------------------------------
FOLDER NAME: UserTweets
FILE NAME: 0SCREENNAMEOFTHEUSER.txt
Each file in this folder contains the time lines of the users that we have get from the friendship information.
The files are named after the screen names of the users
Sample data from the file:
<USERID> (ID of the user which the timeline has retrieved from) </USERID>
Tweet number 1 and Created at: Sat Dec 21 23:13:19 EET 2019 RT @cedricmiyim: insan sevince manava bile anlatmak istiyor
[Tweet number 1 and Created at: Sat Dec 21 23:13:19 EET 2019] shows the number of tweet and the timestamp of it
[RT @cedricmiyim:] shows that this particular tweet has retweeted from a user with screen name cedricmiyim 
[insan sevince manava bile anlatmak istiyor] is the tweets it self