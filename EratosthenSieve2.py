class EratosthenSieve2:
	def nthElement(self, n):
		values = range(1, 1001)
		for i in range(1, 10):
			mod = i + 1
			new_values = []
			for j in range(1, len(values)+1):
			  if j % mod == 0:
			  	continue
			  new_values.append(values[j-1])
			values = new_values
		return values[n-1]
