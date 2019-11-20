package com.waldura.tw;

import java.util.ArrayList;
import java.util.List;

/**
 * This map stores routes in a matrix, a nxn array. It is most useful when there
 * are lots of routes, otherwise using a sparse representation is recommended.
 * 
 * @author Renaud Waldura &lt;renaud+tw@waldura.com&gt;
 * @version $Id: DenseRoutesMap.java,v 1.1 2002/11/16 20:37:52 renaud Exp $
 */

public class DenseRoutesMap implements RoutesMap {
	private final int NO_EDGE_FLAG = -1;

	private final int[][] distances;

	public DenseRoutesMap(int numCities) {
		//System.out.println("Dim: " + numCities);
		distances = new int[numCities + 1][numCities + 1];
		for (int row = 0; row < numCities + 1; row++) {
			for (int col = 0; col < numCities + 1; col++) {
				distances[row][col] = NO_EDGE_FLAG;
			}
		}
	}

	/**
	 * Link two cities by a direct route with the given distance.
	 */
	public void addDirectRoute(City start, City end, int distance) {
		distances[start.getIndex()][end.getIndex()] = distance;
	}

	/**
	 * @return the distance between the two cities, or NO_EDGE_FLAG if no path
	 *         exists.
	 */
	public int getDistance(City start, City end) {
		return distances[start.getIndex()][end.getIndex()];
	}

	/**
	 * @return the list of all valid destinations from the given city.
	 */
	public List<City> getDestinations(City city) {
		List<City> list = new ArrayList<City>();

		for (int i = 0; i < distances.length; i++) {
			// remove this condition
			// make -1 a signal of lack of connect between cities
			// we want to have distance of 0
			// if (distances[city.getIndex()][i] > 0) {
			if (distances[city.getIndex()][i] != NO_EDGE_FLAG) {
				list.add(City.valueOf(i));
			}
		}

		return list;
	}

	/**
	 * @return the list of all cities leading to the given city.
	 */
	public List getPredecessors(City city) {
		List list = new ArrayList();

		for (int i = 0; i < distances.length; i++) {
			// if (distances[i][city.getIndex()] > 0) {
			if (distances[i][city.getIndex()] != NO_EDGE_FLAG) {
				list.add(City.valueOf(i));
			}
		}

		return list;
	}

	/**
	 * @return the transposed graph of this graph, as a new RoutesMap instance.
	 */
	public RoutesMap getInverse() {
		DenseRoutesMap transposed = new DenseRoutesMap(distances.length);

		for (int i = 0; i < distances.length; i++) {
			for (int j = 0; j < distances.length; j++) {
				transposed.distances[i][j] = distances[j][i];
			}
		}

		return transposed;
	}
}
