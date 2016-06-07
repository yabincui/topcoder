public class BuildBridge {
	public int howManyCards(int D, int L) {
		double sum = L / 2.0;
		double t = L / 2.0;
		int n = 1;
		double expected = D;
		while (sum < expected) {
			n += 1;
			sum += t/n;
		}
		return n;
	}
}
