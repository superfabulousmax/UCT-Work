package pracTwo_task4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
/**
 * Write a description of class AVLTreeNodePrintStream here.
 * 
 * @author Sinead Urisohn 
 * @version 16/03/2015
 */
public class SimpleTreeWriterImpl implements SimpleTreeWriter {

    private PrintStream output;
    
    public SimpleTreeWriterImpl(PrintStream output) {
        this.setDestination(output);
    }

    public void setDestination(PrintStream output) {
        this.output=output;
    }
    
    public void write(AVLTreeNode node) {
        if (node==null) {
            output.println();
        }
        else {
            List<AVLTreeNode> level = TreeUtils.levelZero(node);
            final int labelSize = node.getLargest();
   
            for(int levelNum=0; levelNum<node.getHeight(); levelNum++) {
                final int nodeSpacing = powerOf2(node.getHeight()-levelNum)-1;
                writeLevel(nodeSpacing, labelSize, level);
                level = TreeUtils.nextLevel(level);
            }
        }
    }
    
    
    
        
        private void writeLevel(final int nodeSpacing, final int labelWidth, final List<AVLTreeNode> level) {
            int height = 0;
            String leadingSpace = makeSpacing((nodeSpacing/2)*labelWidth);
            String interNodeSpace = makeSpacing(nodeSpacing*labelWidth);

            final List<Scanner> raster = new ArrayList<Scanner>();
            for (AVLTreeNode node : level) {
                String label = node.toString();
                height = Math.max(height, label.split("\n").length);
                raster.add(new Scanner(node.toString()));
            }

            while (height>0) {
                Iterator<Scanner> iterator = raster.iterator();
                
                output.print(leadingSpace);
                writeNode(iterator.next(), labelWidth);
                
                while (iterator.hasNext()) {
                    output.print(interNodeSpace);
                    writeNode(iterator.next(), labelWidth);
                }
                output.println();
                height--;                
        }
       
            
    }
        
        
    
    private void writeNode(Scanner scanner, int labelWidth) {
        final String blankNode = makeSpacing(labelWidth);
        if (!scanner.hasNextLine()) {
            output.printf(blankNode);
        }
        else {
            output.printf("%-"+labelWidth+"s", scanner.nextLine());
        }        
    }

    private static int powerOf2(int power) {
        if (power==0) {
            return 1;
        }
        else {
            return 2*powerOf2(power-1);
        }
    }
        
    private static String makeSpacing(int size) {
        StringBuilder builder = new StringBuilder(size);
        while(size>0) {
            builder.append(' ');
            size--;
        }
        return builder.toString();
    }

}
