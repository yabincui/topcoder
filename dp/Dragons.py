class Dragons:
	def snaug(self, initialFood, rounds):
		sides = list(initialFood)
		opsidePositions = [1, 0, 3, 2, 5, 4]
		for i in range(rounds):
			newSides = [0 for x in sides]
			for j in range(6):
				for k in range(6):
					if k == j or k == opsidePositions[j]:
						continue
					newSides[j] += sides[k]
			sides = newSides
		a = sides[2]
		if a == 0:
			return '0'
		b = pow(4, rounds)
		t = self.gcd(a, b)
		a /= t
		b /= t
		if b == 1:
			return str(a)
		return '%d/%d' % (a, b)
	
	def gcd(self, a, b):
		if a < b:
			a, b = b, a
		c = a % b
		while c != 0:
			a = b
			b = c
			c = a % b
		return b
