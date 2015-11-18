class BearSong:
	def countRareNotes(self, notes):
		note_dict = {}
		for note in notes:
			if note_dict.has_key(note):
				note_dict[note] += 1
			else:
				note_dict[note] = 1
		result = 0
		for (k,v) in note_dict.items():
			if v == 1:
				result += 1
		return result
