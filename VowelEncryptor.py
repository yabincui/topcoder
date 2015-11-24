class VowelEncryptor:
	def encrypt(self, text):
		text = list(text)
		for i in range(len(text)):
			allVowel = True
			for v in text[i]:
				if not self.isVowel(v):
					allVowel = False
					break
			if not allVowel:
				s = ''
				for v in text[i]:
					if not self.isVowel(v):
						s += v
				text[i] = s
		return text
		
	def isVowel(self, v):
		return v in 'aeiou'
