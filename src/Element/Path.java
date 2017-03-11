package Element;

import java.util.LinkedList;
import java.util.List;

public class Path {
	List<Node> path = new LinkedList<Node>();
	public void addNode(Node n){
		path.add(n);
	}
	
}
