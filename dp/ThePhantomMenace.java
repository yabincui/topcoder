public class ThePhantomMenace {
	public int find(int[] doors, int[] droids) {
		int result = 0;
		for (int i = 0; i < doors.length; ++i) {
			int nearest = Integer.MAX_VALUE;
			for (int j = 0; j < droids.length; ++j) {
				int d = Math.abs(doors[i] - droids[j]);
				nearest = Math.min(nearest, d);
			}
			result = Math.max(result, nearest);
		}
		return result;
	}
}
