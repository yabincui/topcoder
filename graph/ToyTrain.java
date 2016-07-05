import java.util.*;

public class ToyTrain {
  final int DIR_UP = 0;
	final int DIR_RIGHT = 1;
	final int DIR_DOWN = 2;
	final int DIR_LEFT = 3;
  final int[] dr = new int[]{-1, 0, 1, 0};
	final int[] dc = new int[]{0, 1, 0, -1};

  final int UP_FLAG = 1 << (16 + DIR_UP);
  final int RIGHT_FLAG = 1 << (16 + DIR_RIGHT);
  final int DOWN_FLAG = 1 << (16 + DIR_DOWN);
  final int LEFT_FLAG = 1 << (16 + DIR_LEFT);
  final int NO_DIR_FLAG = 1 << (16 + 4); 
  final int A_FLAG = 1 << (16 + 5);
  final int B_FLAG = 1 << (16 + 6);
  final int S_FLAG = 1 << (16 + 7);
  final int FIXED_FLAG = 1 << (16 + 8);
  final int EMPTY_FLAG = 1 << (16 + 9);
  final int BLOCK_FLAG = 1 << (16 + 10);

  final int aNoDir = A_FLAG | NO_DIR_FLAG;
  final int aLeftUp = A_FLAG | LEFT_FLAG | UP_FLAG;
  final int aUpRight = A_FLAG | UP_FLAG | RIGHT_FLAG;
  final int aRightDown = A_FLAG | RIGHT_FLAG | DOWN_FLAG;
  final int aDownLeft = A_FLAG | DOWN_FLAG | LEFT_FLAG;
  final int bNoDir = B_FLAG | NO_DIR_FLAG;
  final int bLeftUp = B_FLAG | LEFT_FLAG | UP_FLAG;
  final int bUpRight = B_FLAG | UP_FLAG | RIGHT_FLAG;
  final int bRightDown = B_FLAG | RIGHT_FLAG | DOWN_FLAG;
  final int bDownLeft = B_FLAG | DOWN_FLAG | LEFT_FLAG;
  final int sNoDir = S_FLAG | NO_DIR_FLAG;
  final int sDownUp = S_FLAG | DOWN_FLAG;  // in at down, out at up.
  final int sUpDown = S_FLAG | UP_FLAG;
  final int sLeftRight = S_FLAG | LEFT_FLAG;
  final int sRightLeft = S_FLAG | RIGHT_FLAG;
  final int emptyPoint = EMPTY_FLAG;
  final int blockPoint = BLOCK_FLAG;

  int getRevFlag(int d) {
    int reverseDirection = (d + 2) % 4;
    return 1 << (16 + reverseDirection);
  }

  int getDirFlag(int d) {
    return 1 << (16 + d);
  }

  int getRevDir(int d) {
    return (d + 2) % 4;
  }


