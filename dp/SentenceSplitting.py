class SentenceSplitting:
	def split(self, sentence, K):
		dp = []
		dp.append([0 for x in range(K+1)])
		for i in range(1, len(sentence)+1):
			dp.append([-1 for x in range(K+1)])
			dp[i][0] = dp[i-1][0] + 1
			if sentence[i-1] != ' ':
				continue
			dp[i][1] = dp[i][0] - 1
			for j in range(0, i):
				if j != 0 and sentence[j-1] != ' ':
					continue
				lastWordLen = i - j - 1
				for k in range(1, K):
					curWidth = max(dp[j][k], lastWordLen)
					if dp[i][k+1] == -1 or dp[i][k+1] > curWidth:
						dp[i][k+1] = curWidth
			#print 'dp[%d] = ' % i, dp[i]
		minWidth = [-1 for x in range(K+1)]
		for i in range(len(sentence)+1):
			if i != 0 and sentence[i-1] != ' ':
				continue
			lastWordLen = len(sentence) - i
			for k in range(0, K+1):
				curWidth = max(dp[i][k], lastWordLen)
				if minWidth[k] == -1 or minWidth[k] > curWidth:
					minWidth[k] = curWidth
		return min(minWidth)
				
			
				
