class Palindromize:
    def minAdds(self, s):
        reverse_s = s[::-1]
        matchLen = self.kmpMatch(s, reverse_s)
        return s + s[:len(s)-matchLen][::-1]
    
    def kmpMatch(self, text, pattern):
        m = len(pattern)
        prevMatch = [0 for x in range(m+1)]
        prevMatch[0] = 0
        if m > 0:
            prevMatch[1] = 0
        matchLen = 0
        for i in range(2, m+1):
            while matchLen != 0 and pattern[i-1] != pattern[matchLen]:
                matchLen = prevMatch[matchLen]
            if pattern[i-1] == pattern[matchLen]:
                matchLen += 1
            prevMatch[i] = matchLen
        
        matchLen = 0
        for i in range(1, len(text)+1):
            while matchLen != 0 and text[i-1] != pattern[matchLen]:
                matchLen = prevMatch[matchLen]
            if text[i-1] == pattern[matchLen]:
                matchLen += 1
        return matchLen