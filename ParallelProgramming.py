class ParallelProgramming:
	def minTime(self, time, prec):
		prev = [0 for x in time]
		for i in range(len(prec)):
			for j in range(len(prec[i])):
				if prec[i][j] == 'Y':
					prev[j] += 1
		dp = [-1 for x in time]
		while True:
			select = -1
			for i in range(len(prev)):
				if prev[i] == 0 and dp[i] == -1:
					select = i
					break
			if select == -1:
				break
			maxPrev = 0
			for i in range(len(prec)):
				if prec[i][select] == 'Y':
					maxPrev = max(maxPrev, dp[i])
			dp[select] = maxPrev + time[select]
			for i in range(len(prec[select])):
				if prec[select][i] == 'Y':
					prev[i] -= 1
		if sum([1 if x == -1 else 0 for x in dp]) != 0:
			return -1
		return max(dp)
