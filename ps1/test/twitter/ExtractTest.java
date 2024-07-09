/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.Set;

import org.junit.Test;

public class ExtractTest {

	/* Testing Strategy for getTimeSpan method
	 * Partition on value of tweets.length(): zero, one , more than one.
     * Partition on number of tweets with minimum timespan: zero, one, more than one.
     * Partition on number of tweets with maximum timespan: zero, one, more than one.
	 * */
	
	/*  Tests that covers Cartesian Product of Subdomains
	 *  
	 *  1.  covers tweets.length() is zero, number of tweets with minimum timespan is zero.
     *      number of tweets with maximum timespan is zero.
	 *  2.  covers tweets.length() is one, number of tweets with minimum timespan is one
     *      number of tweets with maximum timespan is one.
	 *  3.  covers tweets.length() is more than one, number of tweets with minimum timespan is one
     *      number of tweets with maximum timespan is more than one.
	 *  4.  covers tweets.length() is more than one , number of tweets with minimum timespan is more than one
     *      number of tweets with maximum timespan is one.
     *  5.  covers tweets.length() is more than one , number of tweets with minimum timespan is more than one
     *      number of tweets with maximum timespan is more than one.
	 *  
	 * */			
	
	
	/*
	 * Testing Strategy for getMentionedUsers
	 * Partition on number of vaild usernames: Zero , one <= number of valid username < tweets.length(), >= tweets.length().
	 * Partition on number of different tweets having the same username: Zero , One <= No. of repetations < tweets.length(), tweets.length().
	 * Partition on the size of tweets: Zero, Atleast one tweet in tweets.
	 * 
	 * 
	 * Tests that covver subdomains of partition
	 * 1. Zero valid usernames, Zero tweets in tweets
	 * 2. Zero valid usernames, atleast one tweet in tweets
	 * 3. Atleast one valid username but total is less than tweets.length(), Zero tweets with the same username, atleast one tweet in tweets.
	 * 4. Atleast one valid username but total is less than tweets.length(), One <= No. of repetations < tweets.length(), atleast one tweet in tweets.
	 * 5. More than tweets.length() valid usernames, tweets.length() repetations, atleast one tweet in tweets.
	 * 
	 * **/
	
	/*
	 * Testing Strategy for getMentionedUsers
	 * Partition on number of valid usernames: no valid usernames, atleast one valid usernames.
	 * Partition on number of invalid usernames: no invalid username, atleast one invalid username.
	 * Partition on case of letters in the valid usernames content: letters in uppercase, letters in lowercase
	 * Partition on number of letters in the valid username: without letters, with some letters,  with all letters                                    
	 * Partition on valid username content: with hypen,without hypen, 
	 * Partition on valid username content: with underscore, without underscore.
	 * Partition on valid username content: with digits, without digits.  
	 * Partition on value of tweets.length(): zero, one, more than one. 
	 * 
	 * 
	 * */

	
	 
    private static final Instant d1  = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2  = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d3  = Instant.parse("2016-02-18T08:00:00Z");
    private static final Instant d4  = Instant.parse("2016-02-18T09:30:00Z");
    private static final Instant d5  = Instant.parse("2016-03-18T10:10:00Z");
    private static final Instant d6  = Instant.parse("2016-03-19T10:20:00Z");
    private static final Instant d7  = Instant.parse("2016-03-20T10:30:00Z");
    private static final Instant d8  = Instant.parse("2016-04-22T10:01:00Z");
    private static final Instant d9  = Instant.parse("2016-04-23T11:30:00Z");
    private static final Instant d10 = Instant.parse("2016-04-23T11:30:00Z");
    
    
    private static final Tweet tweet1  = new Tweet(1, "alyssa", "Is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2  = new Tweet(2, "bbitdiddle", "I hate rivest,he is xjd@9ixnx.", d2);
    private static final Tweet tweet3  = new Tweet(3, "dappy", "@ELI, @ROSE I hate the system that wasted @rose's and @eli's time.", d3);
    private static final Tweet tweet4  = new Tweet(4, "eli", "I know all the tricks that they will play @juilja.You should inform @12345.", d4);
    private static final Tweet tweet5  = new Tweet(5, "lily-rose", "@eli & @dippy-dappy I will email you the details. My mail id is rose@newage.com.", d5);
    private static final Tweet tweet6  = new Tweet(6, "caremel", "@rose @ELI @DIPPY-dappy This was a big revelation for me.", d6);
    private static final Tweet tweet7  = new Tweet(7, "julija", "@rOsE has learned so much from @dippy-DAPPY.", d7);
    private static final Tweet tweet8  = new Tweet(8, "dippy_dappy", "@lily-rose I bid you farwell!. @LiLy-rOsE is misguided by the New Age movement.", d8);
    private static final Tweet tweet9  = new Tweet(9, "lily-rose", "@dippy-dappy @Donnie @Masalafry @eli I hate both of you but I hate you the most @dIpPy-dApPy.", d9);
    private static final Tweet tweet10 = new Tweet(10, "eli", "Hey everyone rose's teeth are yellow. She has revealed her true face.", d10);
    
    
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    
    // covers tweets.length() is zero, number of tweets with minimum timespan is zero.
    // number of tweets with maximum timespan is zero.
    @Test
    public void testGetTimespanZeroTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList());
        
