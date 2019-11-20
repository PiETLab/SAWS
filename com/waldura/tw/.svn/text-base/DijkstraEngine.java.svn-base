package com.waldura.tw;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * An implementation of Dijkstra's shortest path algorithm. It computes the
 * shortest path (in distance) to all cities in the map. The output of the
 * algorithm is the shortest distance from the start city to every other city,
 * and the shortest path from the start city to every other.
 * <p>
 * Upon calling {@link #execute(City, City)}, the results of the algorithm are
 * made available by calling {@link #getPredecessor(City)} and
 * {@link #getShortestDistance(City)}.
 * 
 * To get the shortest path between the city <var>destination</var> and the
 * source city after running the algorithm, one would do:
 * 
 * <pre>
 * List l = new ArrayList();
 * 
 * for (City city = destination; city != null; city = engine.getPredecessor(city)) {
 * 	l.add(city);
 * }
 * 
 * Collections.reverse(l);
 * 
 * return l;
 * </pre>
 * 
 * @see #execute(City, City)
 * 
 * @author Renaud Waldura &lt;renaud+tw@waldura.com&gt;
 * @version $Id: DijkstraEngine.java,v 1.4 2003/03/03 01:26:22 renaud Exp $
 */

public class DijkstraEngine {
	/**
	 * Infinity value for distances.
	 */
	public static final int INFINITE_DISTANCE = Integer.MAX_VALUE;

	/**
	 * This comparator orders cities according to their shortest distances, in
	 * ascending fashion. If two cities have the same shortest distance, we
	 * compare the cities themselves.
	 */
	private final Comparator<City> shortestDistanceComparator = new Comparator<City>() {
		// public int compare(Object left, Object right) {
		// assert left instanceof City && right instanceof City : "invalid
		// comparison";
		// return compare((City) left, (City) right);
		// }
		public int compare(City left, City right) {
			// note that this trick doesn't work for huge distances, close to
			// Integer.MAX_VALUE
			int result = getShortestDistance(left) - getShortestDistance(right);

			// MB: in case of ties, below produced a bias toward travelling
			// along diagonal (RiCj -> Ri+1Cj+1, since the row would be tested first)
			// return (result == 0) ? left.compareTo(right) : result;
			
			// MB: in case of ties, below produces a bias toward 
			return (result == 0) ? left.compareTo(right) : result;
		}
	};

	/**
	 * The graph.
	 */
	private final RoutesMap map;

	/**
	 * The working set of cities, kept ordered by shortest distance.
	 */
	private final SortedSet<City> unsettledNodes = new TreeSet<City>(
			shortestDistanceComparator);

	/**
	 * The set of cities for which the shortest distance to the source has been
	 * found.
	 */
	private final Set<City> settledNodes = new HashSet<City>();

	/**
	 * The currently known shortest distance for all cities.
	 */
	private final Map<City, Integer> shortestDistances = new HashMap<City, Integer>();

	/**
	 * Predecessors list: maps a city to its predecessor in the spanning tree of
	 * shortest paths.
	 */
	private final Map<City, City> predecessors = new HashMap<City, City>();

	/**
	 * Constructor.
	 */
	public DijkstraEngine(RoutesMap map) {
		this.map = map;
	}

	/**
	 * Initialize all data structures used by the algorithm.
	 * 
	 * @param start
	 *            the source node
	 */
	private void init(City start) {
		settledNodes.clear();
		unsettledNodes.clear();

		shortestDistances.clear();
		predecessors.clear();

		// add source
		setShortestDistance(start, 0);
		unsettledNodes.add(start);
	}

	/**
	 * Run Dijkstra's shortest path algorithm on the map. The results of the
	 * algorithm are available through {@link #getPredecessor(City)} and
	 * {@link #getShortestDistance(City)} upon completion of this method.
	 * 
	 * @param start
	 *            the starting city
	 * @param destination
	 *            the destination city. If this argument is <code>null</code>,
	 *            the algorithm is run on the entire graph, instead of being
	 *            stopped as soon as the destination is reached.
	 */
	public void execute(City start, City destination) {
		init(start);

		// the current node
		City u;

		// extract the node with the shortest distance
		while ((u = extractMin()) != null) {
			assert !isSettled(u);

			// destination reached, stop
			if (u == destination)
				break;

			markSettled(u);

			relaxNeighbors(u);
		}
	}

	/**
	 * Extract the city with the currently shortest distance, and remove it from
	 * the priority queue.
	 * 
	 * @return the minimum city, or <code>null</code> if the queue is empty.
	 */
	private City extractMin() {
		if (unsettledNodes.isEmpty())
			return null;

		City min = (City) unsettledNodes.first();
		unsettledNodes.remove(min);

		return min;
	}

	/**
	 * Compute new shortest distance for neighboring nodes and update if a
	 * better distance is found.
	 * 
	 * @param u
	 *            the node
	 */
	private void relaxNeighbors(City u) {
		for (Iterator i = map.getDestinations(u).iterator(); i.hasNext();) {
			City v = (City) i.next();

			// skip node already settled
			if (isSettled(v))
				continue;

			if (getShortestDistance(v) > getShortestDistance(u)
					+ map.getDistance(u, v)) {
				// assign new shortest distance and mark unsettled
				setShortestDistance(v, getShortestDistance(u)
						+ map.getDistance(u, v));

				// assign predecessor in shortest path
				setPredecessor(v, u);
			}
		}
	}

	/**
	 * Mark a node as settled.
	 * 
	 * @param u
	 *            the node
	 */
	private void markSettled(City u) {
		settledNodes.add(u);
	}

	/**
	 * Test a node.
	 * 
	 * @param v
	 *            the node to consider
	 * 
	 * @return whether the node is settled, ie. its shortest distance has been
	 *         found.
	 */
	private boolean isSettled(City v) {
		return settledNodes.contains(v);
	}

	/**
	 * @return the shortest distance from the source to the given city, or
	 *         {@link DijkstraEngine#INFINITE_DISTANCE} if there is no route to
	 *         the destination.
	 */
	public int getShortestDistance(City city) {
		Integer d = (Integer) shortestDistances.get(city);
		return (d == null) ? INFINITE_DISTANCE : d.intValue();
	}

	/**
	 * Set the new shortest distance for the given node, and re-balance the set
	 * according to new shortest distances.
	 * 
	 * @param city
	 *            the node to set
	 * @param distance
	 *            new shortest distance value
	 */
	private void setShortestDistance(City city, int distance) {
		// this crucial step ensure no duplicates will be created in the queue
		// when an existing unsettled node is updated with a new shortest
		// distance
		unsettledNodes.remove(city);

		shortestDistances.put(city, new Integer(distance));

		// re-balance the sorted set according to the new shortest distance
		// found
		// (see the comparator the set was initialized with)
		unsettledNodes.add(city);
	}

	/**
	 * @return the city leading to the given city on the shortest path, or
	 *         <code>null</code> if there is no route to the destination.
	 */
	public City getPredecessor(City city) {
		return (City) predecessors.get(city);
	}

	private void setPredecessor(City a, City b) {
		predecessors.put(a, b);
	}

}
