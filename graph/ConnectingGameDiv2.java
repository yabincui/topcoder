import java.util.*;

public class ConnectingGameDiv2 {
	final int INF = 100000000;

	class Node {
		ArrayList<Edge> edges;
		
		Node() {
			edges = new ArrayList();
		}
		
		void addEdge(int width, Node toNode) {
			for (Edge edge : edges) {
				if (edge.toNode == toNode) {
					edge.left_width += width;
					return;
				}
			}
			edges.add(new Edge(width, toNode));
		}
	}
	
	class Edge {
		int left_width;
		Node toNode;
		
		Edge(int width, Node toNode) {
			this.left_width = width;
			this.toNode = toNode;
		}
	}

	public int getmin(String[] board) {
		HashMap<Character, Integer> char_to_pos_map = new HashMap();
		int rows = board.length;
		int cols = board[0].length();
		int[][] pos_board = new int[rows][cols];
		for (int r = 0; r < rows; ++r) {
			for (int c = 0; c < cols; ++c) {
				char ch = board[r].charAt(c);
				Integer pos = char_to_pos_map.get(ch);
				if (pos == null) {
					pos = char_to_pos_map.size();
					char_to_pos_map.put(ch, pos);
				}
				pos_board[r][c] = pos;
			}
		}

		int n = char_to_pos_map.size();
		int[] size = new int[n];
		boolean[][] connect = new boolean[n][n];
		int[] dr = new int[]{1, 0};
		int[] dc = new int[]{0, 1};
		for (int r = 0; r < rows; ++r) {
			for (int c = 0; c < cols; ++c) {
				int pos = pos_board[r][c];
				size[pos]++;
				if (r + 1 < rows) {
					int npos = pos_board[r + 1][c];
					connect[pos][npos] = connect[npos][pos] = true;
				}
				if (c + 1 < cols) {
					int npos = pos_board[r][c + 1];
					connect[pos][npos] = connect[npos][pos] = true;
				}
			}
		}
		boolean[] is_source_range = new boolean[n];
		boolean[] is_sink_range = new boolean[n];
		for (int c = 0; c < cols; ++c) {
			is_source_range[pos_board[0][c]] = true;
			is_sink_range[pos_board[rows - 1][c]] = true;
		}
		
		// build the flow graph, each range is an edge, having a start node and end node.
		Node[] start_nodes = new Node[n];
		Node[] end_nodes = new Node[n];
		for (int i = 0; i < n; ++i) {
			start_nodes[i] = new Node();
			end_nodes[i] = new Node();
			start_nodes[i].addEdge(size[i], end_nodes[i]);
			//System.out.printf("start_nodes[%d].addEdge(%d, end_edges[%d]\n", i, size[i], i);
		}
		Node source = new Node();
		Node sink = new Node();
	
		
		for (int i = 0; i < n; ++i) {
			if (is_source_range[i]) {
				source.addEdge(INF, start_nodes[i]);
				//System.out.printf("source.addEdge(start_nodes[%d]\n", i);
			}
			if (is_sink_range[i]) {
				end_nodes[i].addEdge(INF, sink);
				//System.out.printf("end_nodes[%d].addEdge(sink)\n", i);
			}
			for (int j = i + 1; j < n; ++j) {
				if (connect[i][j]) {
					end_nodes[i].addEdge(INF, start_nodes[j]);
					end_nodes[j].addEdge(INF, start_nodes[i]);
					//System.out.printf("end_nodes[%d].addEdge(start_nodes[%d]\n", i, j);
					//System.out.printf("end_nodes[%d].addEdge(start_nodes[%d]\n", j, i);
				}
			}
		}
		
		int result = findMaxFlow(source, sink);
		return result;
	}
	
	private int findMaxFlow(Node source, Node sink) {
		int result = 0;
		while (true) {
			ArrayList<Edge> path = new ArrayList<Edge>();
			HashSet<Node> visited = new HashSet<Node>();
			//System.out.printf("findMaxFlow, before dfs, result = %d\n", result);
			if (!dfs(source, sink, visited, path)) {
				//System.out.printf("dfs false\n");
				break;
			}
			//System.out.printf("dfs true\n");
			int flow = INF;
			for (Edge edge : path) {
				flow = Math.min(flow, edge.left_width);
			}
			//System.out.printf("flow = %d\n", flow);
			Node from = source;
			for (int i = 0; i < path.size(); ++i) {
				path.get(i).left_width -= flow;
				Node to = path.get(i).toNode;
				to.addEdge(flow, from);
				from = to;
			}
			result += flow;
		}
		return result;
	}
	
	private boolean dfs(Node source, Node sink, HashSet<Node> visited, ArrayList<Edge> path) {
		for (Edge edge : source.edges) {
			if (edge.toNode == sink) {
				path.add(edge);
				return true;
			}
			if (edge.left_width == 0 || visited.contains(edge.toNode)) {
				continue;
			}
			visited.add(edge.toNode);
			path.add(edge);
			if (dfs(edge.toNode, sink, visited, path)) {
				return true;
			}
			path.remove(path.size() - 1);
		}
		return false;
	}
}
