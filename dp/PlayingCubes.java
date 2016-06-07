import java.util.*;

public class PlayingCubes {
	
	class Edge {
		int width;
		Node toNode;
		
		Edge(int width, Node toNode) {
			this.width = width;
			this.toNode = toNode;
		}
	}
	
	class Node {
		ArrayList<Edge> neighbors = new ArrayList<Edge>();
		
		Edge findNeighbor(Node toNode) {
			for (Edge edge : neighbors) {
				if (edge.toNode == toNode) {
					return edge;
				}
			}
			return null;
		}
		
		void addEdge(int width, Node toNode) {
			Edge edge = findNeighbor(toNode);
			if (edge != null) {
				edge.width += width;
			} else {
				neighbors.add(new Edge(width, toNode));
			}
		}
		
		void subEdge(int width, Node toNode) {
			Edge edge = findNeighbor(toNode);
			if (edge != null) {
				edge.width -= width;
				if (edge.width == 0) {
					neighbors.remove(edge);
				}
			}
		}
	}
	
	public int[] composeWords(String[] cubes, String[] words) {
		ArrayList<Integer> formatable = new ArrayList<Integer>();
		for (int i = 0; i < words.length; ++i) {
			Node[] cubeNodes = new Node[cubes.length];
			for (int j = 0; j < cubeNodes.length; ++j) {
				cubeNodes[j] = new Node();
			}
			Node[] wordNodes = new Node[words[i].length()];
			for (int j = 0; j < wordNodes.length; ++j) {
				wordNodes[j] = new Node();
			}
			Node source = new Node();
			Node sink = new Node();
			for (int j = 0; j < wordNodes.length; ++j) {
				source.addEdge(1, wordNodes[j]);
			}
			for (int j = 0; j < wordNodes.length; ++j) {
				for (int k = 0; k < cubeNodes.length; ++k) {
					if (cubes[k].indexOf(words[i].charAt(j)) != -1) {
						wordNodes[j].addEdge(1, cubeNodes[k]);
					}
				}
			}
			for (int j = 0; j < cubeNodes.length; ++j) {
				cubeNodes[j].addEdge(1, sink);
			}
			int maxFlow = findMaxFlow(source, sink);
			if (maxFlow == wordNodes.length) {
				formatable.add(i);
			}
		}
		int[] result = new int[formatable.size()];
		for (int i = 0; i < formatable.size(); ++i) {
			result[i] = formatable.get(i);
		}
		return result;
	}
	
	class BFSNode {
		BFSNode prev;
		int width;
		Node cur;
		
		BFSNode(BFSNode prev, int width, Node cur) {
			this.prev = prev;
			this.width = width;
			this.cur = cur;
		}
	}
	
	int findMaxFlow(Node source, Node sink) {
		int result = 0;
		while (true) {
			HashSet<Node> hitSet = new HashSet<Node>();
			Queue<BFSNode> queue = new ArrayDeque<BFSNode>();
			queue.add(new BFSNode(null, 0, source));
			hitSet.add(source);
			BFSNode bfsNode = null;
			boolean found = false;
			while (!queue.isEmpty()) {
				BFSNode curBFSNode = queue.poll();
				for (Edge edge : curBFSNode.cur.neighbors) {
					if (edge.toNode == sink) {
						bfsNode = new BFSNode(curBFSNode, edge.width, edge.toNode);
						found = true;
						break;
					}
					if (!hitSet.contains(edge.toNode)) {
						hitSet.add(edge.toNode);
						queue.add(new BFSNode(curBFSNode, edge.width, edge.toNode));
					}
				}
			}
			if (!found) {
				break;
			}
			BFSNode last = bfsNode;
			int minWidth = Integer.MAX_VALUE;
			for (; bfsNode.prev != null; bfsNode = bfsNode.prev) {
				minWidth = Math.min(minWidth, bfsNode.width);
			}
			result += minWidth;
			for (bfsNode = last; bfsNode.prev != null; bfsNode = bfsNode.prev) {
				Node from = bfsNode.prev.cur;
				Node to = bfsNode.cur;
				from.subEdge(minWidth, to);
				to.addEdge(minWidth, from);
			}
		}
		return result;
	}
}