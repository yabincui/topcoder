import re

class TwoEquations:
    def solve(self, first, second):
        m = re.match(r'(.+?)\*X\s\+\s(.+?)\*Y\s=\s(.+)', first)
        va = [self.strToInt(x) for x in [m.group(1), m.group(2), m.group(3)]]
        m = re.match(r'(.+?)\*X\s\+\s(.+?)\*Y\s=\s(.+)', second)
        vb = [self.strToInt(x) for x in [m.group(1), m.group(2), m.group(3)]]
    
        if va[0] == 0:
            if va[1] == 0:
                if va[2] == 0:
                    return self.oneEquation(vb)
                else:
                    return 'NO SOLUTIONS'
            else:
                y = (va[2], va[1])
                return self.oneEquation(vb, y)
        elif vb[0] == 0:
            if vb[1] == 0:
                if vb[2] == 0:
                    return self.oneEquation(va)
                else:
                    return 'NO SOLUTIONS'
            else:
                y = (vb[2], vb[1])
                return self.oneEquation(va, y)
        else:
            mulA = vb[0]
            mulB = va[0]
            va = [x * mulA for x in va]
            vb = [x * mulB for x in vb]
            yco = vb[1] - va[1]
            sumco = vb[2] - va[2]
            if yco == 0:
                if sumco == 0:
                    return 'MULTIPLE SOLUTIONS'
                else:
                    return 'NO SOLUTIONS'
            y = (sumco, yco)
            return self.oneEquation(va, y)
    
    def oneEquation(self, v, y=None):
        if y is None:
            if v[0] == 0 and v[1] == 0 and v[2] != 0:
                return 'NO SOLUTIONS'
            else:
                return 'MULTIPLE SOLUTIONS'
        v[0] *= y[1]
        v[2] *= y[1]
        v[2] -= y[0] * v[1]
        if v[0] == 0:
            if v[2] == 0:
                return 'MULTIPLE SOLUTIONS'
            else:
                return 'NO SOLUTIONS'
        x = (v[2], v[0])
        x = self.remedyValue(x)
        y = self.remedyValue(y)
        return 'X=%s/%s Y=%s/%s' % (self.intToStr(x[0]), self.intToStr(x[1]), self.intToStr(y[0]), self.intToStr(y[1]))
    
    def remedyValue(self, value):
        if value[0] == 0:
            return (0, 1)
        flag = 1 if value[0] * value[1] > 0 else -1
        a = abs(value[0])
        b = abs(value[1])
        factor = self.gcd(a, b)
        a /= factor
        b /= factor
        return (a * flag, b)
        
    def gcd(self, a, b):
        if a < b:
            (a, b) = (b, a)
        c = a % b
        while c != 0:
            a = b
            b = c
            c = a % b
        return b
        
    def strToInt(self, s):
        print 'strToInt(%s)' % s
        m = re.match(r'\((.+)\)', s)
        if m:
            return int(m.group(1))
        else:
            return int(s)
    
    def intToStr(self, value):
        if value < 0:
            return '(%d)' % value
        else:
            return str(value)
    