public class MovingAvg {
	public double difference(int k, double[] data) {
		double sum = 0.0;
		for (int i = 0; i < k; ++i) {
			sum += data[i];
		}
		double minAvg = sum / k;
		double maxAvg = minAvg;
		for (int i = k; i < data.length; ++i) {
			sum += data[i] - data[i-k];
			double avg = sum / k;
			minAvg = Math.min(minAvg, avg);
			maxAvg = Math.max(maxAvg, avg);
		}
		return maxAvg - minAvg;
	}
}
