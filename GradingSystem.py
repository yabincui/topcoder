class GradingSystem:
	def fairness(self, scores, grades):
		n = len(scores)
		schemeOne = []
		minGrade = 0
		for i in range(n):
			g = max(minGrade, grades[i])
			schemeOne.append(g)
			minGrade = g
		schemeTwo = [0 for x in range(n)]
		maxGrade = 8
		for i in range(n-1, -1, -1):
			g = min(maxGrade, grades[i])
			schemeTwo[i] = g
			maxGrade = g
		diff = 0
		for i in range(n):
			diff += abs(schemeOne[i] - schemeTwo[i])
		return diff
