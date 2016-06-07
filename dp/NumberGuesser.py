class NumberGuesser:
	def guess(self, leftOver):
		for a in range(1, 9999):
			bList = self.getPossibleB(a)
			for b in bList:
				if a > b:
					c = a - b
					c = str(c)
					while '0' in c:
						c = self.removeDigit(c, '0')
					valid = True
					for x in leftOver:
						if x != '0':
							if x in c:
								c = self.removeDigit(c, x)
							else:
								valid = False
								break
					if not valid:
						continue
					if len(c) == 1 and c[0] > '0':
						return c[0]
	
	def getPossibleB(self, a):
		nonZeroDigits = []
		for x in str(a):
			if x != '0':
				nonZeroDigits.append(int(x))
		result = []
		self.recursiveFill(0, nonZeroDigits, result)
		return result
	
	def recursiveFill(self, curValue, leftDigits, result):
		if curValue > 9998:
			return
		# Move curValue into result.
		if sum(leftDigits) == 0:
			if curValue >= 1 and curValue <= 9998:
				result.append(curValue)
		# Append 0.
		if curValue != 0:
			value = curValue * 10
			self.recursiveFill(value, leftDigits, result)
		# Append one digit.
		for i in range(len(leftDigits)):
			if leftDigits[i] != 0:
				tmp = leftDigits[i]
				value = curValue * 10 + leftDigits[i]
				leftDigits[i] = 0
				self.recursiveFill(value, leftDigits, result)
				leftDigits[i] = tmp
				
	def removeDigit(self, s, ch):
		for i in range(len(s)):
			if s[i] == ch:
				return s[:i] + s[i+1:]	 
