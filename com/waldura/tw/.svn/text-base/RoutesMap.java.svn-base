package com.waldura.tw;

import java.util.List;

/**
 * This interface defines the object storing the graph of all routes in the
 * system.
 * 
 * @author Renaud Waldura &lt;renaud+tw@waldura.com&gt;
 * @version $Id: RoutesMap.java,v 1.1 2002/11/16 20:37:51 renaud Exp $
 */

public interface RoutesMap {
	/**
	 * Enter a new segment in the graph.
	 */
	public void addDirectRoute(City start, City end, int distance);

	/**
	 * Get the value of a segment.
	 */
	public int getDistance(City start, City end);

	/**
	 * Get the list of cities that can be reached from the given city.
	 */
	public List getDestinations(City city);

	/**
	 * Get the list of cities that lead to the given city.
	 */
	public List getPredecessors(City city);

	/**
	 * @return the transposed graph of this graph, as a new RoutesMap instance.
	 */
	public RoutesMap getInverse();
}
