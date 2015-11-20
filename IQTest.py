class IQTest:
	def nextNumber(self, previous):
		n = len(previous)
		if n == 1:
			return 'AMBIGUITY'
		if n == 2:
			if previous[0] == previous[1]:
				return str(previous[1])
			else:
				return 'AMBIGUITY'
		mul = previous[1] - previous[0]
		mul_result = previous[2] - previous[1]
		if mul == 0:
			if mul_result == 0:
				for v in previous:
					if v != previous[0]:
						return 'BUG'
				return str(previous[0])
			else:
				return 'BUG'
		if mul_result % mul != 0:
			return 'BUG'
		a = mul_result / mul
		b = previous[1] - a * previous[0]
		for i in range(1, len(previous)):
			if previous[i] != a * previous[i-1] + b:
				return 'BUG'
		return str(previous[-1] * a + b)
