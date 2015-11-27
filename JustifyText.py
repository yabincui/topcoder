class JustifyText:
	def format(self, text):
		maxLen = 0
		for x in text:
			maxLen = max(maxLen, len(x))
		justifiedText = []
		for i in range(len(text)):
			justifiedText.append(' ' * (maxLen - len(text[i])) + text[i])
		return justifiedText
