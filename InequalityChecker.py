class InequalityChecker:
	def getDifferences(self, n):
		s = 0
		for i in range(1, n):
			s += i * i * i
		S = s + n * n * n
		sum = 2* (s + S) - n * n * n * n
		div = 4
		if sum % 4 == 0:
			sum /= 4
			div = 1
		elif sum % 2 == 0:
			sum /= 2
			div = 2
		return (sum, div) 
