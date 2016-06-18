import java.util.*;

public class AddElectricalWires {

  public int maxNewWires(String[] wires, int[] gridConnections) {
	int n = wires.length;
	int m = gridConnections.length;
	int[] component = new int[n];
	for (int i = 0; i < n; ++i) {
		component[i] = -1;
	}
	for (int i = 0; i < m; ++i) {
		component[gridConnections[i]] = i;
	}
	Queue<Integer> queue = new ArrayDeque<Integer>();
	for (int i : gridConnections) {
		queue.add(i);
	}
	while (!queue.isEmpty()) {
		int cur = queue.poll();
		for (int i = 0; i < n; ++i) {
			if (component[i] == -1 && wires[i].charAt(cur) == '1') {
				component[i] = component[cur];
				queue.add(i);
			}
		}
	}
	int[] component_count = new int[m];
	int free_count = 0;
	for (int i = 0; i < n; ++i) {
		if (component[i] == -1) {
			free_count++;
		} else {
			component_count[component[i]]++;
		}
	}
	int total_wire_count = 0;
	int max_component_count = 0;
	for (int i = 0; i < m; ++i) {
		total_wire_count += component_count[i] * (component_count[i] - 1) / 2;
		max_component_count = Math.max(max_component_count, component_count[i]);
	}
	total_wire_count += (max_component_count * 2 + free_count - 1) * free_count / 2;
	int cur_wire_count = 0;
	for (int i = 0; i < n; ++i) {
		for (int j = i + 1; j < n; ++j) {
			if (wires[i].charAt(j) == '1') {
				cur_wire_count++;
			}
		}
	}
	return total_wire_count - cur_wire_count;
  }
}
