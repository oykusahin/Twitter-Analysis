import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.algs4.ST;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
public class FriendsP {
	
	public static Twitter twitter0;

	Friendship friendship;
	List<twitter4j.IDs> IDS;
	long cursor =-1;
	IDs ids;
	Twitter twitter;
	boolean isProtected;
	ArrayList<Long> friendsIDs = new ArrayList<>();
	int pass =0;

	public void IDtoName(long id) throws TwitterException {
		UserP userP = new UserP(twitter.showUser(id));
		userP.toFile();
	}
	
	
	public void getUserInfo(ST<Integer, Long> stUser,int symbolTaleSize, Twitter twitter){		
		for(int i=0; i<stUser.size() ; i++) {
			ConfigurationB();
			long userID = stUser.get(i); 
			
			  try {
		            long cursor = -1;
		            IDs ids;
		            do {
		            	ids = twitter.getFriendsIDs(userID, cursor);
		            	
		                System.out.println("\n<FRIENDSUSERID>"+stUser.get(i)+"</FRIENDSUSERID>");
		                System.err.println("Getting Friends of the user with ID: " + userID);	        			
	        			for (long id : ids.getIDs()) {
		                	
		                	  if(pass==10)
			        				twitter=ConfigurationB0();

		                	if (twitter.showUser(id).isProtected()==false) 
		                	{       	
		                	System.out.println(" ");
		                    friendsIDs.add(id);
		                	IDtoName(id);
		                	}
		                	pass++;		                	
		                }
		            } while ((cursor = ids.getNextCursor()) != 0);

		        } catch (TwitterException te) {
		       
		        	System.err.println("Failed to get all friends of " + userID);
					try {
					Thread.sleep(900000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		            i--;
		            pass= pass+10;
		        }
		}
		
	}

    
    public static Twitter ConfigurationB0() {
		AUTH_CONSUMER_KEY=System.getenv("AUTH_CONSUMER_KEY");
        AUTH_CONSUMER_SECRET=System.getenv("AUTH_CONSUMER_SECRET");
        AUTH_ACCESS_TOKEN=System.getenv("AUTH_ACCESS_TOKEN");
        AUTH_ACCESS_TOKEN_SECRET=System.getenv("AUTH_ACCESS_TOKEN_SECRET");

        ConfigurationBuilder cf0;
		cf0 = new ConfigurationBuilder(); 
		cf0.setDebugEnabled(true)
		.setOAuthConsumerKey(AUTH_CONSUMER_KEY)
		.setOAuthConsumerSecret(AUTH_CONSUMER_SECRET)
		.setOAuthAccessToken(AUTH_ACCESS_TOKEN)
		.setOAuthAccessTokenSecret(AUTH_ACCESS_TOKEN_SECRET).setTweetModeExtended(true);
	
		TwitterFactory tf0 = new TwitterFactory(cf0.build());
		twitter0 = tf0.getInstance();
		return twitter0;
	}



}