import java.math.*;

public class CantorSet {
	public int removed(String value) {
		BigInteger v = new BigInteger(value.substring(1));
		BigInteger high = BigInteger.valueOf(1);
		for (int i = 0; i < value.length() - 1; ++i) {
			high = high.multiply(BigInteger.valueOf(10));
		}
		for (int step = 1; step < 1000000; ++step) {
			v = v.multiply(BigInteger.valueOf(3));
			if (v.compareTo(high) <= 0) {
				continue;
			} else if (v.compareTo(high.add(high)) >= 0) {
				v = v.subtract(high.add(high));
			} else {
				return step;
			}
		}
		return 0;
	}
}
