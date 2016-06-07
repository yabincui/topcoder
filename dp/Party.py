class Party:
	def averageNames(self, n, personA, personB):
		dp = []
		for x in range(n):
			dp.append(1 << x)
		for i in range(len(personA)):
			shared = dp[personA[i]] | dp[personB[i]]
			dp[personA[i]] = dp[personB[i]] = shared
		total = 0
		for i in range(n):
			for j in range(n):
				if i == j:
					continue
				if (dp[i] & (1 << j)) != 0:
					total += 1
		return total * 1.0 / n
