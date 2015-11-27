class StringCompare:
	def simpleDifference(self, a, b):
		match = 0
		for i in range(min(len(a), len(b))):
			if a[i] == b[i]:
				match += 1
		return match
