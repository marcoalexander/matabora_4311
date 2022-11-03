import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.*;


public class BreadthFirstSearch {
	
	public void traverse(Vertex root){
		
		Queue<Vertex> queue = new LinkedList<>();

		root.setVisited(true);
		queue.add(root);

		while (!queue.isEmpty()) {
			Vertex actualVertex = queue.remove();

			System.out.println("Actual visited vertex: " + actualVertex.getName());
            Map<Vertex,Integer> map = actualVertex.getNeighbors();
            Set<Vertex> v = map.keySet();
			for (Vertex name : v) {
				if (!name.isVisited()) {
					name.setVisited(true);
					queue.add(name);
				}
			}
		}
	}


}