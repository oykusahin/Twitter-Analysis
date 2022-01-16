import twitter4j.User;
import twitter4j.Twitter;

public class UserP {
	User user;
	Twitter twitter;

	/*
	 * Parameters to be stored in the UserP object
	 * */
	private boolean isProtected;
	private String eMail;
	private int favCount;
	private int followersCount;
	private int friendshipCount;
	private long userID;
	private String lang;
	private int listedCount;
	private String location;
	private String name;
	private String screenName;
	private int statusCount;
	private boolean isVerified;
	private String profileDescription;
	
	UserP(User user){
		
		isProtected = user.isProtected();
		eMail = user.getEmail();
		favCount=user.getFavouritesCount();
		followersCount=user.getFollowersCount();
		friendshipCount=user.getFriendsCount();
		userID = user.getId();
		lang = user.getLang();
		listedCount = user.getListedCount();
		location = user.getLocation();
		name = user.getName();
		screenName = user.getScreenName();
		statusCount = user.getStatusesCount();
		isVerified = user.isVerified();
		profileDescription=user.getDescription();
	}
	
	public void toFile() {
		getUserID();
		getName();
		getScreenName();
		getFriendshipCount();
		getFollowersCount();
		getIsProtected();
		getIsVerified();
		getProfileDescription();
	}
	public String getProfileDescription() {
		System.out.print("<PROFILEDESCRIPTION>");
		System.out.print(profileDescription);
		System.out.println("</PROFILEDESCRIPTION>");
		return  profileDescription;
	}
	public boolean getIsVerified() {
		System.out.print("<ISVERIFIED>");
		System.out.print(isVerified);
		System.out.println("</ISVERIFIED>");
		return isProtected;
	    }
	public int getFriendshipCount() {
		System.out.print("<FRIENDSHIPCOUNT>");
		System.out.print(friendshipCount);
		System.out.println("</FRIENDSHIPCOUNT>");
		return  friendshipCount;
	}
	public int getNumberOfTweets() {
		return statusCount;
	}
	public String getScreenName() {
		System.out.print("<SCREENNAME>");
		System.out.print(screenName);
		System.out.println("</SCREENNAME>");
		return screenName;
	}
	public String getName() {
		System.out.print("<USERNAME>");
		System.out.print(name);
		System.out.println("</USERNAME>");
		return name;
	}
	public String getLocation() {
		return location;			
	}
	public int getHowManyTimesAUserListed() {
		return listedCount;
	}
	public String getLanguage() {
		return lang;
	}
	public long getUserID() {
		System.out.print("<USERID>");
		System.out.print(userID);
		System.out.println("</USERID>");
		return userID;
	}
	public int getFollowersCount() {
		System.out.print("<FOLLOWERSCOUNT>");
		System.out.print(followersCount);
		System.out.println("</FOLLOWERSCOUNT>");
		return followersCount;
	}
	public int getFavouritesCount() {
		return favCount;
	    }
	
	public boolean getIsProtected() {
		System.out.print("<ISPROTECTED>");
		System.out.print(isProtected);
		System.out.println("</ISPROTECTED>");
		return isProtected;
	    }
	public String geteMail() {
		return eMail;
	    }
}