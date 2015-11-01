class BusTrip:
    def returnTime(self, N, buses):
        busCount = len(buses)
        busStops = []
        for bus in buses:
            busStops.append([int(x) for x in bus.split()])
        busNextStopIndex = [1 for x in range(busCount)]
        busDir = [0 for x in range(busCount)]
        busPos = [x[0] for x in busStops]
        for i in range(busCount):
            if busStops[i][1] > busStops[i][0]:
                busDir[i] = 1
            else:
                busDir[i] = -1
        onBus = False
        curStation = 0
        curBus = -1
        for i in range(busCount):
            if busStops[i][0] == 0:
                onBus = True
                curBus = i
                break
                
        time = 0
        while time <= 1000:
            if not onBus:
                # Get on a bus.
                for i in range(busCount):
                    if busStops[i][busNextStopIndex[i]] == curStation and busPos[i] == curStation:
                        onBus = True
                        curBus = i
                        break
            else:
                # Get off a bus.
                if busPos[curBus] == busStops[curBus][busNextStopIndex[curBus]]:
                    onBus = False
                    curStation = busPos[curBus]
                    if curStation == 0:
                        break
            # Move each bus.
            for i in range(busCount):
                if busPos[i] == busStops[i][busNextStopIndex[i]]:
                    busNextStopIndex[i] = (busNextStopIndex[i] + 1) % len(busStops[i])
                    busDir[i] = 1 if busStops[i][busNextStopIndex[i]] > busPos[i] else -1
                busPos[i] += busDir[i] 
                
            time += 1
            
        return time if time <= 1000 else -1