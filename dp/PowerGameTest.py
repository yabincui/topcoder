import unittest

class PowerGame(object):
	def winner(self, size0, size1):
		INT_MAX = 1 << 31
		wins = {}
		steps = {}
		wins[0] = 0
		steps[0] = 0
		for i in range(1, max(size0, size1) + 1):
			wins[i] = 0
			for j in range(1, i+1):
				if j * j > i:
					break
				k = i - j * j
				if wins[k] == 0:
					wins[i] = 1
					break
			
			cur_step = INT_MAX if wins[i] == 1 else 0
			for j in range(1, i+1):
				if j * j > i:
					break
				k = i - j * j
				if wins[i] == 1:
					if wins[k] == 0:
						cur_step = min(cur_step, steps[k] + 1)
				else:
					cur_step = max(cur_step, steps[k] + 1)
			steps[i] = cur_step
		
		is_win = wins[size0] if (steps[size0] < steps[size1]) else wins[size1]
		step = min(steps[size0], steps[size1])
		if is_win:
			return 'Alan will win after %d moves' % step
		else:
			return 'Bob will win after %d moves' % step

class PowerGameTest(unittest.TestCase):
	def test(self):
		game = PowerGame()
		self.assertEqual('Alan will win after 1 moves', game.winner(4, 9))
		self.assertEqual('Alan will win after 1 moves', game.winner(4, 3))
		self.assertEqual('Bob will win after 2 moves', game.winner(2, 3))
		self.assertEqual('Bob will win after 4 moves', game.winner(7, 13))
		self.assertEqual('Alan will win after 7 moves', game.winner(2136, 1244))
		
if __name__ == '__main__':
	unittest.main()