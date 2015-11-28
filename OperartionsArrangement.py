class OperationsArrangement:
	def arrange(self, sequence):
		if '0' in sequence:
			return '*'.join(sequence)
		result = []
		prev = 0
		for i in range(len(sequence)):
			if i == 0:
				result.append(sequence[i])
				prev = int(sequence[i])
				continue
			cur = int(sequence[i])
			if prev == 1 or cur == 1:
				result.append('*')
				result.append(sequence[i])
				prev = prev * cur
				continue
			if prev == 2 and cur == 2:
				result.append('*')
				result.append(sequence[i])
				prev = prev * cur
				continue
			result.append('+')
			result.append(sequence[i])
			prev = cur
		return ''.join(result)
