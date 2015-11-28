import re

class ChatTranscript:
	def howMany(self, transcript, name):
		count = 0
		for s in transcript:
			m = re.search('^([A-Za-z0-9]+):', s)
			if m and m.group(1) == name:
				count += 1
		return count
