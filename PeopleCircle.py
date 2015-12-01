class PeopleCircle:
	def order(self, numMales, numFemales, K):
		n = numMales + numFemales
		result = ['M' for x in range(n)]
		valid = [True for x in range(n)]
		last = n
		cur = 0
		while last > numMales:
			k = (K - 1) % last
			while True:
				while not valid[cur]:
					cur = (cur + 1) % n
				if k > 0:
					cur = (cur + 1) % n
					k -= 1
				else:
					break
			valid[cur] = False
			result[cur] = 'F'
			last -= 1
		return ''.join(result)
