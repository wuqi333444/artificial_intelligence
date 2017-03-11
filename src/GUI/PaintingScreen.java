package GUI;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;




import Element.Edge;
import Element.Node;
import Element.World;

public class PaintingScreen extends Canvas {
	private World world;
	private final int minX=-1500;
	private final int minY=4000;
	private double scale = 7;//PAY ATTENTION TO SCALE
	public  List<Node> path=null;
	public PaintingScreen(int width, int height, World world){
		super();
		setBounds(0, 0, width, height);
		setVisible(true);
		setBackground(Color.WHITE);
		this.world = world;
	}
	
	
	public void update(){
		update(getGraphics());
		drawEdges(world.getEdges(),getGraphics());
		if (path!=null){
			drawPath(path, getGraphics());
		}
	}
	
	public int[] coordinate(Edge e){
		int[] a = new int[4];
		a[0] = (int)((e.start.x-minX)/scale);
		a[1] = (int)((e.start.y-minY)/scale);
		a[2] = (int)((e.end.x-minX)/scale);
		a[3] = (int)((e.end.y-minY)/scale);
		return a;
	}
	public int[] coordinate(Node start,Node end){
		int[] a = new int[4];
		a[0] = (int)((start.x-minX)/scale);
		a[1] = (int)((start.y-minY)/scale);
		a[2] = (int)((end.x-minX)/scale);
		a[3] = (int)((end.y-minY)/scale);
		return a;
	}
	
	public void drawPath(List<Node> path, Graphics g){
		if (path!=null&& path.size()>0){
			Node start = path.get(0);
			for (int i=1;i<path.size();i++){
				Node end = path.get(i);
				drawEdge(start,end, g);
				start = path.get(i);
			}
		}
	}
	public void drawEdge(Node start,Node end, Graphics g){
		int[] a = coordinate(start, end);
			g.setColor(Color.RED);
		g.drawLine(a[0], a[1], a[2], a[3]);
	}
	public void drawEdge(Edge e, Graphics g){
		int[] a = coordinate(e);
		if (e.isBoth()){
			g.setColor(Color.black);
		}
		else{
			g.setColor(Color.green);
		}
		g.drawLine(a[0], a[1], a[2], a[3]);
	}
	
	public void drawEdges(List<Edge> edges, Graphics g){
		for (int i=0;i<edges.size();i++){
			drawEdge(edges.get(i), g);
		}
	}
	

	
	private void drawPolygonWithScale(Polygon polygon,Graphics g,Color lineColor,Color fillColor,String s,double x,double y){
		int[] xs =new int[polygon.npoints];
		int[] ys = new int[polygon.npoints];
		for (int i = 0;i<polygon.npoints;i++){
			xs[i] = (int)scale*polygon.xpoints[i];
			ys[i] = (int)scale*polygon.ypoints[i];
		}
		g.setColor(fillColor);
		g.fillPolygon(xs, ys, polygon.npoints);
		g.setColor(lineColor);
		g.drawPolygon(xs, ys, polygon.npoints);
		g.drawString(s, (int)(scale*x), (int)(scale*y));
	}
}
