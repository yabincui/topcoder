class ListeningIn:
	def probableMatch(self, typed, phrase):
		removed = []
		match = 0
		for i in range(len(phrase)):
			if typed[match] != phrase[i]:
				removed.append(phrase[i])
				continue
			match += 1
			if match == len(typed):
				removed += phrase[i+1:]
				break
		if match != len(typed):
			return 'UNMATCHED'
		return ''.join(removed)
			
