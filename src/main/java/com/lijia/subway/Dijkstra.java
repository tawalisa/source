package com.lijia.subway;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

class Vertex implements Comparable<Vertex> {
	public final String name;
	public Set<Edge> adjacencies = new HashSet<Edge>();
	public double minDistance = Double.POSITIVE_INFINITY;
	public Vertex previous;

	public Vertex(String argName) {
		name = argName;
	}

	public String toString() {
		return name;
	}

	public int compareTo(Vertex other) {
		return Double.compare(minDistance, other.minDistance);
	}
}

class Edge {
	public final Vertex target;
	public final double weight;

	public Edge(Vertex argTarget, double argWeight) {
		target = argTarget;
		weight = argWeight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		return true;
	}
}

public class Dijkstra {
	public void computePaths(Vertex source) {
		source.minDistance = 0.;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
			Vertex u = vertexQueue.poll();

			// Visit each edge exiting u
			for (Edge e : u.adjacencies) {
				Vertex v = e.target;
				double weight = e.weight;
				double distanceThroughU = u.minDistance + weight;
				if (distanceThroughU < v.minDistance) {
					vertexQueue.remove(v);
					v.minDistance = distanceThroughU;
					v.previous = u;
					vertexQueue.add(v);
				}
			}
		}
	}

	public List<String> getShortestPathTo(Vertex target) {
		List<String> path = new ArrayList<String>();
		for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
			path.add(vertex.name);
		Collections.reverse(path);
		return path;
	}
	
	public static void main(String[] args) {
		Vertex v0 = new Vertex("北京");
		Vertex v1 = new Vertex("沈阳");
		Vertex v2 = new Vertex("上海");
		Vertex v3 = new Vertex("广州");
		Vertex v4 = new Vertex("深圳");
		

		v0.adjacencies.add(new Edge(v1, 5));
		v0.adjacencies.add(new Edge(v2, 10));
		v0.adjacencies.add(new Edge(v3, 8));
		v1.adjacencies.add(new Edge(v0, 5));
		v1.adjacencies.add(new Edge(v2, 3));
		v1.adjacencies.add(new Edge(v4, 7));
		v2.adjacencies.add(new Edge(v0, 10));
		v2.adjacencies.add(new Edge(v1, 3));
		v3.adjacencies.add(new Edge(v0, 8));
		v3.adjacencies.add(new Edge(v4, 2));
		v4.adjacencies.add(new Edge(v1, 7));
		v4.adjacencies.add(new Edge(v3, 2));
		Vertex[] vertices = { v0, v1, v2, v3, v4 };
		Dijkstra di = new Dijkstra();
		di.computePaths(v1);
		for (Vertex v : vertices) {
			System.out.println("Distance to " + v + ": " + v.minDistance);
			List<String> path = di.getShortestPathTo(v);
			System.out.println("Path: " + path);
		}
	}
}