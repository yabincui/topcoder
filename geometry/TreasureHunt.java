public class TreasureHunt {
	int bestDist;
	int bestDestR, bestDestC;
	int rows, cols;
	String[] island;
	int estimateR, estimateC;
	int[] dr = new int[]{-1, 1, 0, 0};
	int[] dc = new int[]{0, 0, 1, -1};
	
	class Inst {
		int dir;
		int step;
		Inst(int dir, int step) {
			this.dir = dir;
			this.step = step;
		}
	}
	Inst[] insts;
	
	public int[] findTreasure(String[] island, String[] instructions) {
		bestDist = Integer.MAX_VALUE;
		rows = island.length;
		cols = island[0].length();
		this.island = island;
		estimateR = -1;
		for (int i = 0; i < rows && estimateR == -1; ++i) {
			for (int j = 0; j < cols; ++j) {
				if (island[i].charAt(j) == 'X') {
					estimateR = i;
					estimateC = j;
					break;
				}
			}
		}
		insts = new Inst[instructions.length];
		for (int i = 0; i < instructions.length; ++i) {
			char c = instructions[i].charAt(0);
			int d = -1;
			if (c == 'N') {
				d = 0;
			} else if (c == 'S') {
				d = 1;
			} else if (c == 'E') {
				d = 2;
			} else if (c == 'W') {
				d = 3;
			}
			int step = (instructions[i].charAt(2) - '0');
			insts[i] = new Inst(d, step);
		}
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols; ++j) {
				if (island[i].charAt(j) != '.') {
					if (nearWater(i, j)) {
						followInstructions(i, j);
					}
				}
			}
		}
		if (bestDist == Integer.MAX_VALUE) {
			return new int[]{};
		}
		return new int[]{bestDestC, bestDestR};
	}
	
	boolean nearWater(int r, int c) {
		for (int i = 0; i < 4; ++i) {
			int tr = dr[i] + r;
			int tc = dc[i] + c;
			if (tr < 0 || tr >= rows || tc < 0 || tc >= cols || island[tr].charAt(tc) == '.') {
				return true;
			}
		}
		return false;
	}
	
	void followInstructions(int curR, int curC) {
		for (Inst inst : insts) {
			int nr = curR;
			int nc = curC;
			for (int i = 0; i < inst.step; ++i) {
				nr += dr[inst.dir];
				nc += dc[inst.dir];
				if (nr < 0 || nr >= rows || nc < 0 || nc >= cols || island[nr].charAt(nc) == '.') {
					return;
				}
			}
			curR = nr;
			curC = nc;
		}
		int dist = (curR - estimateR) * (curR - estimateR) + (curC - estimateC) * (curC - estimateC);
		if (dist < bestDist) {
			bestDist = dist;
			bestDestR = curR;
			bestDestC = curC;
		} else if (dist == bestDist) {
			if (curR < bestDestR || (curR == bestDestR && curC < bestDestC)) {
				bestDist = dist;
				bestDestR = curR;
				bestDestC = curC;
			}
		}
	}
}