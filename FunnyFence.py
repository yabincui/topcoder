class FunnyFence:
	def getLength(self, s):
		prev = 1
		maxLen = 1
		for i in range(1, len(s)):
			if s[i] == s[i-1]:
				prev = 1
			else:
				prev = prev + 1
				maxLen = max(maxLen, prev)
		return maxLen
