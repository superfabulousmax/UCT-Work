JFLAGS =-g
JC=javac

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Edge.java \
	Graph.java \
	Main.java \
	Vertex.java 

default: classes

classes: $(CLASSES:.java=.class)

#complie test
compileTest: GraphTest.java
	javac  -cp junit-4.11.jar:hamcrest-core-1.3.jar:. GraphTest.java

runTest: compileTest
	java -cp junit-4.11.jar:hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore GraphTest
#run program
compileProgram: Main.java
	 javac Main.java

run: compileProgram
	java Main
clean:
	$(RM) *.class

