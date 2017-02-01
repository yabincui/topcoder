// SRM 169 Div 2 Hard
#include <vector>
using namespace std;

class FairWorkload {
public:
	int getMostWork(vector<int> folders, int workers) {
		int sum = 0;
		int max_value = 0;
		for (auto& f : folders) {
			sum += f;
			max_value = max(max_value, f);
		}
		int low = max_value;
		int high = sum;
		while (low < high) {
			int mid = (low + high) / 2;
			if (fulfill(folders, mid, workers)) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}
	
	bool fulfill(vector<int>& folders, int mid, int workers) {
		int count = 1;
		int cur = 0;
		for (auto& f : folders) {
			if (cur + f <= mid) {
				cur += f;
			} else {
				count++;
				cur = f;
			}
		}
		return count <= workers;
	}
};
