class DiskSpace:
	def minDrives(self, used, total):
		total = sorted(total)
		total.reverse()
		usedSum = sum(used)
		for i in range(len(total)):
			if usedSum <= total[i]:
				return i+1
			usedSum -= total[i]
		return len(total)
