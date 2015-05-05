UnitTestList := ZigZagTest BadNeighborsTest FlowerGardenTest AvoidRoadsTest ChessMetricTest \
								JewelryTest

all: JewelryTest


JUNIT_MAIN = org.junit.runner.JUnitCore

%.class : %.java
	javac $<

%Test: %.class %Test.class
	java $(JUNIT_MAIN) $@

clean:
	rm -rf *.class
