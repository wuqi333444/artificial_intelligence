package Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Node {
	public int x;
	public int y;
	private Map<Node,Edge> neighbours= new HashMap<Node,Edge>(); 
	public Node(int x,int y){
		this.x = x;
		this.y = y;
	}
	public double getDistance(Node a){
		return Math.sqrt((a.x-x)*(a.x-x)+(a.y-y)*(a.y-y));
	}
	public void addNeighbour(Node n){
		neighbours.put(n, new Edge(this, n));
	}
	public void addNeighbour(Node n,Edge e){
		neighbours.put(n, e);
	}
	public void addEdge(Edge e){
		addNeighbour(e.end, e);
	}
	public Set<Node> getNeighbours(){
		return neighbours.keySet();
	}
	public boolean equals(Object node){
		if (node instanceof Node){
			Node n = (Node) node; 
		if (n!=null && n.x==this.x && n.y==this.y){
			return true;
		}
		else{
			return false;
		}
		}
		else{
			return false;
		}
	}
	@Override
	public int hashCode() {
		
		return this.x+10000*this.y;
	}
	public String toString(){
		return "("+x+","+y+")";
	}
}
