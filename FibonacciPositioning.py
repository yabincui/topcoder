class FibonacciPositioning:
	def getFPosition(self, n):
		a = 1
		b = 1
		a_pos = 1
		while b < n:
			c = a + b
			a = b
			b = c
			a_pos += 1
		if b == n:
			return a_pos + 1
		return a_pos + (n - a) * 1.0 / (b - a)
		
