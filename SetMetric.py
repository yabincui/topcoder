class SetMetric:
	def nearness(self, target, candidate):
		target = sorted(target)
		candidate = sorted(candidate)
		m = len(target)
		n = len(candidate)
		dp = [[0 for x in range(n+1)] for x in range(m+1)]
		for i in range(1, m+1):
			for j in range(i, n+1):
				# Match i with j - 1.
				dp[i][j] = int(1e9)
				if j > i:
					dp[i][j] = min(dp[i][j], dp[i][j-1])
				dp[i][j] = min(dp[i][j], dp[i-1][j-1] + abs(target[i-1] - candidate[j-1]))
		return dp[m][n]
