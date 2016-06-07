class TeXLeX:
	def getTokens(self, input):
		result = []
		input = list(input)
		i = 0
		while i < len(input):
			if input[i] != '^':
				result.append(ord(input[i]))
				i += 1
				continue
			if i + 3 < len(input) and input[i+1] == '^':
				valid = True
				a = input[i+2]
				if a >= '0' and a <= '9':
					a = ord(a) - ord('0')
				elif a >= 'a' and a <= 'f':
					a = ord(a) - ord('a') + 10
				else:
					valid = False
				b = input[i+3]
				if b >= '0' and b <= '9':
					b = ord(b) - ord('0')
				elif b >= 'a' and b <= 'f':
					b = ord(b) - ord('a') + 10
				else:
					valid = False
				if valid:
					input[i+3] = chr(a * 16 + b)
					i += 3
					continue
			if i + 2 < len(input) and input[i+1] == '^':
				k = ord(input[i+2])
				if k > 63:
					input[i+2] = chr(k - 64)
				else:
					input[i+2] = chr(k + 64)
				i += 2
				continue
			result.append(ord('^'))
			i += 1
		return result
