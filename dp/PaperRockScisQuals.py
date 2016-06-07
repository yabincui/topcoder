class PaperRockScisQuals:
	def whoPassed(self, players):
		n = len(players)
		scores = [0 for x in range(n)]
		for i in range(n):
			for j in range(i+1, n):
				diff = 0
				for k in range(5):
					diff += self.diff(players[i][k], players[j][k])
				if diff > 0:
					scores[i] += 2
				elif diff == 0:
					scores[i] += 1
					scores[j] += 1
				elif diff < 0:
					scores[j] += 2
		result = 0
		for i in range(1, n):
			if scores[i] > scores[result]:
				result = i
		return result
	
	def diff(self, a, b):
		if a == b:
			return 0
		if a == 'P':
			return 1 if b == 'R' else -1
		if a == 'R':
			return 1 if b == 'S' else -1
		if a == 'S':
			return 1 if b == 'P' else -1
