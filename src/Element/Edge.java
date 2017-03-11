package Element;

public class Edge {
	public Node start;
	public Node end;
	public double weight;
	private boolean isBoth = false;
	public Edge(Node start,Node end){
		this.start = start;
		this.end = end;
		this.weight = start.getDistance(end);
	}
	public void setBoth(){
		isBoth = true;
	}
	public boolean isBoth(){
		return isBoth;
	}
	
//	@Override
//	public int hashCode() {
//		return this.start.x+10000*(this.start.y+10000*(this.end.x+10000*this.end.y));
//	}
}
