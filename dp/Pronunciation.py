class Pronunciation:
	def canPronounce(self, words):
		for word in words:
			can = True
			state = 0
			for x in word:
				if x.lower() in 'aeiou':
					if state <= 0:
						state = 1
						last_x = x.lower()
					elif last_x == x.lower():
						pass
					else:
						can = False
						break
				else:
					if state >= 0:
						state = -1
					elif state == -1:
						state = -2
					else:
						can = False
						break
			if not can:
				return word
		return ''
