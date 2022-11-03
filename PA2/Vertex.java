import java.util.*;

public class Vertex {
    final private String name;

    private boolean visited;
    private Map<Vertex,Integer> neighbors;
    //added later
    private Integer distance = Integer.MAX_VALUE;
    //private LinkedList<Vertex> shortestPath = new LinkedList<>();
    //

    public Vertex(String name) {
        this.name = name;
        this.neighbors = new HashMap<Vertex,Integer>();
    }

    public String getName() {
        return name;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited){
        this.visited = visited;
    }

    public Map<Vertex,Integer> getNeighbors(){
        return neighbors;
    }

    public void addNeighbor(Vertex vertex,int weight){
        neighbors.put(vertex,weight);
        //this.neighbors.add(map);

    }

}