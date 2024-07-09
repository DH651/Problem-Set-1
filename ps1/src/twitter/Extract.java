/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;


import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
    	
    	
    	//if empty list return a sentinal object.
    	if (tweets.isEmpty()){
    		return new Timespan(Instant.now(), Instant.now());
    	}
    	
        //Initialize timespanStart & timespanEnd with the timestamp of the first tweet.
       	Instant Start = tweets.get(0).getTimestamp();
       	Instant End = tweets.get(0).getTimestamp();
       	
 
        //Iterate over entire collection and check if the timestamp of the current tweet is greater than timespanEnd or less than timespanStart.
        //and accordingly adjust the timespanStart and timespanEnd values.
        for (Tweet currentTweet:tweets) {
        	Instant currentTweetTimeStamp = currentTweet.getTimestamp();

        	if (currentTweetTimeStamp.isBefore(Start)){
        		Start = currentTweetTimeStamp;
        	}
        	if (currentTweetTimeStamp.isAfter(End)){
        		End = currentTweetTimeStamp;
        	}
        }
    
        return new Timespan(Start, End);
    }

    

	/**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param  tweets, list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
    	
        // Initialize an empty Set.
        Set<String> mentionedUsers = new HashSet<String>();
        Set<String> mentionedUsersLowercase = new HashSet<String>();
        
        // Iterate over the entire collection, and extract mentioned username from the current tweet by calling getMentionedUsersFromSingleTweet method. 
        // Add the extracted usernames to the set.
        for (Tweet currentTweet:tweets) {
        	Set <String> mentionedUsersFromSingleTweet = Utilities.getMentionedUsersFromSingleTweet(currentTweet);
        	for(String user:mentionedUsersFromSingleTweet){
        		if(!mentionedUsersLowercase.contains(user.toLowerCase())) {
        			
        			mentionedUsers.add(user);
        			mentionedUsersLowercase.add(user.toLowerCase());
        		}
        		
        	}
        	
        }
        
        return mentionedUsers;
    }
     
}
