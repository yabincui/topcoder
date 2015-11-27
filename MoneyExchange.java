import java.util.*;

public class MoneyExchange {	
	class Edge {
		int fromType;
		int toType;
		double rate;
		Edge(int fromType, int toType, double rate) {
			this.fromType = fromType;
			this.toType = toType;
			this.rate = rate;
		}
	}
	
	public double bestRate(String[] rates, String type1, String type2) {
		if (type1.equals(type2)) {
			return 1.0;
		}
		HashMap<String, Integer> typeMap = new HashMap<String, Integer>();
		Edge[] edges = new Edge[rates.length];
		for (int i = 0; i < rates.length; ++i) {
			String[] strs = rates[i].split(" ");
			String type = strs[0];
			if (!typeMap.containsKey(type)) {
				typeMap.put(type, typeMap.size());
			}
			int fromType = typeMap.get(type);
			type = strs[2];
			if (!typeMap.containsKey(type)) {
				typeMap.put(type, typeMap.size());
			}
			int toType = typeMap.get(type);
			double r = Integer.parseInt(strs[3]) * 1.0 / Integer.parseInt(strs[1]);
			edges[i] = new Edge(fromType, toType, r);
		}
		Integer startType = typeMap.get(type1);
		Integer endType = typeMap.get(type2);
		if (startType == null || endType == null) {
			return -1;
		}
		int typeCount = typeMap.size();
		double[] value = new double[typeCount];
		for (int i = 0; i < typeCount; ++i) {
			value[i] = -1;
		}
		value[startType] = 1.0;
		// Try n times.
		for (int round = 0; round < edges.length; ++round) {
			for (Edge edge : edges) {
				if (value[edge.fromType] >= 0) {
					double v = value[edge.fromType] * edge.rate;
					value[edge.toType] = Math.max(value[edge.toType], v);
				}
			}
		}
		return value[endType];
	}
}
