class Trekking:
	def findCamps(self, trail, plans):
		n = len(trail)
		result = -1
		for plan in plans:
			valid = True
			campCount = 0
			for i in range(n):
				if trail[i] == '^' and plan[i] == 'C':
					valid = False
					break
				if plan[i] == 'C':
					campCount += 1
			if valid:
				if result == -1 or result > campCount:
					result = campCount
		return result