package com.waldura.tw;

import java.util.List;

import junit.framework.TestCase;

public class DenseRoutesMapTest extends TestCase
{
	private RoutesMap map;
	
	public DenseRoutesMapTest(String name)
	{
		super(name);
	}

	protected void setUp()
	{
		map = new DenseRoutesMap(5);
		map.addDirectRoute(City.A, City.B, 5);
		map.addDirectRoute(City.A, City.C, 3);
		map.addDirectRoute(City.A, City.D, 2);
		map.addDirectRoute(City.E, City.C, 1);		
	}
	
	public void testDistance()
	{
		assertDistanceEquals(City.A, City.B, 5);		
		assertDistanceEquals(City.A, City.D, 2);		
		assertDistanceEquals(City.E, City.C, 1);
	}
	
	public void testDefautDistance()
	{
		assertDistanceEquals(City.A, City.A, 0);
		assertDistanceEquals(City.D, City.E, 0);
	}
		
	private void assertDistanceEquals(City start, City stop, int expectedDistance)
	{
		assertEquals(
			"wrong distance", 
			expectedDistance,
			map.getDistance(start, stop)
    		);		
	}
	
	public void testDestinations()
	{
		List l = map.getDestinations(City.A);
		
		assertEquals("incorrect number of destinations", 3, l.size());
		assertSame(City.B, l.get(0));
		assertSame(City.C, l.get(1));
		assertSame(City.D, l.get(2));
	}
	
	public void testPredecessors()
	{
		List l = map.getPredecessors(City.A);		
		assertEquals("incorrect number of predecessors", 0, l.size());
		
		l = map.getPredecessors(City.C);		
		assertEquals("incorrect number of predecessors", 2, l.size());
		assertSame(City.A, l.get(0));
		assertSame(City.E, l.get(1));
	}
	
	public void testInverse()
	{
		map = map.getInverse();
		
		assertDistanceEquals(City.B, City.A, 5);
		assertDistanceEquals(City.D, City.A, 2);
		assertDistanceEquals(City.C, City.E, 1);
		
		assertDistanceEquals(City.A, City.B, 0);
		assertDistanceEquals(City.A, City.D, 0);
		assertDistanceEquals(City.E, City.C, 0);
	}
}
