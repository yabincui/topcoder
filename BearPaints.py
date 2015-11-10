class BearPaints:
	def maxArea(self, W, H, M):
		result = 0
		for w in range(1, min(W+1, M+1)):
			h = min(M/w, H)
			area = w * h
			result = max(result, area)
		return result
