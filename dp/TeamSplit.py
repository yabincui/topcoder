class TeamSplit:
	def difference(self, strengths):
		strengths = sorted(strengths)
		strengths.reverse()
		teamOne = 0
		teamTwo = 0
		for i in range(len(strengths)):
			if i % 2 == 0:
				teamOne += strengths[i]
			else:
				teamTwo += strengths[i]
		return teamOne - teamTwo
