import java.util.*;

public class RailroadSwitchOperator {
	static final int LEFT = 0;
	static final int RIGHT = 1;
	
	class Node {
		int direction;
		int actionTime; // The node is changed to current direction at actionTime.
		int keepTime; // The node is requested to keep current direction <= keepTime.
		
		Node() {
			direction = LEFT;
			actionTime = 0;
			keepTime = 0;
		}
		
		Node(int direction, int actionTime, int keepTime) {
			this.direction = direction;
			this.actionTime = actionTime;
			this.keepTime = keepTime;
		}
		
		boolean needNewAction(int newDirection, int curActionTime) {
			if (direction == newDirection) {
				return false;
			}
			if (actionTime < curActionTime && keepTime < curActionTime) {
				// Use curActionTime to change it.
				return false;
			}
			return true;
		}
	}
	
	class TimeEntry {
		int t;
		int direction;
		String position;
		
		TimeEntry(int t, int direction, String position) {
			this.t = t;
			this.direction = direction;
			this.position = position;
		}
	}
	
	class TimeEntryComparator implements Comparator<TimeEntry> {
		public int compare(TimeEntry t1, TimeEntry t2) {
			if (t1.t != t2.t) {
				return t1.t - t2.t;
			}
			return t1.position.compareTo(t2.position);
		}
	}
	
	public int minEnergy(int N, int[] x, int[] t) {
		HashMap<String, Node> nodeMap = new HashMap<String, Node>();
		ArrayList<TimeEntry> timeList = new ArrayList<TimeEntry>();
		
		int height = 0;
		for (int size = 1; size < N; size *= 2) {
			height++;
		}
		for (int i = 0; i < x.length; ++i) {
			String key = "k";
			for (int h = 0; h < height; ++h) {
				int bit = height - 1 - h;
				boolean set = ((x[i] - 1) & (1 << bit)) != 0;
				int time = t[i] + h;
				int direction = (set ? RIGHT : LEFT);
				timeList.add(new TimeEntry(time, direction, key));
				key += (set ? "1" : "0");
			}
		}
		Collections.sort(timeList, new TimeEntryComparator());
		int curActionTime = 0;
		int actionCount = 0;
		for (int i = 0; i < timeList.size(); ++i) {
			TimeEntry entry = timeList.get(i);
			Node node = null;
			if (!nodeMap.containsKey(entry.position)) {
				node = new Node();
			} else {
				node = nodeMap.get(entry.position);
			}
			int nodeActionTime = node.actionTime;
			if (node.needNewAction(entry.direction, curActionTime)) {
				curActionTime = entry.t;
				nodeActionTime = entry.t;
				actionCount++;
			}
			nodeMap.put(entry.position, new Node(entry.direction, nodeActionTime, entry.t));
		}
		return actionCount;
	}
}
