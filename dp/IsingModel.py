class IsingModel:
	def energy(self, spins):
		result = 0
		rows = len(spins)
		cols = len(spins[0])
		for r in range(rows):
			if r + 1 < rows:
				for c in range(cols):
					if spins[r][c] == spins[r+1][c]:
						result -= 1
					else:
						result += 1
		for r in range(rows):
			for c in range(cols):
				if c + 1 < cols:
					if spins[r][c] == spins[r][c+1]:
						result -= 1
					else:
						result += 1
		return result
