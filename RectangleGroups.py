class RectangleGroups:
	def maximalIndexed(self, rectangles):
		groups = {}
		for rectangle in rectangles:
			strs = rectangle.split(' ')
			name = strs[0]
			length = int(strs[1])
			width = int(strs[2])
			groups[name] = groups.get(name, 0) + length * width
		
		maxValue = 0
		result = ''
		for (key, value) in groups.items():
			if value > maxValue:
				maxValue = value
				result = key
			elif value == maxValue and key < result:
				result = key
		return '%s %d' % (result, maxValue)
