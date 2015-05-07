JFLAGS =-g
JC=javac

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	ChainedEntry.java \
	ChainedHashEntry.java \
	Definition.java \
	Dictionary.java \
	Entry.java \
	FileUtil.java \
	HashEntry.java \
	HashTableLoadPerformance.java \
	HashTableSearchPerformance.java \
	LPHashtable.java \
	QPHashtable.java \
	SCHashtable.java \
	UserInterface.java \
	WordType.java

default: classes

classes: $(CLASSES:.java=.class)

#complie test
compileTest: TestHashTables.java
	javac  -cp junit-4.11.jar:hamcrest-core-1.3.jar:. TestHashTables.java

runTest: compileTest
	java -cp junit-4.11.jar:hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore TestHashTables
#run program
compileInterface: UserInterface.java
	 javac UserInterface.java

run: compileInterface
	java UserInterface
clean:
	$(RM) *.class

