class OptimalQueues:
	def minWaitingTime(self, clientArrivals, tellerCount, serviceTime):
		clientArrivals = list(clientArrivals)
		clientArrivals.sort()
		clientArrivals = clientArrivals[::-1]
		cycle = 0
		result = 0
		for i in range(len(clientArrivals)):
			cur = (cycle + 1) * serviceTime + clientArrivals[i]
			result = max(result, cur)
			if (i + 1) % tellerCount == 0:
				cycle += 1
		return result
