class DollSets:
	def maximumQuantity(self, dollSizes, K):
		dollSet = {}
		for doll in dollSizes:
			if dollSet.has_key(doll):
				dollSet[doll] += 1
			else:
				dollSet[doll] = 1
		dollSizes = list(dollSizes)
		dollSizes.sort()
		result = 0
		for i in range(len(dollSizes)):
			doll = dollSizes[i]
			if dollSet[doll] == 0:
				continue
			if K == 1:
				result += dollSet[doll] / 2
				dollSet[doll] = 0
			else:
				cur = doll
				curCount = dollSet[doll]
				next = cur * K
				while curCount != 0 and dollSet.has_key(next):
					nextCount = dollSet[next]
					omitCount = min(curCount, nextCount)
					result += omitCount
					dollSet[cur] = 0
					dollSet[next] -= omitCount
					cur = next
					curCount = nextCount - omitCount
					next = cur * K
		return result
