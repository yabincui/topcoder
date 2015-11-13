class RandomizedQuickSort:
	def getExpectedTime(self, listSize, S, a, b):
		dp = [-1 for x in range(listSize + 1)]
		dp[0] = 0
		for i in range(1, min(S+1, listSize+1)):
			dp[i] = b * i * i
		for i in range(S + 1, listSize + 1):
			sum = 0
			for left in range(0, i):
				right = i - left - 1
				sum += dp[left] + dp[right]
			sum /= float(i)
			sum += a * i
			dp[i] = sum
		return dp[listSize]
