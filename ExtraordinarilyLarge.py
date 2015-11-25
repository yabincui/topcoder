class ExtraordinarilyLarge:
	def compare(self, x, y):
		a, factor_a = self.decode(x)
		b, factor_b = self.decode(y)
		common = min(factor_a, factor_b)
		factor_a -= common
		factor_b -= common
		if factor_a == 0:
			while factor_b > 0 and b <= a:
				b = self.compute(b, a+1)
				factor_b -= 1
		else:
			while factor_a > 0 and a <= b:
				a = self.compute(a, b+1)
				factor_a -= 1
		if a < b:
			return 'x<y'
		elif a == b:
			return 'x=y'
		else:
			return 'x>y'
	
	def decode(self, s):
		value = 0
		i = 0
		while i < len(s) and s[i] >= '0' and s[i] <= '9':
			value = value * 10 + ord(s[i]) - ord('0')
			i += 1
		factor_count = len(s) - i
		if value == 0 and factor_count > 0:
			value = 1
			factor_count = 0
		return (value, factor_count)
	
	def compute(self, n, limit):
		result = 1
		i = 2
		while i <= n and result < limit:
			result *= i
			i += 1
		return result
