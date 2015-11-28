class SyllableCounter:
	def countSyllables(self, word):
		count = 0
		inVowel = False
		for x in word:
			if x in 'AEIOU':
				if not inVowel:
					inVowel = True
					count += 1
			else:
				inVowel = False
		return count
