class PipeCuts:
	def probability(self, weldLocations, L):
		total = len(weldLocations) * (len(weldLocations) - 1) / 2
		win = 0
		for i in range(len(weldLocations)):
			for j in range(i+1, len(weldLocations)):
				left = min(weldLocations[i], weldLocations[j])
				right = max(weldLocations[i], weldLocations[j])
				a = left
				b = right - left
				c = 100 - right
				if a > L or b > L or c > L:
					win += 1
		return win * 1.0 / total
