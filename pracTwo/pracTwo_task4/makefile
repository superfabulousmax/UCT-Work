#My make file
JC=javac
#JFLAGS=-g

#compile normal
compile: SimpleTreeWriter.java AVLTreeNode.java TreeUtils.java AVLTree.java TreeUI.java SimpleTreeWriterImpl.java
	javac AVLTreeNode.java SimpleTreeWriter.java TreeUtils.java AVLTree.java TreeUI.java SimpleTreeWriterImpl.java
#complie test
compileTest: TestTreeUtils.java
	javac  -cp junit-4.11.jar:hamcrest-core-1.3.jar:. TestTreeUtils.java

runTest: compileTest
	java -cp junit-4.11.jar:hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore TestTreeUtils

run: compile
	cd ..; java pracTwo_task4/TreeUI
clean:
	@rm -f *.class
