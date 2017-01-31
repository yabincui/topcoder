// SRM 158 Div 1 Hard
#include <string.h>
#include <stdio.h>
#include <queue>
#include <string>
#include <vector>
using namespace std;

class Jumper {
public:
	struct Pos {
		int r;
		int c;
		Pos(int r, int c) : r(r), c(c) {}
	};
	
	int dr[5] = {0, -1, 1, 0, 0};
	int dc[5] = {0, 0, 0, -1, 1};

	int minTime(vector<string> patterns, vector<int> speeds, string row) {
		int rows = row.size();
		int T = 4000;
		bool visited[rows + 1][20][4000];
		memset(visited, 0, sizeof(visited));
		
		int shift[rows + 1];
		memset(shift, 0, sizeof(shift));
		queue<Pos> q;
		q.push(Pos(0, 0));
		visited[0][0][0] = true;
		int time = 0;
		int min_jump = -1;
		while (!q.empty() && min_jump == -1) {
			int size = q.size();
			{
				printf("map for time %d\n", time);
				char v[rows+1][20];
				for (int i = 0; i < 20; ++i) {
					v[0][i] = '#';
				}
				for (int i = 1; i <= rows; ++i) {
					for (int j = 0; j < 20; ++j) {
						int to_j = speeds[row[i-1]-'0'] * time + j;
						while (to_j < 0) to_j += 20;
						to_j %= 20;
						v[i][to_j] = patterns[row[i-1]-'0'][j % 5];
					}
				}
				printf("    ");
				for (int j = 0; j < 20; ++j) {
					printf("%02d ", j);
				}
				printf("\n");
				for (int i = rows; i >= 0; --i) {
					printf("%02d: ", i);
					for (int j = 0; j < 20; ++j) {
						if (visited[i][j][time]) { v[i][j] = 'P'; }
						printf("  %c", v[i][j]);
					}
					printf("\n");
				}
			}
			while (size-- > 0 && min_jump == -1) {
				Pos cur = q.front(); q.pop();
				if (time == 16 || time == 17 || time > 17) {
					printf("time %d, cur r %d, c %d\n", time, cur.r, cur.c);
				}
				for (int i = 0; i < 5; ++i) {
					int nr = cur.r + dr[i];
					int nc = cur.c + dc[i];
					if (nr == rows + 1) {
						min_jump = time + 1;
						break;
					}
					if (nr < 0 || nc < 0 || nc >= 20) {
						continue;
					}
					bool show = (time == 16 && nr == 1 && nc == 16);
					int pattern_id = -1;
					if (nr >= 1) {
						pattern_id = row[nr - 1] - '0';
					}
					int speed = (pattern_id == -1 ? 0 : speeds[pattern_id]);
					int orig_col = getOrigCol(nc, speed, time);
					if (show) printf("pattern_id = %d, speed = %d, orig_col = %d\n", pattern_id, speed, orig_col);
					if (nr >= 1) {
						string& pattern = patterns[pattern_id];
						if (pattern[orig_col % 5] == '.') {
							continue;
						}
					}
					// move with pad.
					if (nr >= 1) {
						nc += speed;
						if (nc < 0 || nc >= 20) {
							continue;
						}
					}
					int t = (time + 1) % T;
					if (visited[nr][nc][t]) {
						continue;
					}
					visited[nr][nc][t] = true;
					q.push(Pos(nr, nc));
				}
			}
			time++;
		}		
		return min_jump;
	}
	
	int getOrigCol(int c, int speed, int time) {
		int shift = time * speed;
		c -= shift;
		while (c < 0) c += 20;
		c %= 20;
		return c;
	}
};

int main() {
	vector<string> patterns(1, "...#.");
	patterns.push_back("#..##");
	vector<int> speeds(1, -3);
	speeds.push_back(7);
	string row = "1100001011000011";
	Jumper solution;
	int res = solution.minTime(patterns, speeds, row);
	printf("res = %d\n", res);
	return 0;
}
