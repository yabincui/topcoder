// SRM 209 Div 1, Level One
#include <string>
#include <vector>
#include <unordered_map>
#include <algorithm>

using namespace std;

class MedalTable {
	struct Node {
		string name;
		int gold;
		int silver;
		int bronze;
		
		Node() : gold(0), silver(0), bronze(0) {}
	};
	
	static bool compareNode(const Node& n1, const Node& n2) {
		if (n1.gold != n2.gold) {
			return n1.gold > n2.gold;
		}
		if (n1.silver != n2.silver) {
			return n1.silver > n2.silver;
		}
		if (n1.bronze != n2.bronze) {
			return n1.bronze > n2.bronze;
		}
		return n1.name < n2.name;
	}

public:
	vector<string> generate(vector<string> results) {
		unordered_map<string, Node> map;
		for (auto& res : results) {
			char name[3][4];
			sscanf(res.c_str(), "%s %s %s", name[0], name[1], name[2]);
			for (int i = 0; i < 3; ++i) {
				auto it = map.find(name[i]);
				if (it == map.end()) {
					Node node;
					node.name = name[i];
					auto pair = map.insert(make_pair(node.name, node));
					it = pair.first;
				}
				if (i == 0) it->second.gold++;
				else if (i == 1) it->second.silver++;
				else if (i == 2) it->second.bronze++;
			}
		}
		vector<Node> nodes;
		for (auto pair : map) {
			nodes.push_back(pair.second);
		}
		sort(nodes.begin(), nodes.end(), MedalTable::compareNode);
		vector<string> rank;
		for (auto& node : nodes) {
			char buf[40];
			snprintf(buf, sizeof(buf), "%s %d %d %d", node.name.c_str(), node.gold, node.silver, node.bronze);
			rank.push_back(buf);
		}
		return rank;
	}
};
