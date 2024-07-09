package twitter;

import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;





import org.junit.Test;

public class UtilitiesTest {
	
	/* Testing Strategy for getMentionedUsersFromSingleTweet
	 * 
	 * Partition on number of valid usernames per tweet: no valid usernames, at least one valid usernames.
	 * Partition on whether a valid username is repeated in a tweet: true, false
	 * 
	 * Tests that cover subdomains of partition
	 * 1. No valid username is present, No repeation in the tweets
	 * 2. Atleast one valid username, No repeation in the tweets
	 * 3. Atleast one valid username, Usernames are repeated
	 * */
	 
	
    /* Testing Strategy for isValidUsername
     *
	 * Partition on location of "@": start, middle, end
	 * Partition on username content: with lowercase letters, without lowercase letters
	 * Partition on username content: with uppercase letters, without uppercase letters
	 * Partition on username content: with hypen, without hypen, 
	 * Partition on username content: with underscore, without underscore.
	 * Partition on username content: with digits, without digit. 
	 * Partiton on whether a username contains characters other than defined by Tweet.getAuthor()'s spec: True, False 
	 * 
	 * Tests that cover subdomains of partition
	 * 1."@" is at start, with lowercase, without uppercase,with hypen, without underscore, without digits, no invalid charcater
	 * 2."@" is at start, without lowercase, with uppercase,without hypen, with underscore, with digits, no invalid character
	 * 3."@" is at middle, with lowercase, without uppercase, without hypen, without underscore, without digits, contains invalid character
	 * 4."@" is at middle, without lowercase, with uppercase, with hypen, without underscore, without digits, contains invalid character
	 * 5."@" is at end, without lowercase, without uppercase, without hypen, with underscore, with digits, contains invalid character
	 * */

	
    private static final Instant d3 = Instant.parse("2016-02-18T08:00:00Z");
    private static final Instant d6 = Instant.parse("2016-03-19T10:20:00Z");
    private static final Instant d9 = Instant.parse("2016-04-23T11:30:00Z");

    
    private static final Tweet tweet3 = new Tweet(3, "dappy", "I hate the system that wasted @rose's and @eli's time.", d3);
    private static final Tweet tweet6 = new Tweet(6, "caremel", "@DIPPY-dappy This was a big revelation for me. @ELI @ROSE @JULIJA my mail id is rose@newage.com.", d6);
    private static final Tweet tweet9 = new Tweet(9, "lily-rose", "@dippy_dappy & @eli123 I hate both of you but I hate you the most @dIpPy_dApPy. Same for you @eli123", d9);
 
    
    // No valid username is present, No repeation in the tweets
    @Test
    public void testGetMentionedUsers1() {
        Set<String> actualMentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet3));
        Set<String> expectedMentionedUsersLowercase = Set.of();
        Set<String> actualMentionedUsersLowercase = new HashSet<String>();
        
        for (String username:actualMentionedUsers) {
        	actualMentionedUsersLowercase.add(username.toLowerCase());
        }
        assertTrue("Expected", expectedMentionedUsersLowercase.containsAll(actualMentionedUsersLowercase));
    }
    
    
    // Atleast one valid username, No repeation in the tweets
    @Test
    public void testGetMentionedUsers2() {
        Set<String> actualMentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet6));
        Set<String> expectedMentionedUsersLowercase = Set.of("dippy-dappy", "eli", "rose", "julija");
        Set<String> actualMentionedUsersLowercase = new HashSet<String>();
        
        for (String username:actualMentionedUsers) {
        	actualMentionedUsersLowercase.add(username.toLowerCase());
        }
        assertTrue("Expected", expectedMentionedUsersLowercase.containsAll(actualMentionedUsersLowercase));
    }
    
    // Atleast one valid username, Usernames are repeated in the tweets
    @Test
    public void testGetMentionedUsers3() {
        Set<String> actualMentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet9));
        Set<String> expectedMentionedUsersLowercase = Set.of("dippy-dappy", "eli123");
        Set<String> actualMentionedUsersLowercase = new HashSet<String>();
        
        for (String username:actualMentionedUsers) {
        	actualMentionedUsersLowercase.add(username.toLowerCase());
        }
        assertTrue("Expected", expectedMentionedUsersLowercase.containsAll(actualMentionedUsersLowercase));
    }
    
    
/*---------------------------------------------------------------------------------------------------------------------------------------------------------------*/
   
   
    // Tests that cover subdomains of partition
	//	"@" is at start, with lowercase, without uppercase,with hypen, without underscore, without digits, no invalid charcater
    @Test
    public void testisValidUsername1() {
        boolean mentionedUser = Utilities.isValidUsername("@julija-ceh");
        
        assertTrue("expected true", mentionedUser);
    }
    
    
    
	//  "@" is at start, without lowercase, with uppercase,without hypen, with underscore, with digits, no invalid character
    @Test
    public void testisValidUsername2() {
        boolean mentionedUser = Utilities.isValidUsername("@TEEJAY_JUVKES1234");
        
        assertTrue("expected true", mentionedUser);
    }
    
    
    
    //  "@" is at middle, with lowercase, without uppercase, without hypen, without underscore, without digits, contains invalid character
    @Test
    public void testisValidUsername3() {
        boolean mentionedUser = Utilities.isValidUsername("deny@deid.com");
        
        assertTrue("expected true", mentionedUser);
    }
    
    
    
    //  "@" is at middle, without lowercase, with uppercase, with hypen, without underscore, without digits, contains invalid character
    @Test
    public void testisValidUsername4() {
        boolean mentionedUser = Utilities.isValidUsername("UPPP#@JUL-IJA<?");
        
        assertTrue("expected true", mentionedUser);
    }
    
    
    
    //  "@" is at end, without lowercase, without uppercase, without hypen, with underscore, with digits, contains invalid character
    @Test
    public void testisValidUsername5() {
        boolean mentionedUser = Utilities.isValidUsername("@&JUL_@I_JA$%");
        
        assertTrue("expected true", mentionedUser);
    }
    
   
}  
    

