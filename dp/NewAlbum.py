class NewAlbum:
	def leastAmountOfCDs(self, nSongs, length, cdCapacity):
		canFill = cdCapacity / (length + 1)
		while (canFill + 1) * length + canFill <= cdCapacity:
			canFill += 1
		if canFill % 13 == 0:
			canFill -= 1
		dp = [0 for x in range(nSongs+1)]
		for i in range(1, nSongs+1):
			canTry = min(i, canFill)
			dp[i] = nSongs
			for fill in range(max(1, canTry - 13), canTry + 1):
				if fill % 13 == 0:
					continue
				dp[i] = min(dp[i], dp[i-fill] + 1)
		return dp[nSongs]
