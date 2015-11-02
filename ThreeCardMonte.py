class ThreeCardMonte:
    def position(self, swaps):
        pos = [0, 1, 2]
        for c in swaps:
            if c == 'L':
                (pos[0], pos[1]) = (pos[1], pos[0])
            elif c == 'R':
                (pos[1], pos[2]) = (pos[2], pos[1])
            elif c == 'E':
                (pos[0], pos[2]) = (pos[2], pos[0])
        if pos[0] == 1:
            return 'L'
        elif pos[1] == 1:
            return 'M'
        return 'R'