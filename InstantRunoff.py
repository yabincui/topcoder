class InstantRunoff:
	def outcome(self, candidates, ballots):
		positions = [0 for x in ballots]
		validCandidates = {}
		for x in candidates:
			validCandidates[x] = True
		while len(validCandidates.keys()) > 1:
			map = {}
			for key in validCandidates:
				map[key] = 0
			for i in range(len(ballots)):
				while not (ballots[i][positions[i]] in validCandidates):
					positions[i] += 1
				key = ballots[i][positions[i]]
				map[key] += 1
			maxValue = max(map.values())
			if maxValue * 2 > len(ballots):
				for (key, value) in map.items():
					if value == maxValue:
						return key
			minValue = min(map.values())
			for (key, value) in map.items():
				if value == minValue:
					del validCandidates[key]
		keys = validCandidates.keys()
		return '' if len(keys) == 0 else keys[0]
