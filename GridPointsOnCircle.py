import math

class GridPointsOnCircle:
	def countPoints(self, rSquare):
		limit = int(math.sqrt(rSquare))
		if (limit + 1) * (limit + 1) <= rSquare:
			limit += 1
		count = 0
		for a in range(0, limit+1):
			b = int(math.sqrt(rSquare - a * a))
			if a * a + b * b == rSquare:
				if b != 0:
					count += 1
		return count * 4
