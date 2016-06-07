import java.util.*;

public class InfiniteSequence {
	HashMap<Long, Long> map;
	int p;
	int q;
	public long calc(long n, int p, int q) {
		map = new HashMap<Long, Long>();
		map.put(0L, 1L);
		this.p = p;
		this.q = q;
		return find(n);
	}
	
	public long find(long n) {
		if (!map.containsKey(n)) {
			long result = find(n / p) + find(n / q);
			map.put(n, result);
			return result;
		} else {
			return map.get(n);
		}
	}
}