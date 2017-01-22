// SRM 146 Div 2 Easy
#include <vector>

using namespace std;

class YahtzeeScore {
	public:
		int maxPoints(vector<int> toss) {
			int max_value = 0;
			for (int i = 0; i < toss.size(); ++i) {
				int cur_sum = 0;
				for (int j = 0; j < toss.size(); ++j) {
					if (toss[i] == toss[j]) {
						cur_sum += toss[i];
					}
				}
				if (cur_sum > max_value) {
					max_value = cur_sum;
				}
			}
			return max_value;
		}
};
