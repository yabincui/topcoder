class TravellingByTrain:
	def earliestArrival(self, timeTable):
		curDay = 1
		curTime = 9 * 60
		for s in timeTable:
			times = s.split()
			minNextDay = -1
			minNextTime = -1
			for time in times:
				fromT = int(time[0:2]) * 60 + int(time[3:5])
				toT = int(time[6:8]) * 60 + int(time[9:11])
				if fromT < toT:
					dayCost = 0
				else:
					dayCost = 1
				if curTime > fromT:
					dayCost += 1
				nextDay = curDay + dayCost
				nextTime = toT
				if minNextDay == -1 or minNextDay > nextDay or (
					minNextDay == nextDay and minNextTime > nextTime):
					minNextDay, minNextTime = nextDay, nextTime
			curDay = minNextDay
			curTime = minNextTime
		timeStr = '%02d:%02d' % (curTime / 60, curTime % 60)
		return '%s, day %d' % (timeStr, curDay)
				
