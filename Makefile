UnitTestList := ZigZagTest BadNeighborsTest FlowerGardenTest

all: FlowerGardenTest


JUNIT_MAIN = org.junit.runner.JUnitCore

%.class : %.java
	javac $<

ZigZagTest: ZigZag.class ZigZagTest.class
	java $(JUNIT_MAIN) ZigZagTest

BadNeighborsTest: BadNeighbors.class BadNeighborsTest.class
	java $(JUNIT_MAIN) BadNeighborsTest

FlowerGardenTest: FlowerGarden.class FlowerGardenTest.class
	java $(JUNIT_MAIN) FlowerGardenTest

clean:
	rm -rf *.class
