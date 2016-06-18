import java.util.*;
import static org.junit.Assert.*;

public class ErdosNumber {
  class Node {
    String name;
    int number;
    ArrayList<Node> neighbors;

    Node(String name) {
      this.name = name;
      number = -1;
      neighbors = new ArrayList<Node>();
    }

    void addNeighbor(Node neighbor) {
      for (int i = 0; i < neighbors.size(); ++i) {
        if (neighbors.get(i) == neighbor) {
          return;
        }
      }
      neighbors.add(neighbor);
      neighbor.neighbors.add(this);
    }
  }

  class NodeWithDist {
    Node node;
    int dist;
    NodeWithDist(Node node, int dist) {
      this.node = node;
      this.dist = dist;
    }
  }

  class NodeWithDistComparator implements Comparator<NodeWithDist> {
    public int compare(NodeWithDist d1, NodeWithDist d2) {
      return d1.dist - d2.dist;
    }
  }

  class NodeNameComparator implements Comparator<Node> {
    public int compare(Node n1, Node n2) {
      return n1.name.compareTo(n2.name);
    }
  }

  public String[] calculateNumbers(String[] publications) {
    HashMap<String, Node> hit_map = new HashMap<String, Node>();
    for (String pub : publications) {
      String[] authors = pub.split(" ");
      int n = authors.length;
      Node[] nodes = new Node[n];
      for (int i = 0; i < n; ++i) {
        Node node = hit_map.get(authors[i]);
        if (node == null) {
          node = new Node(authors[i]);
          hit_map.put(authors[i], node);
        }
        nodes[i] = node;
      }
      for (int i = 0; i < n; ++i) {
        for (int j = i + 1; j < n; ++j) {
          nodes[i].addNeighbor(nodes[j]);
        }
      }
    }
    Node node = hit_map.get("ERDOS");
    if (node != null) {
      node.number = 0;
      PriorityQueue<NodeWithDist> queue = new PriorityQueue<NodeWithDist>(hit_map.size(),
          new NodeWithDistComparator());
      queue.add(new NodeWithDist(node, 0));
      while (!queue.isEmpty()) {
        NodeWithDist cur = queue.poll();
        if (cur.dist > cur.node.number) {
          continue;
        }
        for (Node neighbor : cur.node.neighbors) {
          if (neighbor.number == -1 || cur.dist + 1 < neighbor.number) {
            neighbor.number = cur.dist + 1;
            System.out.printf("add queue (%s, %d)\n", neighbor.name, neighbor.number);
            queue.add(new NodeWithDist(neighbor, neighbor.number));
          }
        }
      }
    }
    Node[] nodes = hit_map.values().toArray(new Node[0]);
    Arrays.sort(nodes, new NodeNameComparator());
    String[] result = new String[nodes.length];
    for (int i = 0; i < nodes.length; ++i) {
      if (nodes[i].number == -1) {
        result[i] = nodes[i].name;
      } else {
        result[i] = nodes[i].name + " " + nodes[i].number;
      }
    }
    System.out.printf("[");
    for (int i = 0; i < result.length; ++i) {
      System.out.printf("%s ", result[i]);
    }
    System.out.printf("]\n");
    return result;
  }

  public static void main(String[] args) {
    ErdosNumber obj = new ErdosNumber();
    assertArrayEquals(new String[]{"ERDOS 0" }, obj.calculateNumbers(new String[]{"ERDOS"}));
    assertArrayEquals(new String[]{"ERDOS 0", "KLEITMAN 1", "LANDER 2" },
        obj.calculateNumbers(new String[]{"KLEITMAN LANDER", "ERDOS KLEITMAN"}));
    assertArrayEquals(new String[]{"A 1", "AA 3", "B 2", "C 3", "ERDOS 0" },
        obj.calculateNumbers(new String[]{"ERDOS A", "A B", "B AA C"}));
    assertArrayEquals(new String[]{"A 2", "B 1", "C 2", "D", "E 2", "ERDOS 0", "F" },
        obj.calculateNumbers(new String[]{"ERDOS B", "A B C", "B A E", "D F"}));
    assertArrayEquals(new String[]{"ALON 2",
"CHUNG 2",
"DEAN 2",
"ERDOS 0",
"GODDARD 2",
"KLEITMAN 1",
"STURTEVANT 2",
"WAYNE 2" }, 
        obj.calculateNumbers(new String[]{"ERDOS KLEITMAN", "CHUNG GODDARD KLEITMAN WAYNE", "WAYNE GODDARD KLEITMAN", 
 "ALON KLEITMAN", "DEAN GODDARD WAYNE KLEITMAN STURTEVANT"}));
  }
}
