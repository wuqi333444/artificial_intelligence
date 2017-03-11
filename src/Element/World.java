package Element;

import java.util.ArrayList;
import java.util.List;

public class World {
	int minx = Integer.MAX_VALUE;
	int miny = Integer.MAX_VALUE;
	int maxx = Integer.MIN_VALUE;
	int maxy = Integer.MIN_VALUE;
	List<Node> nodelist = new ArrayList<Node>();
	List<Edge> edgelist = new ArrayList<Edge>();
	public List<Node> getNodes(){
		return nodelist;
	}
	public List<Edge> getEdges(){
		return edgelist;
	}
}
