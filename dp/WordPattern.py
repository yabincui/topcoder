class WordPattern:
	def countWords(self, word):
		if len(word) == 1:
			return 1
		self.dpCenter = [-1 for x in range(len(word))]
		self.dpNoCenter = [-1 for x in range(len(word))]
		self.dpCenter[1] = 1
		self.dpNoCenter[1] = 1
		return 4 * self.count(len(word) - 1, True)
	
	def count(self, length, isCenter):
		if isCenter and self.dpCenter[length] != -1:
			return self.dpCenter[length]
		if (not isCenter) and self.dpNoCenter[length] != -1:
			return self.dpNoCenter[length]
		
		if isCenter:
			result = 2 * self.count(length - 1, False) + self.count(length - 1, True)
			self.dpCenter[length] = result
		else:
			result = 2 * self.count(length - 1, False)
			self.dpNoCenter[length] = result
		return result
