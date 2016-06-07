import java.util.*;

public class Graduation {
	
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
	
	public String moreClasses(String classesTaken, String[] requirements) {
		int classCount = 128;
		Node[] classNodes = new Node[classCount];
		for (int i = 0; i < classCount; ++i) {
			classNodes[i] = new Node();
		}
		int requireCount = requirements.length;
		Node[] requireNodes = new Node[requireCount];
		for (int i = 0; i < requireCount; ++i) {
			requireNodes[i] = new Node();
		}
		Node source = new Node();
		Node sink = new Node();
		for (int i = 0; i < classCount; ++i) {
			source.addEdge(1, classNodes[i]);
		}
		int totalFlow = 0;
		for (int i = 0; i < requirements.length; ++i) {
			String s = requirements[i];
			int digitCount = 0;
			for (; digitCount < s.length() &&
					s.charAt(digitCount) >= '0' && s.charAt(digitCount) <= '9'; ++digitCount) {
			}
			int count = Integer.parseInt(s.substring(0, digitCount));
			totalFlow += count;
			requireNodes[i].addEdge(count, sink);
			for (int j = digitCount; j < s.length(); ++j) {
				classNodes[s.charAt(j)].addEdge(1, requireNodes[i]);
			}
		}
		int maxFlow = findMaxFlow(source, sink);
		if (maxFlow != totalFlow) {
			return "0";
		}
		boolean[] takenClasses = new boolean[classCount];
		for (int i = 0; i < classesTaken.length(); ++i) {
			takenClasses[classesTaken.charAt(i)] = true;
		}
		StringBuilder builder = new StringBuilder();
		for (int i = classCount - 1; i >= 0; --i) {
			if (!takenClasses[i]) {
				Edge edge = classNodes[i].findNeighbor(source);
				if (edge != null && edge.width == 1) {
					findAndReversePath(sink, classNodes[i]);
					classNodes[i].neighbors.clear();
					int addFlow = findMaxFlow(source, sink);
					if (addFlow == 0) {
						maxFlow--;
						builder.append((char)i);
					}
				} else {
					classNodes[i].neighbors.clear();
				}
			}
		}
		return builder.reverse().toString();
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
	
	BFSNode findPath(Node source, Node sink) {
		HashSet<Node> hitSet = new HashSet<Node>();
		Queue<BFSNode> queue = new ArrayDeque<BFSNode>();
		queue.add(new BFSNode(null, 0, source));
		hitSet.add(source);
		boolean found = false;
		BFSNode bfsNode = null;
		while (!queue.isEmpty()) {
			BFSNode cur = queue.poll();
			for (Edge edge : cur.cur.neighbors) {
				bfsNode = new BFSNode(cur, edge.width, edge.toNode);
				if (edge.toNode == sink) {
					found = true;
					break;
				}
				if (!hitSet.contains(edge.toNode)) {
					hitSet.add(edge.toNode);
					queue.add(bfsNode);
				}
			}
			if (found) {
				break;
			}
		}
		if (found) {
			return bfsNode;
		}
		return null;
	}
	
	int getPathWidth(BFSNode bfsNode) {
		int minWidth = Integer.MAX_VALUE;
		for (; bfsNode.prev != null; bfsNode = bfsNode.prev) {
			minWidth = Math.min(minWidth, bfsNode.width);
		}
		return minWidth;
	}
	
	void reversePath(int minWidth, BFSNode bfsNode) {
		for (; bfsNode.prev != null; bfsNode = bfsNode.prev) {
			Node from = bfsNode.prev.cur;
			Node to = bfsNode.cur;
			from.subEdge(minWidth, to);
			to.addEdge(minWidth, from);
		}
	}
	
	int findMaxFlow(Node source, Node sink) {
		int result = 0;
		while (true) {
			BFSNode bfsNode = findPath(source, sink);
			if (bfsNode == null) {
				break;
			}
			int minWidth = getPathWidth(bfsNode);
			result += minWidth;
			reversePath(minWidth, bfsNode);
		}
		return result;
	}
	
	void findAndReversePath(Node source, Node sink) {
		BFSNode bfsNode = findPath(source, sink);
		int minWidth = getPathWidth(bfsNode);
		reversePath(minWidth, bfsNode);
	}
}















