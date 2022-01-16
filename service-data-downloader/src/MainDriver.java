import java.io.*;
import java.util.ArrayList;
import twitter4j.*; 
import edu.princeton.cs.algs4.ST;


public class MainDriver {	
	public static void main(String[] args) throws TwitterException, IOException  {
		/**Objects to be used in the mainDriver.java class
		*query: to call methods and queues from HashtagQuery.java class
		* allIDs: to store all the IDs which are retrieved from Twitter in an array list to pass as a parameter to the GetUserTimeline1.java class
		*/         
		HashtagQuery query = new HashtagQuery();
		ArrayList<Long> allIDs = new ArrayList<Long>();
		
		//Creates the file of the # owners and saves their userIDs who had write about TEDU.
		PrintStream o0 = new PrintStream(new File("Makale_HashtagUserIDs.txt")); 
      	System.setOut(o0);  
		query.getHashtag("TEDU", "getHashtagOwners");
    	o0.close();
		
		try {
			Thread.sleep(900000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Creates a separate file for the friends of the hashtag owners.
		PrintStream o1 = new PrintStream(new File("Makale_FriendIDs.txt")); 
		System.setOut(o1);  
		query.getHashtag("TEDU", "getFriends");
		o1.close();
		
		//Creates a sleep thread in order to give time for tokens to refresh
		try {
			Thread.sleep(900000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		/** 
        //Creates a separete file for the followers of the hashtag owners. 
		PrintStream o2 = new PrintStream(new File("Makale_FollowerIDs.txt")); 
		System.setOut(o2);  
		query.getHashtag("TEDU", "getFollowers");
		o2.close();
		*/

		/**In order to get the time line of the users we need their user ID.
		* While the friendship information has retrieved from the twitter with the previous codes all 
		* user IDs within a relation with the hashtag owners has been stored in three separate array lists
		*In order to make it easier for us to understand the following code copies all the userIDs in those array lists
		*into one major arrayList named allIDs to pass it to the GetUserTimeline1.java class
        */
			for(int i =0; i<query.hashtagIDs.size(); i++) {
				if(!allIDs.contains(query.hashtagIDs.get(i)))
						allIDs.add(query.hashtagIDs.get(i));
			}
			
			for(int i =0; i<query.friendIDs.size(); i++) {
				if(!allIDs.contains(query.friendIDs.get(i)))
					allIDs.add(query.friendIDs.get(i));
			}
			
			for(int i =0; i<query.followersIDs.size(); i++) {
				if(!allIDs.contains(query.followersIDs.get(i)))
					allIDs.add(query.followersIDs.get(i));
			}
			
			try {
				Thread.sleep(900000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		String line = null;
		String[] lineParser;
		ArrayList<Long> ids = new ArrayList<Long>();

		int c1=0;
		try {
			 
		  	FileReader fileReader = new FileReader("Makale_IDandScreenName.txt");
		    BufferedReader bufferedReader = new BufferedReader(fileReader);
		    while((line = bufferedReader.readLine()) != null) {
		      	lineParser=line.split("\t"); 
		      	Long id = Long.parseLong(lineParser[1]);
		      	if(id!=0)
		      	{
		      		ids.add(id);
		      	}
		      	c1++;
		    }
		      fileReader.close();
		      bufferedReader.close();
		  }
	
	catch(FileNotFoundException ex) {
			System.out.println("Unable to open file");                
		  }
	catch(IOException ex) {
		    System.out.println("Error reading file");                  
		  }	
		//Passes the allIDs queue to the GetUserTimeline1.java class and calls the class.
		GetUserTimeline1 getUserTimelineObj = new GetUserTimeline1();
		getUserTimelineObj.main(ids);
	}
}