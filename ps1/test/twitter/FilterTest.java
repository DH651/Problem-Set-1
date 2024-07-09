/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class FilterTest {
	
	// Testing strategy for writtenBy:
    /* Partition on tweets.length(): zero, one, more than one.
     * Partition on number of tweets made by author <username>: zero, one, more than one.
     * */
	
	
	/*   Tests that cover cartesian product of subdomains:
	 *   1. single item, one tweet from author<username>.
     *   2. more than one item, zero tweet made by author<username>.
     *   3. more than one item, one tweet made by author <username>.
     *   4. more than one item, more than one tweets made by author <username>.
	 *   5. empty list and zero tweets from author. 
	 * */
	
    
	// Testing strategy for inTimeSpan
	/* Partition on tweets.length():zero, one item, more than one item.
	 * Partition on value of timespan.start: T.start <= tweets[min].timestamp,
	 *                                       tweets[min].timestamp < T.start < tweets[max].timestamp,
	 *                                       tweets[max].timestamp <= T.start
	 *                                                       
	 * Partition on value of timespan.end: T.end <= tweets[min].timestamp,
	 *                                     tweets[min].timestamp < T.end < tweets[max].timestamp,
	 *                                     tweets[max].timestamp  <= T.end
	 *                                      
	 * Partition on number of tweets within the timestamp: zero, one, 1 < x < tweets.length(), tweets.length() 
	 * 
     * Partition on number of tweets that overlap with timespan.start:zero, one, more than one
     * 
     * Partition on number of tweets that overlap timespan.end:zero, one, one more than one
     * */
	 
//	1. more than one item, T.start <= tweets[min].timestamp, tweets[max].timestamp <= T.end, 
//     tweets.length() tweets within timespan,one tweet overlap with T.start and T.end
//	2. one item, T.start < tweets[min].timestamp,T.end < tweets[min].timestamp, 
//     zero tweets within timespan, zero tweet overlap with T.start and T.end
//	3. one item, tweets[max].timestamp < T.start, tweets[max].timestamp < T.end, 
//     zero tweets within timespan, zero tweet overlap with T.start and T.end
//	4. more than one item, tweets[min].timestamp <= T.start <= tweets[max].timestamp, tweets[min].timestamp <= T.end   <= tweets[max].timestamp, 
//     1 < x < tweets.length() tweets with in timespan, zero tweet overlap with T.start and T.end
//  5. more than one item, tweets[min].timestamp <= T.end   <= tweets[max].timestamp, tweets[min].timestamp <= T.start <= tweets[max].timestamp,
//     zero tweets within timepsan, zero tweet overlap with T.start and T.end
//  6. zero item,zero tweets within timespan,zero tweet overlap with T.start and T.end
//	7. more than one item ,tweets[min].timestamp <= T.start <= tweets[max].timestamp, tweets[min].timestamp <= T.end <= tweets[max].timestamp,
// 	   tweets.length() tweets within timespan,and more than one tweet overlap with T.start and T.end
    
    
	
	// Testing strategy for containing
	/* 
	 * Partition on length of list of tweets: empty, one item, more than one item.
	 * Partition on number of words: one, more than one.
	 * Partition on number of tweets with atleast one word from words: zero, one, 1 < x < tweets.length(), tweets.length(),
     * */

//     1. one tweet, more than one word, one tweet with atleast one word from words.
//     2. one tweet, one word, one tweet with atleast one word from words.
//     3. more than one tweet, more than one word, tweets.length() tweets have atleast one word from word.
//     4. more than one tweet, one word, 1 < x < tweets.length() tweets with atleast one word from words.
//	   5. more than one tweet, more than word, zero tweets with atleast one word from words.
//     6. zero tweet, one word, zero tweet with atleast one word from words,


	private static final Instant d0  = Instant.parse("2016-02-17T10:00:00Z");
	private static final Instant d1  = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2  = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3  = Instant.parse("2016-02-18T08:00:00Z");
    private static final Instant d4  = Instant.parse("2016-02-18T09:30:00Z");
    private static final Instant d5  = Instant.parse("2016-03-18T10:10:00Z");
    private static final Instant d6  = Instant.parse("2016-03-19T10:20:00Z");
    private static final Instant d7  = Instant.parse("2016-03-20T10:30:00Z");
    private static final Instant d8  = Instant.parse("2016-04-22T10:01:00Z");
    private static final Instant d9  = Instant.parse("2016-04-23T11:30:00Z");
    private static final Instant d10 = Instant.parse("2016-04-23T11:30:00Z");
    
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "I hate rivest,he is xjd@9ixnx", d2);
    private static final Tweet tweet3 = new Tweet(3, "DAPPY", "@ELI @ROSE @JULIJA I hate this system.", d3);
    private static final Tweet tweet4 = new Tweet(4, "ELI", "@12345 I know all the tricks that he will play.", d4);
    private static final Tweet tweet5 = new Tweet(5, "rose", "@eli I will email you the details.My mail id is rose@newage.com.", d5);
    private static final Tweet tweet6 = new Tweet(6, "alyssa", "@rose @eli @dappy This was a big revelation for me.", d6);
    private static final Tweet tweet7 = new Tweet(7, "rOsE", "I have learned so much from you @dappy.", d7);
    private static final Tweet tweet8 = new Tweet(8, "dApPy", "@roSE I bid you farwell!", d8);
    private static final Tweet tweet9 = new Tweet(9, "alyssa", "@DaPPy @eLI Do you know that I hate you both?", d9);
    private static final Tweet tweet10 = new Tweet(10, "ELI", "@RoSe You have revealed your true face.", d10);
    
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    
    /*---------------------------------------------------------------------------------------------------------------------*/

