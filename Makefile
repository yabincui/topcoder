UnitTestList := ZigZagTest BadNeighborsTest

all: BadNeighborsTest


JUNIT_MAIN = org.junit.runner.JUnitCore

%.class : %.java
	javac $<

ZigZagTest: ZigZag.class ZigZagTest.class
	java $(JUNIT_MAIN) ZigZagTest

BadNeighborsTest: BadNeighbors.class BadNeighborsTest.class
	java $(JUNIT_MAIN) BadNeighborsTest

clean:
	rm -rf *.class
