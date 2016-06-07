class IntegerGenerator:
	def nextInteger(self, allowed, current):
		allowedDict = [False for x in range(10)]
		for x in allowed:
			allowedDict[x] = True
		for x in current:
			if not allowedDict[int(x)]:
				return 'INVALID INPUT'
		if int(current) == 0 or current[0] == '0':
			return 'INVALID INPUT'
		maxBit = 0
		for x in range(9, 0, -1):
			if allowedDict[x]:
				maxBit = x
				break
		minNonZeroBit = 9
		for x in range(1, 9):
			if allowedDict[x]:
				minNonZeroBit = x
				break
		minBit = 0 if allowedDict[0] else minNonZeroBit
		if current == (str(maxBit) * len(current)):
			# Increase one bit.
			return str(minNonZeroBit) + (str(minBit) * len(current))
		# Move one bit higher.
		result = list(current)
		for i in range(len(result) - 1, -1, -1):
			x = int(result[i])
			if x < maxBit:
				for j in range(x + 1, 10):
					if allowedDict[j]:
						nextX = j
						break
				result[i] = str(nextX)
				for j in range(i+1, len(result)):
					result[j] = str(minBit)
				return ''.join(result)
