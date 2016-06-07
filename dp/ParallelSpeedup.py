class ParallelSpeedup:
	def numProcessors(self, k, overhead):
		bestProcessors = 1
		minCost = k
		for processors in range(2, k+1):
			communication = processors * (processors - 1) / 2 * overhead
			work = k / processors
			if k % processors != 0:
				work += 1
			cost = communication + work
			if minCost > cost:
				minCost = cost
				bestProcessors = processors
		return bestProcessors
