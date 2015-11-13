class PackingParts:
	def pack(self, partSizes, boxSizes):
		useBoxCount = 0
		result = 0
		for i in range(0, len(partSizes)):
			partCount = i + 1
			while useBoxCount < len(boxSizes) and boxSizes[useBoxCount] < partSizes[i]:
				useBoxCount += 1
			if useBoxCount == len(boxSizes):
				break
			useBoxCount += 1
			result = partCount
		return result
		
