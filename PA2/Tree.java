import java.util.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class Tree {
    public static void main(String[] args) {

        //Building First Tree
        Vertex a1 = new Vertex("1");
        Vertex b2 = new Vertex("2");
        Vertex c3 = new Vertex("3");
        Vertex d4 = new Vertex("4");
        /*
        Vertex e1 = new Vertex("e");
        Vertex f1 = new Vertex("f");
        Vertex g1 = new Vertex("g");
        Vertex h1 = new Vertex("h");
        */
        a1.addNeighbor(c1,1);
        c1.addNeighbor(b1,0);
        c1.addNeighbor(d1,0);
        d1.addNeighbor(b1,0);
        /*
        b1.addNeighbor(h1,0);
        b1.addNeighbor(d1,0);
        d1.addNeighbor(f1,0);
        */
        //BFS
        BreadthFirstSearch bfs=new BreadthFirstSearch();
        long start1 = System.nanoTime();
        bfs.traverse(a1); 
        long end1 = System.nanoTime();
        
        NumberFormat formatter = new DecimalFormat("#0.0000");
        System.out.print("Execution time is " + formatter.format((end1 - start1) / 1000000d) +" "+ " milliseconds");

        System.out.println();
        
    }
}