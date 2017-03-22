// SRM 223 Div 1, Level 3
#include <string>
#include <vector>
#include <unordered_set>
#include <queue>
#include <cstring>

using namespace std;

class RevolvingDoors {

	struct State {
		int r, c, doorState;
		
		State(int state = 0) {
			c = state & 0x3f;
			r = (state >> 6) & 0x3f;
			doorState = (state >> 12);
		}
		
		State(int r, int c, int doorState) : r(r), c(c), doorState(doorState) {
		
		}
		
		int getIntState() {
			return c | (r << 6) | (doorState << 12);
		}
		
	};
	
	enum {
		UP,
		DOWN,
		LEFT,
		RIGHT,
	};
	
	int dr[4] = {-1, 1, 0, 0};
	int dc[4] = {0, 0, -1, 1};
	
	bool canGo(bool vert, bool vertDoor) {
		return vert != vertDoor;
	}
	
	bool canTurn(bool vert, int dir) {
		if (vert) {
			return dir == LEFT || dir == RIGHT;
		} else {
			return dir == UP || dir == DOWN;
		}
	}
	
	bool visited[50][50][1024];

public:
	int turns(vector<string> map) {
	
		queue<int> q;
		queue<int> nq;
		
		memset(visited, 0, sizeof(visited));
		
		State start;
		int doorCount = 0;
		vector<vector<int>> doorId(map.size(), vector<int>(map[0].size(), -1));
		for (int r = 0; r < map.size(); ++r) {
			for (int c = 0; c < map[0].size(); ++c) {
				if (map[r][c] == 'S') {
					start.r = r;
					start.c = c;
				} else if (map[r][c] == 'O') {
					int id = doorCount++;
					if (map[r-1][c] == '|') {
						start.doorState |= 1 << id;
					}
					doorId[r-1][c] = doorId[r+1][c] = (id << 1) | 1;
					doorId[r][c-1] = doorId[r][c+1] = id << 1;
				}
			}
		}
		q.push(start.getIntState());
		visited[start.r][start.c][start.doorState] = true;
		int step = 0;
		while (!q.empty()) {
			//fprintf(stderr, "step = %d\n", step);
			while (!q.empty()) {
				int val = q.front(); q.pop();
				State cur(val);
				//printf("cur.r = %d, c = %d, doorState = %x\n", cur.r, cur.c, cur.doorState);
				if (map[cur.r][cur.c] == 'E') {
					return step;
				}
				for (int d = 0; d < 4; ++d) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					if (nr < 0 || nr >= map.size() || nc < 0 || nc >= map[0].size()) {
						continue;
					}
					if (map[nr][nc] == 'O' || map[nr][nc] == '#') {
						continue;
					}
					int id = doorId[nr][nc];
					bool vertDoor = id & 1;
					id >>= 1;
					bool vert = (cur.doorState >> id) & 1;
					if (id == -1 || canGo(vert, vertDoor)) {
						State next(nr, nc, cur.doorState);
						int key = next.getIntState();
						if (!visited[next.r][next.c][next.doorState]) {
							//printf("q.push(r =%d, c = %d, doorState = %x\n", nr, nc, cur.doorState);
							visited[next.r][next.c][next.doorState] = true;
							q.push(key);
						}
					} else if (canTurn(vert, d)) {
						State next(nr, nc, cur.doorState ^ (1 << id));
						int key = next.getIntState();
						if (!visited[next.r][next.c][next.doorState]) {
							visited[next.r][next.c][next.doorState] = true;
							//printf("nq.push(r =%d, c = %d, doorState = %x\n", nr, nc, cur.doorState ^ (1 << id));
							nq.push(key);
						}
					}
				}
			}
			q = nq;
			nq = queue<int>();
			step++;
		}
		return -1;
	}
};
