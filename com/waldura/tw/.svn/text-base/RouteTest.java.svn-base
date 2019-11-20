package com.waldura.tw;

import junit.framework.TestCase;

public class RouteTest extends TestCase
{
	public RouteTest(String name)
	{
		super(name);
	}

	public void testNoStops()
	{	
		Route route = new Route();
		route.addStop(City.A, 0);
		 
		assertRoute(route, City.A, 0, 0);
		assertTrue("route should have start city", route.hasCity(City.A));
	}
	
	public void testAddStop()
	{
		Route route = new Route();		
		route.addStop(City.A, 0);
		route.addStop(City.B, 3);
		route.addStop(City.C, 2);
		route.addStop(City.E, 1);

		assertRoute(route, City.E, 6, 3);
		
		assertTrue("city should be present", route.hasCity(City.A));
		assertTrue("city should be present", route.hasCity(City.B));
	}
	
	static void assertRoute(Route route, City expectedLastStop, int expectedDistance, int expectedLength)
	{		
		assertSame("incorrect last stop", expectedLastStop, route.getLastStop());
		assertEquals("incorrect distance", expectedDistance, route.getDistance());
		assertEquals("incorrect length", expectedLength, route.getLength());
	}
}
