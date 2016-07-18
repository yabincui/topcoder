#include <stdio.h>
#include <string.h>

#include <chrono>
#include <vector>

using namespace std;

typedef long long ll;

#define N 60

class TriangleFree {
private:
	int adj[N][N];
	int adjNum[N];
	bool mat[N][N];
	int n;

public:
	long long count(int n, vector<int> x, vector<int> y) {
		this->n = n;
		memset(adjNum, 0, sizeof(adjNum));
		memset(mat, 0, sizeof(mat));
		for (int i = 0; i < x.size(); ++i) {
			adj[x[i]][adjNum[x[i]]++] = y[i];
			adj[y[i]][adjNum[y[i]]++] = x[i];
			mat[x[i]][y[i]] = mat[y[i]][x[i]] = true;
		}
		ll res = dfs(0, (1ll << n) - 1);
		return res;
	}
	
private:
	ll dfs(ll state, ll left) {
		//printf("dfs(%llx, %llx\n", state, left);
		if (left == 0) {
			//printf("state = %llx, left = 0, res = 1\n", state);
			return 1;
		}
		// 1. how many connected partitions.
		vector<ll> cs;
		for (int i = 0; i < n; ++i) {
			if (left & (1ll << i)) {
				ll connect = 0;
				getConnect(left, i, connect);
				//printf("connect = %llx\n", connect);
				cs.push_back(connect);
				left &= ~connect;
			}
		}
		if (cs.size() > 1) {
			ll res = 1;
			for (int i = 0; i < cs.size(); ++i) {
				ll tmp = dfs(state, cs[i]);
				res *= tmp;
			}
			//printf("state = %llx, left = %llx, res = %lld\n", state, left, res);
			return res;
		}
		left = cs[0];
		// 2. if there are no conflict, permute all.
		bool noConflict = true;
		for (int i = 0; i < n && noConflict; ++i) {
			if ((state & (1ll << i))) {
				for (int j = 0; j < adjNum[i] && noConflict; ++j) {
					int a = adj[i][j];
					if ((state & (1ll << a)) || (left & (1ll << a))) {
						for (int k = 0; k < adjNum[i]; ++k) {
							int b = adj[i][k];
							if ((left & (1ll << b)) && mat[a][b]) {
								noConflict = false;
								break;
							}
						}
					}
				}
			}
		}
		for (int i = 0; i < n && noConflict; ++i) {
			if ((left & (1ll << i))) {
				for (int j = 0; j < adjNum[i] && noConflict; ++j) {
					int a = adj[i][j];
					if ((left & (1ll << a))) {
						for (int k = 0; k < adjNum[i]; ++k) {
							int b = adj[i][k];
							if ((left & (1ll << b)) && mat[a][b]) {
								noConflict = false;
								break;
							}
						}
					}
				}
			}
		}
		if (noConflict) {
			ll res = 1;
      while (left != 0) {
        res <<= 1;
        left &= left - 1;
      }
			//printf("state = %llx, left = %llx, res = %lld\n", state, left, res);
			return res;
		}
		// 3. select a node with most edges, use it, or not use it.
		int edgeNum = -1;
		int select = -1;
		for (int i = 0; i < n; ++i) {
			if (left & (1ll << i)) {
				int edge = 0;
				for (int j = 0; j < adjNum[i]; ++j) {
					int a = adj[i][j];
					if ((left & (1ll << a))) {
						edge++;
					}
				}
				if (edge > edgeNum) {
					select = i;
					edgeNum = edge;
				}
			}
		}
		// not use select.
		ll res = dfs(state, left & ~(1ll << select));
		// use select.
		for (int i = 0; i < adjNum[select]; ++i) {
			int a = adj[select][i];
			if (state & (1ll << a)) {
				for (int j = 0; j < adjNum[select]; ++j) {
					int b = adj[select][j];
					if ((left & (1ll << b)) && mat[a][b]) {
						left &= ~(1ll << b);
					}
				}
			}
		}
		res += dfs(state | (1ll << select), left & ~(1ll << select));
		//printf("state = %llx, left = %llx, res = %lld\n", state, left, res);
		return res;
	}
	
	void getConnect(ll left, int start, ll& connect) {
		connect |= 1ll << start;
		for (int i = 0; i < adjNum[start]; ++i) {
			ll bit = 1ll << adj[start][i];
			if ((left & bit) && !(connect & bit)) {
				connect |= bit;
				getConnect(left, adj[start][i], connect);
			}
		}
	}
};


int main() {
  vector<int> x = {0, 2, 2, 1, 3, 5, 5, 4, 6, 8, 8, 7, 9, 11, 11, 10, 12, 14, 14, 13, 15, 17, 17, 16, 18, 20, 20, 19, 21, 23, 23, 22, 24, 26, 26, 25, 27, 29, 29, 28, 30, 32, 32, 31, 33, 35, 35, 34, 36, 38, 38, 37, 39, 41, 41, 40, 42, 44, 44, 41};
  vector<int> y = {1, 1, 0, 3, 4, 4, 3, 6, 7, 7, 6, 9, 10, 10, 9, 12, 13, 13, 12, 15, 16, 16, 15, 18, 19, 19, 18, 21, 22, 22, 21, 24, 25, 25, 24, 27, 28, 28, 27, 30, 31, 31, 30, 33, 34, 34, 33, 36, 37, 37, 36, 39, 40, 40, 39, 42, 43, 43, 42, 43};
  TriangleFree obj;
  auto t1 = chrono::steady_clock::now();
  ll res = obj.count(60, x, y);
  auto t2 = chrono::steady_clock::now();
  printf("res = %lld\n", res);
  printf("time used is : %lld\n", (t2 - t1).count());
}
