package twitter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utilities {
	
	
	/**
     * Get usernames mentioned from the text of a tweet.
     * 
     * @param  tweet, a tweet with containing some text, not modified by this method.
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
    public static Set<String> getMentionedUsersFromSingleTweet(Tweet tweet){
    	// Break the tweet.text into words and add these two a list
    	String[] Words = tweet.getText().split(" ");
    	Set<String> mentionedUsers= new HashSet<String>();
    	Set<String> mentionedUsersLowerCase = new HashSet<String>();
    	
    	// Iterate over each word and check if the current word is a valid username,if yes then add  it to the answer.
    	for(String word:Words) {
    		//System.out.println("\n");
    		//System.out.println(word);
    		//System.out.println(isValidUsername(word));
    		if (isValidUsername(word)) {
 
    			// Remove the leading "@"
    			word = word.substring(1);
    		
    			// Remove the trailing "," and "."
    			if(word.endsWith(",")|word.endsWith(".")) {
    				word = word.substring(0, word.length() - 1);
    			}
    			
    			// Since, username are case-insensitive, we will add the usernames only if username in lowercase form are not present in  
        		// mentionedUsersLowerCase
        		if(!mentionedUsersLowerCase.contains(word.toLowerCase())) {
        		  mentionedUsers.add(word);
        		  mentionedUsersLowerCase.add(word.toLowerCase());
        		}
    			
    		}
    		
    	}
   
    	return mentionedUsers;
    }
    
    
    
    /**
     * Checks if the word is a valid twitter name. 
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     * @param  word, from tweet.text, not modified by this method.
     * @return true if the word is a valid username Otherwise returns false 
     */
    public static boolean isValidUsername(String word){
    	// regex pattern matches with the word starting with @ followed by one or more characters from the lowercase letters[a-z]
    	// ,uppercase letters[A-Z], underscore[_], hypen[_].
    	 Pattern pattern = Pattern.compile("^@[a-zA-Z0-9_-]+(,|.)?$");
    	 Matcher matcher = pattern.matcher(word);
    	 boolean matchFound = matcher.find();
    	 return matchFound;
    }
    
    
    
    /**
     * Checks if the tweet contain certain words.
     *            all and only the tweets in the list such that the tweet text (when 
     *            represented as a sequence of nonempty words bounded by space characters 
     *            and the ends of the string) includes *at least one* of the words 
     *            found in the words list. Word comparison is not case-sensitive,
     *            so "Obama" is the same as "obama".  The returned tweets are in the
     *            same order as in the input list.
     * @param tweet
     *            a tweet containing certain words in tweet.text, not modified by this method.
     * @param words
     *            a list of words to search for in the tweets. 
     *            A word is a nonempty sequence of nonspace characters.
     * @return true of the tweet contains certain word from words
     */
    public static boolean tweetContainsWords(Tweet tweet, List<String> words) {
    	 String[] wordsInATweetArray = tweet.getText().split(" ");
    	 Set<String> wordsInATweetSet = new HashSet<String>(Arrays.asList(wordsInATweetArray));
    	 for(String word:words) {
    		if(wordsInATweetSet.contains(word)) {
    			return true;
    		}
    	 }
    	 return false;
    }
  
}
