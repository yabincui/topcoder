class SortMachine:
	def countMoves(self, a):
		a = list(a)
		count = 0
		n = len(a)
		for i in range(n):
			cur = a[i]
			for j in range(i+1, len(a)):
				if a[j] < cur:
					count += 1
					a.append(cur)
					break
		return count
