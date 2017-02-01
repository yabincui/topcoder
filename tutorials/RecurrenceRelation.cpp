// SRM 170 Div 1 Easy
#include <deque>
#include <vector>
using namespace std;

class RecurrenceRelation {
public:
	int moduloTen(vector<int> coefficients, vector<int> initial, int N) {
		deque<int> q;
		int n = coefficients.size();
		for (int i = 0; i < n; ++i) {
			q.push_back(initial[i] % 10);
		}
		int res;
		if (N < n) {
			res = q[N];
		} else {
			int t = n;
			while (t <= N) {
				int sum = 0;
				for (int i = 0; i < n; ++i) {
					sum += (coefficients[i] * q[i]) % 10;
				}
				q.push_back(sum % 10);
				q.pop_front();
				t++;
			}
			res = q.back();
		}
		if (res < 0)  res += 10;
		return res;
	}
};
