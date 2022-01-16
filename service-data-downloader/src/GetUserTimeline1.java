import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.algs4.Out;

public class GetUserTimeline1 {

    public static Twitter twitter;
    public static Twitter twitter0;

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

    public void main(ArrayList<Long> allIDs) throws TwitterException, IOException {
        System.err.println("Getting user timelines");
        List<Status> statuses = null;
        ConfigurationB();
        long userID;
        String user = "";
        Paging p ;
        long last;
        int counter;
        ArrayList<Long> control;
        int xy;

        for(int i=0; i<allIDs.size(); i++) {
            userID= (long) allIDs.get(i);
            try {
                user = twitter.showUser(userID).getScreenName();
            } catch(Exception e){
                e.printStackTrace();
                System.err.println("+++ " + userID);
                continue;
            }
            System.err.println("Getting user: "+user);
            last=Long.MAX_VALUE;
            p = new Paging();
            control = new ArrayList<Long>();
            counter=1;
            xy=0;
            Out out=new Out("0" + user+".txt");
            out.println("<USERID>"+userID+"</USERID>");
            while (i<=allIDs.size()) {
                try {
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
                        statuses = twitter0.getUserTimeline(user, p);

                    for (Status status : statuses) {
                        if(status.getId()<last) {
                            last=status.getId();
                        }

                        out.println("<T="+counter+">" + "<D="+status.getCreatedAt()+"/> " + status.getText() + "</T>");
                        counter++;
                    }
                    p.setMaxId(last);
                    control.add(last);

                    if (control.size() > 2) {
                        if ((control.get(xy - 1)).equals(control.get(xy)) == true) {
                            break;
                        }
                    }
                    xy++;

                } catch (TwitterException te) {
                    System.err.println(">>>Failed to get the timeline of " + userID);
                    System.err.println(">>>" + te.getExceptionCode() + ": " + te.getErrorMessage());
                    break;
                }
            }
        }
        System.exit(0);
    }
}