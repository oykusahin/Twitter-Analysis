import java.util.ArrayList;
import edu.princeton.cs.algs4.ST;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.Twitter;
import twitter4j.User;

public class HashtagQuery {

	/**Objects to be used in this class
	* twitter: to connect twitter API
	* user: to define users whom used hashtag
	* f: to call methods from FriendsP class
	* fol: to call methods from FollowersP class
	* stUser: to store user IDs of hashtag owners to pass it to FriendsP and FollowersP classes
	* hashtagIDs: array list to store userIDs data and pass it to the MainDriver to get their time lines
	* friendIDs: array list to store userIDs data and pass it to the MainDriver to get their time lines
	* followerIDs: array list to store userIDs data and pass it to the MainDriver to get their time lines
	*/
    Twitter twitter;
	User user;
	FriendsP f = new FriendsP();
	FollowersP fol = new FollowersP();
	ST<Integer, Long> stUser = new ST<>();
	ArrayList<Long> hashtagIDs = new ArrayList<Long>();
	ArrayList<Long> friendIDs = new ArrayList<Long>();
	ArrayList<Long> followersIDs = new ArrayList<Long>();
	
public void getHashtag(String searchTerm, String queryTask){
		ConfigurationB();
		Query q = new Query(searchTerm);
		
		q.setCount(100);
		q.setSince("2010-1-1");
		q.setLang("tr");
        
		//Begins to retrieve data of the hashtag owners
		try{
			QueryResult result = twitter.search(q);
			int count=0;
			if(queryTask == "getHashtagOwners")
			{
				System.err.println("Getting Hashtag Owners");
				for(twitter4j.Status tweet: result.getTweets())
				{
					//to eliminate the private users
					if(tweet.getUser().isProtected() == false)
					{
						/**in order to test the code, the loop only runs for one user
						*prevents the code to get the same hashtagowners, checks the array list 
                        *in order to TEST the methods add & count <= 10 & !tweet.getUser().getScreenName().equals("ahbap_tedu")  & tweet.getUser().getFollowersCount()<50 & tweet.getUser().getFriendsCount() < 50
                        */
						if(!hashtagIDs.contains(tweet.getUser().getId())) 
						{
							//Stores and prints the data to the initial file within a loop
							stUser.put(count, tweet.getUser().getId());
							hashtagIDs.add(tweet.getUser().getId());
							UserP userP = new UserP(tweet.getUser());
							userP.toFile();
							System.out.println();
							count++;
						}
					}
				}
				System.out.println(stUser.size());
			}
			else if (queryTask == "getFriends")
			{
				//Stores and prints the data to the secondary file by passing the symbol table to FriendsP class
				getFriendsP();
			}
			else if ( queryTask == "getFollowers") {
				//Stores and prints the data to the secondary file by passing the symbol table to FollowersP class
				getFollowersP();
			}			
		}
		catch (TwitterException e) {
			e.printStackTrace();
		}
		System.out.println();
	}


void getFollowersP() {
	//passes the parameters of symbol table with the hashtag owner IDs, the size of the symbol table and twitter to the FollowersP class.
	//returns the IDs of followers in an ArrayList to pass to the mainDriver
	fol.getUserInfo(stUser, stUser.size(), twitter);
	followersIDs = fol.followerIDs;
}

void getFriendsP(){	
	//passes the parameters of symbol table with the hashtag owner IDs, the size of the symbol table and twitter to the FriendsP class.
	//returns the IDs of friends in an ArrayList to pass to the mainDriver
	f.getUserInfo(stUser, stUser.size(), twitter);
	friendIDs = f.friendsIDs;
}

//Keys and tokens for the configuration builder (Since 22.12.2019)
private ConfigurationBuilder cf;
private void ConfigurationB() {
    AUTH_CONSUMER_KEY=System.getenv("AUTH_CONSUMER_KEY");
    AUTH_CONSUMER_SECRET=System.getenv("AUTH_CONSUMER_SECRET");
    AUTH_ACCESS_TOKEN=System.getenv("AUTH_ACCESS_TOKEN");
    AUTH_ACCESS_TOKEN_SECRET=System.getenv("AUTH_ACCESS_TOKEN_SECRET");

	cf = new ConfigurationBuilder(); 
	cf.setDebugEnabled(true)
	.setOAuthConsumerKey(AUTH_CONSUMER_KEY)
	.setOAuthConsumerSecret(AUTH_CONSUMER_SECRET)
	.setOAuthAccessToken(AUTH_ACCESS_TOKEN)
	.setOAuthAccessTokenSecret(AUTH_ACCESS_TOKEN_SECRET).setTweetModeExtended(true);

	TwitterFactory tf = new TwitterFactory(cf.build());
	twitter = tf.getInstance();
}

}