//  1. single item, one  tweet from author<username>
    @Test
    public void testWrittenBy1() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet8), "dApPy");
        
        assertEquals("expected singleton list", 1, writtenBy.size());
        assertTrue("expected list to contain tweet", writtenBy.contains(tweet8));
    }
    
//  2. more than one item, zero tweet made by author<username>
    @Test
    public void testWrittenBy2() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1, tweet2, tweet3, tweet4), "RonDesi");
        
        assertEquals("expected singleton list", 0, writtenBy.size());
        assertTrue("expected an empty list", writtenBy.isEmpty());
    }
    
    
//  3. more than one item, one tweet made by author <username>,
    @Test
    public void testWrittenBy3() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet5, tweet6, tweet7, tweet8, tweet9, tweet10), "ELI");
        Set<Tweet> expected = Set.of(tweet10);
        
        assertEquals("Expected singleton list", 1, writtenBy.size());
        assertTrue("expected list to contain tweet", writtenBy.containsAll(expected));
    }

//  4. more than one item, more than one tweet made by author <username>,
    @Test
    public void testWrittenBy4() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1, tweet2, tweet4, tweet5, tweet6, tweet9), "alyssa");
        Set<Tweet> expected = Set.of(tweet1, tweet6, tweet9);
        
        assertEquals("Expected a list of size three.", 3, writtenBy.size());
        assertTrue("Expected list should have contained tweet.", writtenBy.containsAll(expected));
    }
    
//	5. empty list and zero tweets from the author.
    @Test
    public void testWrittenBy5() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(), "alyssa");
        
        assertEquals("expected an empty list", 0, writtenBy.size());
        assertTrue("expected an empty list", writtenBy.isEmpty());
    }
    
 
    /*---------------------------------------------------------------------------------------------------------------------*/

    //	1. more than one item, T.start = tweets[min].timestamp, tweets[max].timestamp = T.end, tweets.length() tweets within timespan, more than one tweet overlap with T.start , one tweet overlaps with T.end
    @Test
    public void testInTimespan1() {
        Instant testStart = Instant.parse("2016-02-17T10:00:00Z");
        Instant testEnd = Instant.parse("2016-03-20T10:30:00Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5, tweet6, tweet7), new Timespan(testStart, testEnd));
        
        assertEquals("expected 7 items in a list", 7, inTimespan.size());
        assertTrue("expected list to contain tweets", inTimespan.containsAll(Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5, tweet6, tweet7)));
        assertEquals("expected same order", 0, inTimespan.indexOf(tweet1));
        assertEquals("expected same order", 1, inTimespan.indexOf(tweet2));
        assertEquals("expected same order", 2, inTimespan.indexOf(tweet3));
        assertEquals("expected same order", 3, inTimespan.indexOf(tweet4));
        assertEquals("expected same order", 4, inTimespan.indexOf(tweet5));
        assertEquals("expected same order", 5, inTimespan.indexOf(tweet6));
        assertEquals("expected same order", 6, inTimespan.indexOf(tweet7));
    }
   
