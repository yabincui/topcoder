class Sets:
	def operate(self, A, B, operation):
		result = []
		if operation == 'UNION':
			result += A
			for x in B:
				if x not in A:
					result.append(x)
		elif operation == 'INTERSECTION':
			for x in A:
				if x in B:
					result.append(x)
		elif operation == 'SYMMETRIC DIFFERENCE':
			for x in A:
				if x not in B:
					result.append(x)
			for x in B:
				if x not in A:
					result.append(x)
		return sorted(result)