  int[][] field;
	int[][] s;
	int rows;
	int cols;

	
	public int getMinCost(String[] field) {
    rows = field.length;
    cols = field[0].length();
		this.field = new int[rows][cols];
    this.s = new int[rows][cols];
    boolean[] validDigits = new boolean[10];
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols; ++j) {
        char ch = field[i].charAt(j);
        if (ch == '.') {
          this.field[i][j] = emptyPoint;
        } else if (ch == 'A') {
          this.field[i][j] = aNoDir;
        } else if (ch == 'B') {
          this.field[i][j] = bNoDir;
        } else if (ch == 'S') {
          this.field[i][j] = sNoDir;
        } else if (ch >= '1' && ch <= '9') {
          this.field[i][j] = ch - '0';
          validDigits[ch - '0'] = true;
        }
			}
		}
		boolean[] used = new boolean[10];
    for (int i = 1; i <= 9; ++i) {
      if (!validDigits[i]) {
        used[i] = true;
      }
    }
		if (!checkABMatch(used)) {
			return -1;
		}
    for (int i = 0; i < rows; ++i) {
      for (int j = 0; j < cols; ++j) {
        System.out.printf("field[%d][%d] = %x\n", i, j, this.field[i][j]);
      }
    }
		int fixCost = 0;
		for (int i = 1; i <= 9; ++i) {
			if (used[i] && validDigits[i]) {
				fixCost += i;
        System.out.printf("fix %d\n", i);
			}
		}
		for (int i = 0; i <= 45 - fixCost; ++i) {
			if (permute(used, i)) {
        System.out.printf("permute %d\n", i);
    for (int r = 0; r < rows; ++r) {
      for (int c = 0; c < cols; ++c) {
        System.out.printf("field[%d][%d] = %x\n", r, c, s[r][c]);
      }
    }

				return i + fixCost;
			}
		}
		return -1;
	}

  class Node {
    int mark;
    int r;
    int c;
    Node[] neighbors;
    int neighborCount;
    int fixedFlag;

    Node(int mark, int r, int c) {
      this.mark = mark;
      this.r = r;
      this.c = c;
      neighbors = new Node[4];
      neighborCount = 0;
      fixedFlag = 0;
    }

    void addNeighbor(int dir, Node node) {
      neighbors[dir] = node;
      neighborCount++;
    }

    void removeNeighbor(int dir) {
      neighbors[dir] = null;
      neighborCount--;
    }
 
    boolean isValid() {
      // 1. check each neighbor to see if it is still valid.
      // 2. check if neighbors >= 2.
      // 3. check if fixed neighbors have reverse directions.
      fixedFlag = 0;
      for (int k = 0; k < 4; ++k) {
        if (neighbors[k] == null || (neighbors[k].mark & NO_DIR_FLAG) != 0) {
          continue;
        }
        if ((neighbors[k].mark & getRevFlag(k)) == 0) {
          removeNeighbor(k);
        } else {
          fixedFlag |= 1 << k;
        }
      }
      if (neighborCount < 2) {
        return false;
      }
      if ((fixedFlag & 5) == 5 || (fixedFlag & 0xa) == 0xa) {
        return false;
      }
      return true;
    }
  }

  boolean checkABMatch(boolean[] used) {
    // 1. the number of A matches the number of B, and > 0.
    int aCount = 0;
    int bCount = 0;
    for (int r = 0; r < rows; ++r) {
      for (int c = 0; c < cols; ++c) {
        if (field[r][c] == aNoDir) {
          aCount++;
        } else if (field[r][c] == bNoDir) {
          bCount++;
        }
      }
    }
    if (aCount == 0 || aCount != bCount) {
      return false;
    }
    // If some point is A, we must be able to find two Bs in near directions.
    // If some point is B, we must be able to find two As in near directions.
    // If some point is A, and only has two Bs in near directions, fix its direction.
    // If some point is B, and only has two As in near directions, fix its direction.
    Node[][] nodes = new Node[rows][cols];
    for (int i = 0; i < rows; ++i) {
      for (int j = 0; j < cols; ++j) {
        if (field[i][j] == aNoDir || field[i][j] == bNoDir) {
          nodes[i][j] = new Node(field[i][j], i, j);
        }
      }
    }
    for (int i = 0; i < rows; ++i) {
      for (int j = 0; j < cols; ++j) {
        if (field[i][j] == aNoDir || field[i][j] == bNoDir) {
          int need = field[i][j] ^ (A_FLAG | B_FLAG);
          for (int k = 0; k < 4; ++k) {
            int nr = i;
            int nc = j;
            while (true) {
              nr += dr[k];
              nc += dc[k];
              if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) {
                break;
              }
              if (field[nr][nc] == need) {
                nodes[i][j].addNeighbor(k, nodes[nr][nc]);
                break;
              }
              if (field[nr][nc] == field[i][j]) {
                break;
              }
            }
          }
        }
      }
    }
    boolean updated = true;
    while (updated) {
      updated = false;
      // check valid.
      for (int i = 0; i < rows; ++i) {
        for (int j = 0; j < cols; ++j) {
          Node node = nodes[i][j];
          if (node == null) {
            continue;
          }
          if (!node.isValid()) {
            return false;
          }
          if ((node.mark & NO_DIR_FLAG) != 0) {
            int fixedDirMask = 0;
            if (node.neighborCount == 2) {
              for (int k = 0; k < 4; ++k) {
                if (node.neighbors[k] != null) {
                  fixedDirMask |= 1 << k;
                }
              }
            } else if (node.fixedFlag != 0) {
              for (int k = 0; k < 4; ++k) {
                if ((node.fixedFlag & (1 << k)) != 0) {
                  int nk = (k + 1) % 4;
                  if ((node.fixedFlag & (1 << nk)) != 0) {
                    fixedDirMask = (1 << k) | (1 << nk);
                    break;
                  }
                  int pk = (k + 3) % 4;
                  if (node.neighbors[nk] == null && node.neighbors[pk] == null) {
                    return false;
                  }
                  if (node.neighbors[nk] == null) {
                    fixedDirMask = (1 << k) | (1 << pk);
                    break;
                  }
                  if (node.neighbors[pk] == null) {
                    fixedDirMask = (1 << k) | (1 << nk);
                    break;
                  }
                }
              }
            }
            if (fixedDirMask != 0) {
              updated = true;
              int mark = (node.mark & (A_FLAG | B_FLAG) & ~NO_DIR_FLAG) | FIXED_FLAG;
              for (int k = 0; k < 4; ++k) {
                if ((fixedDirMask & (1 << k)) != 0) {
                  mark |= getDirFlag(k);
                  int nr = node.r;
                  int nc = node.c;
                  while (true) {
                    nr += dr[k];
                    nc += dc[k];
                    if (nr == node.neighbors[k].r && nc == node.neighbors[k].c) {
                      break;
                    }
                    if (field[nr][nc] >= 1 && field[nr][nc] <= 9) {
                      used[field[nr][nc]] = true;
                    }
                  }
                }
              }
              field[node.r][node.c] = node.mark = mark;
            }
          }
        }
      }
    }
    return true;
  }

	boolean permute(boolean[] used, int leftCost) {
		if (leftCost == 0) {
      System.out.printf("tryPermute\n");
			boolean ret = tryPermute(used);
      System.out.printf("tryPermute ret = %b\n", ret);
      return ret;
		}
		for (int i = 1; i <= 9; ++i) {
			if (!used[i] && leftCost >= i) {
				used[i] = true;
				if (permute(used, leftCost - i)) {
					return true;
				}
				used[i] = false;
			}
		}
		return false;
	}
	
	boolean tryPermute(boolean[] used) {
		for (int r = 0; r < rows; ++r) {
			for (int c = 0; c < cols; ++c) {
				if (field[r][c] >= 1 && field[r][c] <= 9) {
          if (used[field[r][c]]) {
            s[r][c] = emptyPoint;
          } else {
            s[r][c] = blockPoint;
          }
        } else {
					s[r][c] = field[r][c];
				}
			}
		}
		if (findSolution(0, 0, -1, false)) {
			return true;
		}
		return false;
	}
	
	boolean findSolution(int curR, int curC, int direction, boolean out) {
		//System.out.printf("findSolution(%d, %d, %d, %b)\n", curR, curC, direction, out);
		if (direction == -1) {
      int selR = -1;
      int selC = -1;
			for (int r = 0; r < rows && selR == -1; ++r) {
				for (int c = 0; c < cols; ++c) {
          if ((s[r][c] & NO_DIR_FLAG) != 0 || (s[r][c] & FIXED_FLAG) != 0) {
            selR = r;
            selC = c;
            break;
          }
        }
      }
      if (selR == -1) {
        return true;
      }
      int ch = s[selR][selC];
      if (ch == aNoDir || ch == bNoDir) {
        s[selR][selC] = (ch & ~NO_DIR_FLAG) | RIGHT_FLAG | DOWN_FLAG;
        if (findSolution(selR, selC + 1, DIR_RIGHT, (ch & A_FLAG) != 0)) {
          return true;
        }
      } else if ((ch & FIXED_FLAG) != 0) {
        s[selR][selC] &= ~FIXED_FLAG;
        for (int k = 0; k < 4; ++k) {
          if ((s[selR][selC] & getDirFlag(k)) != 0) {
            if (findSolution(selR + dr[k], selC + dc[k], k, (ch & A_FLAG) != 0)) {
              return true;
            }
            break;
          }
        }
      }
			return false; 
		}
		if (curR < 0 || curR >= rows || curC < 0 || curC >= cols) {
			return false;
		}
    int ch = s[curR][curC];
    if ((ch & A_FLAG) != 0 && out) {
      return false;
    }
    if ((ch & B_FLAG) != 0 && !out) {
      return false;
    }

		if (ch == aNoDir || ch == bNoDir) {
      boolean nout = (ch == aNoDir);
      int revDir = getRevDir(direction);
      for (int k = 0; k < 4; ++k) {
        if (k == direction || k == revDir) continue;
        s[curR][curC] = (ch & (A_FLAG | B_FLAG)) | getDirFlag(revDir) | getDirFlag(k);
        if (findSolution(curR + dr[k], curC + dc[k], k, nout)) {
          return true;
        }
      }
    } else if (ch == sNoDir || ch == emptyPoint) {
      s[curR][curC] = S_FLAG | getDirFlag(direction);
      if (findSolution(curR + dr[direction], curC + dc[direction], direction, out)) {
        return true;
      }
    } else if ((ch & (A_FLAG | B_FLAG)) != 0) {
      s[curR][curC] &= ~FIXED_FLAG;
      int revDir = getRevDir(direction);
      if ((s[curR][curC] & getDirFlag(revDir)) != 0) {
        for (int k = 0; k < 4; ++k) {
          if (k != revDir && (ch & getDirFlag(k)) != 0) {
            if ((ch & FIXED_FLAG) != 0) {
              if (findSolution(curR + dr[k], curC + dc[k], k, (ch & A_FLAG) != 0)) {
                return true;
              }
            } else {
              if (findSolution(-1, -1, -1, false)) {
                return true;
              }
            }
          }
        }
      }
		}
		
		s[curR][curC] = ch;
		return false;
	}

  public static void main(String[] args) {
    ToyTrain obj = new ToyTrain();
    String[] field = 
{"ABABABABABABABABABABABABABABABABABABABABABABABABAB", "BABABABABABABABABABABABABABABABABABABABABABABABABA", "ABABABABABABABABABABABABABABABABABABABABABABABABAB", "BABABABABABABABABABABABABABABABABABABABABABABABABA", "ABABABABABABABABABABABABABABABABABABABABABABABABAB", "BABABABABABABABABABABABABABABABABABABABABABABABABA", "ABABABABABABABABABABABABABABABABABABABABABABABABAB", "BABABABABABABABABABABABABABABABABABABABABABABABABA", "ABABABABABABABABABABABABABABABABABABABABABABABABAB", "BABABABABABABABABABABABABABABABABABABABABABABABABA", "ABABABABABABABABABABABABABABABABABABABABABABABABAB", "BABABABABABABABABABABABABABABABABABABABABABABABABA", "ABABABABABABABABABABABABABABABABABABABABABABABABAB", "BABABABABABABABABABABABABABABABABABABABABABABABABA", "ABABABABABABABABABABABABABABABABABABABABABABABABAB", "BABABABABABABABABABABABABABABABABABABABABABABABABA", "ABABABABABABABABABABABABABABABABABABABABABABABABAB", "BABABABABABABABABABABABABABABABABABABABABABABABABA", "ABABABABABABABABABABABABABABABABABABABABABABABABAB", "BABABABABABABABABABABABABABABABABABABABABABABABABA", "ABABABABABABABABABABABABABABABABABABABABABABABABAB", "BABABABABABABABABABABABABABABABABABABABABABABABABA", "ABABABABABABABABABABABABABABABABABABABABABABABABAB", "BABABABABABABABABABABABABABABABABABABABABABABABABA", "ABABABABABABABABABABABABABABABABABABABABABABABABAB", "BABABABABABABABABABABABABABABABABABABABABABABABABA", "ABABABABABABABABABABABABABABABABABABABABABABABABAB", "BABABABABABABABABABABABABABABABABABABABABABABABABA", "ABABABABABABABABABABABABABABABABABABABABABABABABAB", "BABABABABABABABABABABABABABABABABABABABABABABABABA", "ABABABABABABABABABABABABABABABABABABABABABABABABAB", "BABABABABABABABABABABABABABABABABABABABABABABABABA", "ABABABABABABABABABABABABABABABABABABABABABABABABAB", "BABABABABABABABABABABABABABABABABABABABABABABABABA", "ABABABABABABABABABABABABABABABABABABABABABABABABAB", "BABABABABABABABABABABABABABABABABABABABABABABABABA", "ABABABABABABABABABABABABABABABABABABABABABABABABAB", "BABABABABABABABABABABABABABABABABABABABABABABABABA", "ABABABABABABABABABABABABABABABABABABABABABABABABAB", "BABABABABABABABABABABABABABABABABABABABABABABABABA", "ABABABABABABABABABABABABABABABABABABABABABABABABAB", "BABABABABABABABABABABABABABABABABABABABABABABABABA", "ABABABABABABABABABABABABABABABABABABABABABABABABAB", "BABABABABABABABABABABABABABABABABABABABABABABABABA", "ABABABABABABABABABABABABABABABABABABABABABABABABAB", "BABABABABABABABABABABABABABABABABABABABABABABABABA",
  
  "ABABABABABABABABABABABABABABABABABABABABABABABABAB",
  "BABABABABABABABABABABABABABABABABABABABABABAB..ABA",
  "ABABABABABABABABABABABABABABABABABABABABABABA..BAB", 
  "BABABABABABABABABABABABABABABABABABABABABABABABABA"};

/*
  field = new String[]
  {"..AB",
 "B..A",
 "....",
 "A.B."};
field = new String[]
{"A1B8A2B",
 "16A.B22",
 "BAB.9.A",
 "ABA.7.B",
 "B12345A"};
*/

    int ret = obj.getMinCost(field);
    System.out.printf("ret = %d\n", ret);
  }
}

