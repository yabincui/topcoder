// SRM 236 Div I, level one
#include <vector>
#include <string>
using namespace std;

class BusinessTasks {
public:
	string getTask(vector<string> list, int n) {
		vector<bool> valid(list.size(), true);
		size_t valid_size = list.size();
		size_t cur_pos = 0;
		while (valid_size > 1) {
			size_t move = (n - 1) % valid_size;
			for (size_t i = 0; ; ++i) {
				while (!valid[cur_pos]) {
					cur_pos = forward(cur_pos, list.size());
				}
				if (i == move) {
					valid[cur_pos] = false;
					break;
				}
				cur_pos = forward(cur_pos, list.size());
			}
			valid_size--;
		}
		while (!valid[cur_pos]) {
			cur_pos = forward(cur_pos, list.size());
		}
		return list[cur_pos];
	}
	
	size_t forward(size_t cur_pos, size_t n) {
		if (++cur_pos == n) {
			cur_pos = 0;
		}
		return cur_pos;
	}

};
