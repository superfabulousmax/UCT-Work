#My make file
JC=javac
#JFLAGS=-g

#compile normal
compile: AVLTreeNode.java SimpleTreeWriter.java TreeUtils.java AVLTree.java TreeUI.java SimpleTreeWriterImpl.java
	javac AVLTreeNode.java TreeUtils.java SimpleTreeWriter.java AVLTree.java TreeUI.java SimpleTreeWriterImpl.java
#complie test
compileTest: TestTreeUtils.java
	javac  -cp junit-4.11.jar:hamcrest-core-1.3.jar:. TestTreeUtils.java

runTest: compileTest
	java -cp junit-4.11.jar:hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore TestTreeUtils

run: compile
	java TreeUI

clean:
	@rm -f *.class