//	2. one item, T.start < tweets[min].timestamp,T.end < tweets[min].timestamp,zero tweets from timespan,zero tweet overlap with T.start and T.end
    @Test
    public void testInTimespan2() {
        Instant testStart = Instant.parse("2016-02-16T09:00:00Z");
        Instant testEnd = Instant.parse("2016-02-16T12:00:00Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1), new Timespan(testStart, testEnd));
        
        assertTrue("expected empty list", inTimespan.isEmpty());
    }
    
//	3. one item, tweets[max].timestamp < T.start, tweets[max].timestamp < T.end,zero tweets within timespan,zero tweet overlap with T.start and T.end
    @Test
    public void testInTimespan3() {
        Instant testStart = Instant.parse("2016-02-18T09:00:00Z");
        Instant testEnd = Instant.parse("2016-02-18T12:00:00Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1), new Timespan(testStart, testEnd));
        
        assertTrue("expected empty list", inTimespan.isEmpty());
    }
    
//	4. more than one item, tweets[min].timestamp <= T.start <= tweets[max].timestamp, tweets[min].timestamp <= T.end <= tweets[max].timestamp, 1 < x < tweets.length() tweets with in timespan,zero tweet overlap with T.start and T.end
    @Test
    public void testInTimespan4() {
        Instant testStart = Instant.parse("2016-02-18T09:55:00Z");
        Instant testEnd = Instant.parse("2016-04-22T10:30:00Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet4, tweet5, tweet6, tweet7, tweet8, tweet9, tweet10), new Timespan(testStart, testEnd));
        
        assertFalse("expected non-empty list", inTimespan.isEmpty());
        assertTrue("expected list to contain tweets", inTimespan.containsAll(Arrays.asList(tweet5, tweet6, tweet7, tweet8)));
        assertEquals("expected same order", 0, inTimespan.indexOf(tweet5));
        assertEquals("expected same order", 1, inTimespan.indexOf(tweet6));
        assertEquals("expected same order", 2, inTimespan.indexOf(tweet7));
        assertEquals("expected same order", 3, inTimespan.indexOf(tweet8));
    }
    
//  5. more than one item, tweets[min].timestamp <= T.end <= tweets[max].timestamp, tweets[min].timestamp <= T.start <= tweets[max].timestamp,zero tweets within timepsan
    @Test
    public void testInTimespan5() {
        Instant testStart = Instant.parse("2016-02-17T10:30:00Z");
        Instant testEnd = Instant.parse("2016-02-17T10:35:00Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5, tweet6, tweet7), new Timespan(testStart, testEnd));
        
        assertTrue("expected empty list", inTimespan.isEmpty());
       
    }
    
//	6.  more than one item ,tweets[min].timestamp <= T.start <= tweets[max].timestamp, tweets[min].timestamp <= T.end <= tweets[max].timestamp,
//	    tweets.length() tweets within timespan,and more than one tweet overlap with T.start and T.end
    @Test
    public void testInTimespan6() {
        Instant testStart = Instant.parse("2016-02-17T10:00:00Z");
        Instant testEnd = Instant.parse("2016-04-23T11:30:00Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5, tweet6, tweet7, tweet8, tweet9, tweet10), new Timespan(testStart, testEnd));
        
        assertFalse("expected non-empty list", inTimespan.isEmpty());
        assertTrue("expected list to contain tweets", inTimespan.containsAll(Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5, tweet6, tweet7, tweet8, tweet9, tweet10)));
        assertEquals("expected same order", 0, inTimespan.indexOf(tweet1));
        assertEquals("expected same order", 1, inTimespan.indexOf(tweet2));
        assertEquals("expected same order", 2, inTimespan.indexOf(tweet3));
        assertEquals("expected same order", 3, inTimespan.indexOf(tweet4));
        assertEquals("expected same order", 4, inTimespan.indexOf(tweet5));
        assertEquals("expected same order", 5, inTimespan.indexOf(tweet6));
        assertEquals("expected same order", 6, inTimespan.indexOf(tweet7));
        assertEquals("expected same order", 7, inTimespan.indexOf(tweet8));
        assertEquals("expected same order", 8, inTimespan.indexOf(tweet9));
        assertEquals("expected same order", 9, inTimespan.indexOf(tweet10));
    }
   
//  empty item,zero tweets within timespan,zero tweet overlap with T.start and T.end
    @Test
    public void testInTimespan7() {
        Instant testStart = Instant.parse("2016-02-17T10:00:00Z");
        Instant testEnd = Instant.parse("2016-04-23T11:30:00Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(), new Timespan(testStart, testEnd));
        
        assertTrue("expected empty list", inTimespan.isEmpty());
        
    } 
    
    
    /*---------------------------------------------------------------------------------------------------------------------*/
	
//  1. one item, more than one word, one tweet with atleast one word from words, single word matches with a single tweet.
    @Test
    public void testContaining1() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1), Arrays.asList("talk", "rivest"));
        
        assertTrue("expected list to contain tweets", containing.containsAll(Arrays.asList(tweet1)));
        assertEquals("expected same order", 0, containing.indexOf(tweet1));
    }

//  2. one item , one word, tweets.length() tweet with atleast one word from words, single word matches with a single tweet
    @Test
    public void testContaining2() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1), Arrays.asList("talk"));
       
        assertTrue("expected list to contain tweets", containing.containsAll(Arrays.asList(tweet1)));
        assertEquals("expected same order", 0, containing.indexOf(tweet1));
    }
    
