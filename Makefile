UnitTestList := ZigZagTest

all: ZigZagTest


JUNIT_MAIN = org.junit.runner.JUnitCore

%.class : %.java
	javac $<

ZigZagTest: ZigZag.class ZigZagTest.class
	java $(JUNIT_MAIN) ZigZagTest

clean:
	rm -rf *.class
