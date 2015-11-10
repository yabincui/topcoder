class BearDestroysDiv2:
	def sumUp(self, W, H, MOD):
		mask = (1 << W) - 1
		# prevFallDp[i] is how many ways current row is filled with fall trees. 1 filled, 0 not filled.
		prevFall = [0 for x in range(mask + 1)]
		prevFall[0] = 1
		result = 0
		for row in range(0, H):
			nextFall = [0 for x in range(mask + 1)]
			curSum = 0
			for curMask in range(0, mask + 1):
				# curMask is the mask of 'S', 'E' in current row. 0 is 'S', 1 is 'E'.
				for fall in range(0, mask + 1):
					# fall is prev Fall situation.
					fallCount = prevFall[fall]
					if fallCount == 0:
						continue
					curFall = fall
					nfall = 0
					for i in range(0, W):
						if (curFall & (1 << i)) != 0:
							continue
						if (curMask & (1 << i)) == 0:
							# Prefer 'S'.
							if row != H - 1:
								nfall |= 1 << i
								curSum += fallCount
							elif i != W - 1 and (curFall & (1 << (i + 1))) == 0:
								curFall |= 1 << (i + 1)
								curSum += fallCount
						else:
							# Prefer 'E'.
							if i != W - 1 and (curFall & (1 << (i + 1))) == 0:
								curFall |= 1 << (i + 1)
								curSum += fallCount
							elif row != H - 1:
								nfall |= 1 << i
								curSum += fallCount
					nextFall[nfall] = (nextFall[nfall] + fallCount) % MOD
			prevFall = nextFall
			result = (result + curSum * pow(2, (H - 1 - row) * W) % MOD) % MOD
		return result
							  
