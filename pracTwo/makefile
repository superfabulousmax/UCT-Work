#My make file
JC=javac
#JFLAGS=-g
default: TreeUtils.class

AVLTreeNode.class: AVLTreeNode.java
	$(JC) AVLTreeNode.java
#when it gets to AVLTreeNode.class it will call that rule
TreeUtils.class: TreeUtils.java AVLTreeNode.class
	$(JC) TreeUtils.java
AVLTree.class: AVLTree.java TreeUtils.class
clean:
	@rm -f *.class
