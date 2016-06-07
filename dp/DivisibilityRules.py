class DivisibilityRules:
	def similar(self, numerationBase, divisor):
		expected = []
		x = 1
		while True:
			rem = x % divisor
			if rem in expected:
				expected.append(rem)
				break
			expected.append(rem)
			x = (x * numerationBase) % divisor
		
		count = 0
		for i in range(2, numerationBase):
			match = 0
			x = 1
			while True:
				rem = x % i
				if rem != expected[match]:
					break
				else:
					match += 1
					if match == len(expected):
						break
				x = (x * numerationBase) % i
			if match == len(expected):
				count += 1
		return count
		
		
