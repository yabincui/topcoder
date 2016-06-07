class BinarySearchable:
	def howMany(self, sequence):
		n = len(sequence)
		result = 0
		for i in range(n):
			searchable = True
			for j in range(0, i):
				if sequence[j] > sequence[i]:
					searchable = False
					break
			for j in range(i+1, n):
				if sequence[j] < sequence[i]:
					searchable = False
					break
			if searchable:
				result += 1
		return result
