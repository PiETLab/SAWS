package com.waldura.tw;

import junit.framework.TestCase;

public class CityTest extends TestCase
{
	public CityTest(String name)
	{
		super(name);
	}

	public void testIdentity()
	{
		assertSame( City.valueOf('A'), City.valueOf('A') );
		assertSame(	City.valueOf('A'), City.A );
		assertSame( City.A,	City.valueOf(0) );
			
		assertDifferent( City.valueOf('D'), City.valueOf('E') );
	}
	
    public void testEquality()
    {
        assertEquals( City.valueOf('A'), City.valueOf('A') );
        assertEquals( City.valueOf('A'), City.A );
        assertEquals( City.A, City.A );
        
        assertNotEquals(City.A, City.B);
    }
		
	public void testInvalidValueOf()
	{
		try
		{
			City.valueOf(' ');
			fail("should have failed");	
		}
		catch (IllegalArgumentException iae)
		{
			// this is the successful case		
		}
	}
	
	public void testGetIndex()
	{
		assertIndexEquals('A', 0);
		assertIndexEquals('Z', 25);
	}
	
	public void testName()
	{
        assertEquals(City.C.getName(), City.C.getName());    
		assertNotEquals(City.D.getName(), City.E.getName());	
	}
	
    public void testCompareTo()
    {
        assertTrue("incorrect comparison", City.A.compareTo(City.A) == 0);
        
        assertTrue("incorrect comparison", City.A.compareTo(City.B) < 0);
        assertTrue("incorrect comparison", City.B.compareTo(City.C) < 0);
        assertTrue("incorrect comparison", City.A.compareTo(City.C) < 0);
        
        assertTrue("incorrect comparison", City.B.compareTo(City.A) > 0);
        assertTrue("incorrect comparison", City.C.compareTo(City.B) > 0);
        assertTrue("incorrect comparison", City.C.compareTo(City.A) > 0);
    }

    private void assertIndexEquals(char c, int expectedIndex)
    {
        assertEquals(expectedIndex, City.valueOf(c).getIndex());            
    }
    
    private void assertNotEquals(char c1, char c2)
    {
        assertTrue("expected not equal", c1 != c2); 
    }
    
    private void assertNotEquals(Object o1, Object o2)
    {
        assertTrue("expected not equal", !o1.equals(o2)); 
    }
    
    private void assertDifferent(Object o1, Object o2)
    {
        assertTrue("expected different", o1 != o2); 
    }

}