        //assertEquals("expected start", d1, timespan.getStart());
        //assertEquals("expected end", d2, timespan.getEnd()); 
    }
    
    
    // covers tweets.length() is one, number of tweets with minimum timespan is one
    // number of tweets with maximum timespan is one.
    @Test
    public void testGetTimespanSingleTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet9));
        
        assertEquals("expected start", d9, timespan.getStart());
        assertEquals("expected end", d9, timespan.getEnd()); 
    }
    
    
    // covers tweets.length() is more than one, number of tweets with minimum timespan is one
    // number of tweets with maximum timespan is more than one.
    @Test
    public void testGetTimespanSingleTweet() {
    	Timespan timespan = Extract.getTimespan(Arrays.asList(tweet3, tweet4, tweet5, tweet6, tweet7, tweet8, tweet9, tweet10));
    	
    	assertEquals("expected start", d3, timespan.getStart());
    	assertEquals("expected end", d9, timespan.getEnd());
    }
    
    
    // covers tweets.length() is more than one, number of tweets with minimum timespan is more than one
    // number of tweets with maximum timespan is one.
    @Test
    public void testGetTimespanArbitraryLength() {
    	Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5, tweet6, tweet7, tweet8, tweet9));
    	
    	assertEquals("expected start", d1, timespan.getStart());
    	assertEquals("expected end", d9, timespan.getEnd());
    }
    
    // covers tweets.length() is more than one , number of tweets with minimum timespan is more than one
    // number of tweets with maximum timespan is more than one.
    @Test
    public void testGetTimespanArbitraryLength() {
    	Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5, tweet6, tweet7, tweet8, tweet9, tweet10));
    	
    	assertEquals("expected start", d1, timespan.getStart());
    	assertEquals("expected end", d9, timespan.getEnd());
    }
    
  
/*-------------------------------------------------------------------------------------------------------------------------------------------*/

    // Zero valid usernames, Zero tweets in tweets
    @Test
    public void testGetMentionedUsers1() {
        Set<String> actualMentionedUsers = Extract.getMentionedUsers(Arrays.asList());
        Set<String> expectedMentionedUsersLowercase = Set.of();
        Set<String> actualMentionedUsersLowercase = new Set<String>();
        
        for (String username:actualMentionedUsers) {
        	actualMentionedUsersLowercase.add(username.toLowerCase())
        }
        assertTrue("Expected", expectedMentionedUsersLowercase.containsAll(actualMentionedUsersLowercase));
    }

    
    
    //    Zero valid usernames, atleast one tweet in tweets
    @Test
    public void testGetMentionedUsers2() {
        Set<String> actualMentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1, tweet2, tweet10));
        Set<String> expectedMentionedUsersLowercase = Set.of();
        Set<String> actualMentionedUsersLowercase = new Set<String>();
        
        for (String username:actualMentionedUsers) {
        	actualMentionedUsersLowercase.add(username.toLowerCase())
        }
        assertTrue("Expected", expectedMentionedUsersLowercase.containsAll(actualMentionedUsersLowercase));
    }

    
    
	//    Atleast one valid username but total is less than tweets.length(), Zero tweets with the same username, atleast one tweet in tweets.
    @Test
    public void testGetMentionedUsers3() {
        Set<String> actualMentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet10));
        Set<String> expectedMentionedUsersLowercase = Set.of("rose", "eli", "julija", "12345");
        Set<String> actualMentionedUsersLowercase = new Set<String>();
        
        for (String username:actualMentionedUsers) {
        	actualMentionedUsersLowercase.add(username.toLowerCase())
        }
        assertTrue("Expected", expectedMentionedUsersLowercase.containsAll(actualMentionedUsersLowercase));
    }

    
    
    
    //    Atleast one valid username but total is less than tweets.length(), One <= No. of repetations of valid username< tweets.length(), atleast one tweet in tweets.
    @Test
    public void testGetMentionedUsers1() {
        Set<String> actualMentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1, tweet2, tweet5, tweet6, tweet7, tweet8, tweet10));
        Set<String> expectedMentionedUsersLowercase = Set.of("eli", "dippy-dappy", "rose", "lily-rose");
        Set<String> actualMentionedUsersLowercase = new Set<String>();
        
        for (String username:actualMentionedUsers) {
        	actualMentionedUsersLowercase.add(username.toLowerCase())
        }
        assertTrue("Expected", expectedMentionedUsersLowercase.containsAll(actualMentionedUsersLowercase));
    }

    
    
    //    More than tweets.length() valid usernames, tweets.length() repetations of valid username, atleast one tweet in tweets.
    @Test
    public void testGetMentionedUsers1() {
        Set<String> actualMentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet5, tweet6, tweet7, tweet9));
        Set<String> expectedMentionedUsersLowercase = Set.of("eli", "dippy-dappy","rose", "donnie", "Masalafry");
        Set<String> actualMentionedUsersLowercase = new Set<String>();
        
        for (String username:actualMentionedUsers) {
        	actualMentionedUsersLowercase.add(username.toLowerCase())
        }
        assertTrue("Expected", expectedMentionedUsersLowercase.containsAll(actualMentionedUsersLowercase));
    }

    
    
    
    /*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version.
     * DO NOT strengthen the spec of Extract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */

}
