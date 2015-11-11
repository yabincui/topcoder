#include <limits.h>

#include <algorithm>
#include <vector>

using namespace std;

class JimmyLightning {
public:
	struct Node {
		int value;
		int time;
		int room;
		Node(int value, int time, int room) : value(value), time(time), room(room) {
		}
	};
	
	class NodeComparator {
	public:
		bool operator()(const Node& n1, const Node& n2) {
			return n1.room < n2.room;
		}
	};
	
	int robTheBank(vector<int> doors, vector<string> diamonds) {
		// The max time can stay in each room.
		vector<int> maxStay(doors.size());
		int min_close_time = INT_MAX;
		for (size_t i = 0; i < doors.size(); ++i) {
			min_close_time = min(min_close_time, doors[i]);
			maxStay[i] = min_close_time;
		}
		vector<Node> nodes;
		for (const auto& s : diamonds) {
			size_t pos1 = s.find(' ');
			int value = atoi(s.substr(0, pos1).c_str());
			pos1 += 1;
			size_t pos2 = s.find(' ', pos1);
			int time = atoi(s.substr(pos1, pos2).c_str());
			pos2 += 1;
			int room = atoi(s.substr(pos2).c_str());
			room--;
			nodes.push_back(Node(value, time, room));
		}
		sort(nodes.begin(), nodes.end(), NodeComparator());
		vector<int> dp(maxStay[0]);
		for (int i = nodes.size() - 1; i >= 0; --i) {
			int limit_time = maxStay[nodes[i].room] - 1;
			int value = nodes[i].value;
			int time = nodes[i].time;
			for (int i = time; i <= limit_time; ++i) {
				int new_value = value + dp[i-time];
				dp[i] = max(dp[i], new_value);
			}
		}
		int result = 0;
		for (size_t i = 0; i < dp.size(); ++i) {
			result = max(result, dp[i]);
		}
		return result;
	}
};
