package com.waldura.tw;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

/**
 * DijkstraEngineTest to validate the DijkstraEngine class.
 * 
 * @author Renaud Waldura &lt;renaud+tw@waldura.com&gt;
 * @version $Id: DijkstraEngineTest.java,v 1.3 2003/12/01 21:58:06 renaud Exp $
 */
public class DijkstraEngineTest extends TestCase
{
    /**
     * This test was used to test the issue brought forth by Marc Barry. 
     * This graph is used in my Dijkstra article.
     */
    private static class MockMap3 implements RoutesMap
    {
        private final boolean withNodeE;
                
		public MockMap3(boolean withNodeE)
		{
            this.withNodeE = withNodeE;
		}

        public void addDirectRoute(City start, City end, int distance)
        {
            throw new UnsupportedOperationException();
        }
        
        public int getDistance(City start, City end)
        {
            if (start == City.A)
            {
                if (end == City.B) return 4;
                if (end == City.C) return 2;
                if (withNodeE && end == City.E) return 1;
            }
            else if (start == City.B)
            {
                if (end == City.D) return 1;
                if (end == City.C) return 3;
            }
            else if (start == City.C)
            {
                if (end == City.A) return 2;
                if (end == City.B) return 1;
                if (end == City.D) return 5;
            }
            else if (withNodeE && start == City.E)
            {
                if (end == City.D) return 2;
            }

            return DijkstraEngine.INFINITE_DISTANCE;
        }
        
        public List getDestinations(City city)
        {
            if (city == City.A)
            {
                return Arrays.asList((withNodeE) 
                    ? new City[] { City.B, City.C, City.E }
                    : new City[] { City.B, City.C });
            }
            else if (city == City.B)
            {
                return Arrays.asList(new City[] { City.D, City.C });
            }
            else if (city == City.C)
            {
                return Arrays.asList(new City[] { City.A, City.B, City.D });
            }
            else if (withNodeE && city == City.E)
            {
                return Collections.singletonList(City.D);
            }

            return Collections.EMPTY_LIST;
        }
        
        public List getPredecessors(City city)
        {
            throw new UnsupportedOperationException();
        }
        
        public RoutesMap getInverse()
        {
            throw new UnsupportedOperationException();
        }
    };

    /**
     * This map was used to test and validate the fix for the issue brought forth 
     * by Carl Schwarcz: the <code>Comparator</code> object is used by the SortedSet
     * for object ordering AND identity. My implementation incorrectly reported
     * some nodes as equal.
     */
    private static class MockMap2 implements RoutesMap
    {
        public void addDirectRoute(City start, City end, int distance)
        {
            throw new UnsupportedOperationException();
        }
        
        public int getDistance(City start, City end)
        {
            if (start == City.A)
            {
                if (end == City.B) return 2;
                if (end == City.C) return 7;
                if (end == City.D) return 8;
                if (end == City.E) return 9;
            }
            else if (start == City.B)
            {
                return 3;
            }
            else if (start == City.E)
            {
                return 1;
            }

            return DijkstraEngine.INFINITE_DISTANCE;
        }
        
        public List getDestinations(City city)
        {
            if (city == City.A)
            {
                return Arrays.asList(new City[] { City.B, City.C, City.D, City.E });
            }
            else if (city == City.B)
            {
                return Collections.singletonList(City.E);
            }
            else if (city == City.E)
            {
                return Collections.singletonList(City.F);
            }
            else
            {
                return Collections.EMPTY_LIST;
            }
        }
        
        public List getPredecessors(City city)
        {
            throw new UnsupportedOperationException();
        }
        
        public RoutesMap getInverse()
        {
            throw new UnsupportedOperationException();
        }
    };

    /**
     * Test map.
     * 
     * <pre>
     *     4
     * A -----> B 
     * \        \ 
     *  \ 4      \ 2
     *   \        \
     *    \/       \/
     *     C -----> D 
     *         1
     * </pre>
     */
    private static class MockMap1 implements RoutesMap
	{
		public void addDirectRoute(City start, City end, int distance)
		{
            throw new UnsupportedOperationException();
		}
        
		public int getDistance(City start, City end)
		{
            if (start == City.A)
            {
                return 4;
            }
            else if (start == City.B)
            {
                return 2;
            }
            else if (start == City.C)
            {
                return 1;
            }
            else
            {
                return DijkstraEngine.INFINITE_DISTANCE;
            }
		}
        
		public List getDestinations(City city)
		{
			if (city == City.A)
            {
                return Arrays.asList(new City[] { City.B, City.C });
            }
            else if (city == City.B || city == City.C)
            {
                return Collections.singletonList(City.D);
            }
            else
            {
                return Collections.EMPTY_LIST;
            }
		}
        
		public List getPredecessors(City city)
		{
            throw new UnsupportedOperationException();
		}
        
		public RoutesMap getInverse()
		{
			throw new UnsupportedOperationException();
		}
	};
    
    public DijkstraEngineTest(String name)
    {
        super(name);
    }
    
    private DijkstraEngine engine;
    
    /**
	 * Test the <code>MockMap1</code>.
	 */
	public void testEngine1()
    {
        engine = new DijkstraEngine(new MockMap1());
        engine.execute(City.A, null);

        // sanity checks
        assertPredecessorAndShortestDistance(City.A, null, 0);
        assertPredecessorAndShortestDistance(City.E, null, DijkstraEngine.INFINITE_DISTANCE);
        
        // shortest path from A to D
        assertPredecessorAndShortestDistance(City.D, City.C, 5);
        assertPredecessorAndShortestDistance(City.C, City.A, 4);
    }
    
    private void assertPredecessorAndShortestDistance(City c, City pred, int sd)
    {
        assertEquals("incorrect shortest path", pred, engine.getPredecessor(c));
        assertEquals("incorrect shortest distance", sd, engine.getShortestDistance(c));
    }
        
    /**
	 * Test the <code>MockMap2</code> (Carl Schwarcz fix).
	 */
	public void testEngine2()
    {
        engine = new DijkstraEngine(new MockMap2());
        engine.execute(City.A, null);

        // sanity check
        assertPredecessorAndShortestDistance(City.A, null, 0);
        
        // shortest path from A to F
        assertPredecessorAndShortestDistance(City.F, City.E, 6);
        assertPredecessorAndShortestDistance(City.E, City.B, 5);
        assertPredecessorAndShortestDistance(City.B, City.A, 2);
    }
    
    /**
	 * Test the <code>MockMap3</code> without a <code>E</code> node.
	 */
	public void testEngine3a()
    {
        engine = new DijkstraEngine(new MockMap3(false));
        engine.execute(City.A, null);

        // sanity checks
        assertPredecessorAndShortestDistance(City.A, null, 0);
        assertPredecessorAndShortestDistance(City.E, null, DijkstraEngine.INFINITE_DISTANCE);
        
        // shortest path from A to D
        assertPredecessorAndShortestDistance(City.D, City.B, 4);
        assertPredecessorAndShortestDistance(City.B, City.C, 3);
        assertPredecessorAndShortestDistance(City.C, City.A, 2);
    }
    
    /**
	 * Test the <code>MockMap3</code> with a <code>E</code> node.
	 */
	public void testEngine3b()
    {
        engine = new DijkstraEngine(new MockMap3(true));
        engine.execute(City.A, null);

        // sanity check
        assertPredecessorAndShortestDistance(City.A, null, 0);
        
        // shortest path from A to E
        assertPredecessorAndShortestDistance(City.D, City.E, 3);
        assertPredecessorAndShortestDistance(City.E, City.A, 1);
    }    
 
}
