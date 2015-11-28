class DVDPlayer:
	def findMovies(self, moviesWatched):
		movieMap = {}
		for movie in moviesWatched:
			movieMap[movie] = movie
	
		cur = moviesWatched[0]
		movieMap[cur] = ''
		for i in range(1, len(moviesWatched)):
			next = moviesWatched[i]
			for (key, value) in movieMap.items():
				if value == next:
					select = key
					break
			movieMap[select] = cur
			cur = next
		result = []
		for (key, value) in movieMap.items():
			if len(value) != 0 and key != value:
				result.append("%s is inside %s's case" % (value, key))
		return sorted(result)
			
