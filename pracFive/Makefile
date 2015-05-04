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

testperformance:
clean:
	$(RM) *.class

