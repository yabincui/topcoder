class RunLengthEncoding:
	def decode(self, text):
		result = []
		repeat = 0
		for i in range(len(text)):
			if text[i] >= '0' and text[i] <= '9':
				repeat = repeat * 10 + int(text[i])
			else:
				c = text[i]
				if repeat == 0:
					repeat = 1
				i = 0
				while i < repeat:
					i += 1
					result.append(c)
					if len(result) > 50:
						return 'TOO LONG'
				repeat = 0
		return ''.join(result)
