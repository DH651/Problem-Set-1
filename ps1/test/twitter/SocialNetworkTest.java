/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class SocialNetworkTest {

	/* guessFollowsGraph
	 * Partition on length of list of tweets : empty, one item, more than one item.
	 * Partition on number of twitter authors : zero, one, more than one.
	 * Partition on number of followers of a single twitter author : zero, one, more than one.
	 * */
	
	/* influencers
	 * Partition on number of tweeter author: zero, one, more than one.
	 * Partition on number of followers of an author: one, more than one.
	 * Partition on the follower count follows of an author: one, more than one
	 * Partition on number of authors with same number of followers:every author has different follower count,more than two author has same follower count
	 * */
	
	
	 private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
	 private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
	 private static final Instant d3 = Instant.parse("2016-02-18T08:00:00Z");
	 private static final Instant d4 = Instant.parse("2016-02-18T09:30:00Z");
	 private static final Instant d5 = Instant.parse("2016-03-18T10:10:00Z");
	 private static final Instant d6 = Instant.parse("2016-03-19T10:20:00Z");
	 private static final Instant d7 = Instant.parse("2016-03-20T10:30:00Z");
	 private static final Instant d8 = Instant.parse("2016-04-22T10:01:00Z");
	 private static final Instant d9 = Instant.parse("2016-04-23T11:30:00Z");
	 private static final Instant d10 = Instant.parse("2016-04-23T11:30:00Z");
	    
	    
	 private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
	 private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "I hate rivest,he is xjd@9ixnx", d2);
	 private static final Tweet tweet3 = new Tweet(3, "dappy", "@ELI @ROSE @JULIJA I hate this system.", d3);
	 private static final Tweet tweet4 = new Tweet(4, "eli", "@12345 I know all the tricks that he will play.", d4);
	 private static final Tweet tweet5 = new Tweet(5, "rose", "@eli I'll mail you the details. My mail id is rose@newage.com.", d5);
	 private static final Tweet tweet6 = new Tweet(6, "caremel", "@rose @eli @dappy This was a big revelation for me.", d6);
	 private static final Tweet tweet7 = new Tweet(7, "julija", "I have learned so much from you @dappy.", d7);
	 private static final Tweet tweet8 = new Tweet(8, "dappy", "@roSE I bid you farwell!", d8);
	 private static final Tweet tweet9 = new Tweet(9, "rose", "@DaPPy @eLI Do you know that I hate you both?", d9);
	 private static final Tweet tweet10 = new Tweet(10, "eli", "@RoSe You have revealed your true face.", d10);
	    
	 
	 
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    
    // covers empty tweets list, zero twitter authors, an author has zero followers
    @Test
    public void testGuessFollowsGraph1() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        
        assertTrue("expected empty graph", followsGraph.isEmpty());
    }
    
    
    // covers more than one item, more than one authors, an author has single followers
    @Test
    public void testGuessFollowsGraph2() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet4, tweet5, tweet7, tweet10));
        
        assertTrue("expected empty graph", followsGraph.isEmpty());
    }
    
    
    // covers more than one item, more than one authors, an author may have multiple followers or single followers
    @Test
    public void testGuessFollowsGraph3() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet6, tweet7, tweet8, tweet9, tweet10));
        
        assertTrue("expected empty graph", followsGraph.isEmpty());
    }
    
    // covers more than one item, more than one twitter authors, an author may have multiple followers or single followers.
    @Test
    public void testGuessFollowsGraph4() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet5));
        
        assertTrue("expected empty graph", followsGraph.isEmpty());
    }
   
 /*--------------------------------------------------------------------------------------------------------------------------------------*/
    
    
    // empty graph 
    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue("expected empty list", influencers.isEmpty());
    }
    
    
    
    
    // covers one author, a single author has one follower, every follower follows a single author,
    // every author has same follower count
    @Test
    public void testInfluencers1() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        // dappy -> {eli}
        assertTrue("expected empty list", influencers.isEmpty());
    } 
    
    
    
    
    // covers more than one authors, a single author has multiple followers, follower follows a multiple authors,
    // atleast two authors have same follower count
    public void testInfluencers2() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        // dappy -> {eli,rose,julija} , caremel -> {eli,rose,alyssa} rose -> {dappy,julija) ,julija -> {dappy}
        assertTrue("expected empty list", influencers.isEmpty());
    } 
    
    
    
    
    
    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}
