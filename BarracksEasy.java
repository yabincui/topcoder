import java.util.*;

public class BarracksEasy {
	class State {
		int soldiers;
		int enemies;
		int building;
		State(int soldiers, int enemies, int building) {
			this.soldiers = soldiers;
			this.enemies = enemies;
			this.building = building;
		}
	}
	
	public int attack(int myUnits, int barHp, int unitsPerRound) {
		boolean[][][] validState = new boolean[myUnits + 1][barHp + 1][myUnits * 2 + 1];
		validState[myUnits][barHp][0] = true;
		Queue<State> queue = new ArrayDeque<State>();
		queue.add(new State(myUnits, 0, barHp));
		boolean finished = false;
		int rounds = 0;
		while (true) {
			rounds++;
			Queue<State> nextQueue = new ArrayDeque<State>();
			while (!queue.isEmpty()) {
				State state = queue.poll();
				for (int attack = 0; attack <= Math.min(state.soldiers, state.building); ++attack) {
					int leftBuilding = state.building - attack;
					int leftEnemies = (state.soldiers - attack >= state.enemies) ? 0 : (state.enemies - state.soldiers + attack);
					int leftSoldiers = (state.soldiers >= leftEnemies) ? (state.soldiers - leftEnemies) : 0;
					if (leftBuilding != 0) {
						leftEnemies += unitsPerRound;
					}
					if (leftEnemies > myUnits * 2) {
						leftEnemies = myUnits * 2;
					}
					if (!validState[leftSoldiers][leftBuilding][leftEnemies]) {
						nextQueue.add(new State(leftSoldiers, leftEnemies, leftBuilding));
						validState[leftSoldiers][leftBuilding][leftEnemies] = true;
						if (leftBuilding == 0 && leftEnemies == 0) {
							finished = true;
							break;
						}
					}
				}
				if (finished) {
					break;
				}
			}
			if (finished) {
				break;
			}
			if (nextQueue.isEmpty()) {
				break;
			}
			queue = nextQueue;
		}
		return finished ? rounds : -1;
	}
}