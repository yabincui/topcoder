class Shuffling:
    def position(self, cards, shuffles):
        pos = 0
        for shuffle in shuffles:
            origin_shuffle = shuffle
            if shuffle > 0:
                if pos < cards / 2:
                    # It is in the bottom half.
                    if pos < shuffle:
                        # It is put first.
                        pos = pos
                    else:
                        pos = shuffle + (pos + 1 - shuffle) * 2 - 1
                else:
                    # It is in the top half.
                    if pos < cards - shuffle:
                        # It is put in the middle.
                        pos = shuffle + (pos - cards/2) * 2
                    else:
                        # It is put last.
                        pos = pos
            else:
                shuffle = -shuffle
                if pos < cards / 2:
                    # It is in the bottom half.
                    if pos < cards/2 - shuffle:
                        # It is put in the middle.
                        pos = shuffle + pos * 2
                    else:
                        # It is put last.
                        pos = pos + cards/2
                else:
                    # It is in the top half.
                    if pos < cards/2 + shuffle:
                        # It is put first.
                        pos = pos - cards/2
                    else:
                        pos = shuffle + (pos - cards/2 + 1 - shuffle) * 2 - 1 
            #print 'afte shuffle %d, pos = %d' % (origin_shuffle, pos)
        return pos