class BasketsWithApples:
	def removeExcess(self, apples):
		apples = sorted(apples)
		apples.reverse()
		maxSum = 0
		for i in range(len(apples)):
			cur = (i + 1) * apples[i]
			maxSum = max(maxSum, cur)
		return maxSum
