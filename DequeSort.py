class DequeSort:
	def minDeques(self, data):
		sortedData = sorted(data)
		numToIndex = {}
		for i in range(len(sortedData)):
			numToIndex[sortedData[i]] = i
		deques = []
		for x in data:
			index = numToIndex[x]
			pushed = False
			for deque in deques:
				if index == deque[0] - 1:
					deque[0] -= 1
					pushed = True
					break
				if index == deque[1] + 1:
					deque[1] += 1
					pushed = True
					break
			if not pushed:
				deques.append([index, index])
		return len(deques)
