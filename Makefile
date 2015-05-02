UnitTestList := ZigZagTest BadNeighborsTest FlowerGardenTest AvoidRoadsTest ChessMetricTest

all: ChessMetricTest


JUNIT_MAIN = org.junit.runner.JUnitCore

%.class : %.java
	javac $<

ZigZagTest: ZigZag.class ZigZagTest.class
	java $(JUNIT_MAIN) ZigZagTest

BadNeighborsTest: BadNeighbors.class BadNeighborsTest.class
	java $(JUNIT_MAIN) BadNeighborsTest

FlowerGardenTest: FlowerGarden.class FlowerGardenTest.class
	java $(JUNIT_MAIN) FlowerGardenTest

AvoidRoadsTest: AvoidRoads.class AvoidRoadsTest.class
	java $(JUNIT_MAIN) AvoidRoadsTest

ChessMetricTest: ChessMetric.class ChessMetricTest.class
	java $(JUNIT_MAIN) ChessMetricTest

clean:
	rm -rf *.class
