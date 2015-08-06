import java.util.*;

public class Parking {
	
	class Edge {
		int width;
		Vertex toVertex;
		
		Edge(int width, Vertex toVertex) {
			this.width = width;
			this.toVertex = toVertex;
		}
	}
	
	class Vertex {
		ArrayList<Edge> neighbors = new ArrayList<Edge>();
		
		Edge findNeighbor(Vertex toVertex) {
			for (Edge edge : neighbors) {
				if (edge.toVertex == toVertex) {
					return edge;
				}
			}
			return null;
		}
		
		void addEdge(int width, Vertex toVertex) {
			Edge edge = findNeighbor(toVertex);
			if (edge != null) {
				edge.width += width;
			} else {
				neighbors.add(new Edge(width, toVertex));
			}
		}
		
		void subEdge(int width, Vertex toVertex) {
			Edge edge = findNeighbor(toVertex);
			if (edge != null) {
				edge.width -= width;
				if (edge.width == 0) {
					neighbors.remove(edge);
				}
			}
		}
	}
	
	public int minTime(String[] park) {
		ArrayList<ArrayList<Integer>> dists = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < park.length; ++i) {
			for (int j = 0; j < park[i].length(); ++j) {
				if (park[i].charAt(j) == 'C') {
					ArrayList<Integer> distForCar = getDistForOneCar(park, i, j);
					dists.add(distForCar);
				}
			}
		}
		int carCount = dists.size();
		if (carCount == 0) {
			return 0;
		}
		int parkCount = dists.get(0).size();
		if (parkCount < carCount) {
			return -1;
		}
		Vertex[] carVertexes = new Vertex[carCount];
		for (int i = 0; i < carCount; ++i) {
			carVertexes[i] = new Vertex();
		}
		Vertex[] parkVertexes = new Vertex[parkCount];
		for (int i = 0; i < parkCount; ++i) {
			parkVertexes[i] = new Vertex();
		}
		Vertex source = new Vertex();
		Vertex sink = new Vertex();
		int factor = (park.length * park[0].length() + 1);
		for (int i = 0; i < carCount; ++i) {
			source.addEdge(1, carVertexes[i]);
		}
		for (int i = 0; i < parkCount; ++i) {
			parkVertexes[i].addEdge(1, sink);
		}
		PriorityQueue<PQNode> queue = new PriorityQueue<PQNode>(1, new PQNodeComparator());
		for (int i = 0; i < carCount; ++i) {
			for (int j = 0; j < parkCount; ++j) {
				int dist = dists.get(i).get(j);
				if (dist != -1) {
					queue.add(new PQNode(i, j, dist));
				}
			}
		}
		boolean found = false;
		int time = -1;
		int maxFlow = 0;
		while (!queue.isEmpty()) {
			PQNode node = queue.poll();
			time = node.dist;
			carVertexes[node.r].addEdge(1, parkVertexes[node.c]);
			int addFlow = findMaxFlow(source, sink);
			maxFlow += addFlow;
			if (maxFlow == carCount) {
				found = true;
				break;
			}
		}
		return found ? time : -1;
	}
	
	class PQNode {
		int r;
		int c;
		int dist;
		
		PQNode(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}
	
	class PQNodeComparator implements Comparator<PQNode> {
		public int compare(PQNode n1, PQNode n2) {
			if (n1.dist != n2.dist) {
				return n1.dist - n2.dist;
			}
			if (n1.c != n2.c) {
				return n1.c - n2.c;
			}
			return n1.r - n2.r;
		}
	}
	
	
	ArrayList<Integer> getDistForOneCar(String[] park, int carRow, int carCol) {
		int m = park.length;
		int n = park[0].length();
		int[][] dists = new int[m][n];
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				dists[i][j] = -1;
			}
		}
		dists[carRow][carCol] = 0;
		PriorityQueue<PQNode> queue = new PriorityQueue<PQNode>(1, new PQNodeComparator());
		queue.add(new PQNode(carRow, carCol, 0));
		int[] dr = new int[]{-1, 1, 0, 0};
		int[] dc = new int[]{0, 0, -1, 1};
		while (!queue.isEmpty()) {
			PQNode cur = queue.poll();
			if (cur.dist > dists[cur.r][cur.c]) {
				continue;
			}
			for (int i = 0; i < dr.length; ++i) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				if (nr >= 0 && nr < m && nc >= 0 && nc < n && park[nr].charAt(nc) != 'X' &&
						(dists[nr][nc] == -1 || dists[nr][nc] > cur.dist + 1)) {
					dists[nr][nc] = cur.dist + 1;
					queue.add(new PQNode(nr, nc, dists[nr][nc]));
				}
			}
		}
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				if (park[i].charAt(j) == 'P') {
					result.add(dists[i][j]);
				}
			}
		}
		return result;
	}
	
	class BFSNode {
		BFSNode prev;
		int width;
		Vertex vertex;
		
		BFSNode(BFSNode prev, int width, Vertex vertex) {
			this.prev = prev;
			this.width = width;
			this.vertex = vertex;
		}
	}
	
	int findMaxFlow(Vertex source, Vertex sink) {
		int result = 0;
		while (true) {
			HashSet<Vertex> hitSet = new HashSet<Vertex>();
			Queue<BFSNode> queue = new ArrayDeque<BFSNode>();
			queue.add(new BFSNode(null, 0, source));
			BFSNode bfsNode = null;
			boolean found = false;
			while (!queue.isEmpty()) {
				BFSNode node = queue.poll();
				for (Edge edge : node.vertex.neighbors) {
					if (edge.toVertex == sink) {
						found = true;
						bfsNode = new BFSNode(node, edge.width, sink);
						break;
					} else if (!hitSet.contains(edge.toVertex)) {
						hitSet.add(edge.toVertex);
						queue.add(new BFSNode(node, edge.width, edge.toVertex));
					}
				}
				if (found) {
					break;
				}
			}
			if (bfsNode == null) {
				break;
			}
			BFSNode last = bfsNode;
			int minWidth = Integer.MAX_VALUE;
			for (; bfsNode.prev != null; bfsNode = bfsNode.prev) {
				minWidth = Math.min(minWidth, bfsNode.width);
			}
			result += minWidth;
			for (bfsNode = last; bfsNode.prev != null; bfsNode = bfsNode.prev) {
				Vertex from = bfsNode.prev.vertex;
				Vertex to = bfsNode.vertex;
				from.subEdge(minWidth, to);
				to.addEdge(minWidth, from);
			}
		}
		return result;
	}
}