//  3. more than one item, more than one word, all tweets have atleast one word from word, a single word matches multiple tweets.
    @Test
    public void testContaining3() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet2, tweet3, tweet4, tweet5), Arrays.asList("I","hate"));
        
 
        assertTrue("expected list to contain tweets", containing.containsAll(Arrays.asList(tweet2, tweet3, tweet4, tweet5)));
        assertEquals("expected same order", 0, containing.indexOf(tweet2));
        assertEquals("expected same order", 1, containing.indexOf(tweet3));
        assertEquals("expected same order", 2, containing.indexOf(tweet4));
        assertEquals("expected same order", 3, containing.indexOf(tweet5));
    }
  
//  4. more than one item, one word,1 < x < tweets.length() tweets with atleast one word from words, a single word matches multiple tweets.
    @Test
    public void testContaining4() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet5, tweet6, tweet7, tweet8, tweet9, tweet10), Arrays.asList("have"));
        
        assertFalse("expected non-empty list", containing.isEmpty());
        assertTrue("expected list to contain tweets", containing.containsAll(Arrays.asList(tweet7, tweet10)));
        assertEquals("expected same order", 0, containing.indexOf(tweet7));
        assertEquals("expected same order", 1, containing.indexOf(tweet10));
    }
    
//  5. more than one item, more than one word, all tweets have atleast one word from word, a single word matches multiple tweets.
    @Test
    public void testContaining5() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet2, tweet3, tweet4, tweet5), Arrays.asList("farwell"));
        
 
        assertTrue("expected empty list", containing.isEmpty());
        
    }

//  6. zero item, zero tweet with one word from words., zero tweets matching a single word.
    @Test
    public void testContaining6() {
        List<Tweet> containing = Filter.containing(Arrays.asList(), Arrays.asList("talk"));
        
        assertTrue("expected empty list", containing.isEmpty());
    }
    
    
    
    
    
    
    
    
    /*
     * Warning: all the tests you write here must be runnable against any Filter
     * class that follows the spec. It will be run against several staff
     * implementations of Filter, which will be done by overwriting
     * (temporarily) your version of Filter with the staff's version.
     * DO NOT strengthen the spec of Filter or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Filter, because that means you're testing a stronger
     * spec than Filter says. If you need such helper methods, define them in a
     * different class. If you only need them in this test class, then keep them
     * in this test class.
     */

}
