package Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Algorithm.AStarAlgorithm.Cell;
import Element.Node;
import Element.World;

public class MyAlgorithm {
	World world;
	List<Node> result = new ArrayList<Node>();
	List<Cell> resultList = new ArrayList<Cell>();
	long time=0;
	int nodeExpand = 0;
	public double totalCost = 0;
	public MyAlgorithm(World world){
		this.world = world;
	}
	class Cell{
		Cell parent;
		Node self;
		double h=0;
		double f=0;
		double g=0;
		public Cell(Node n,Cell parent){
			self =n;
			this.parent = parent;
		}
		public void setParent(Cell parent){
			this.parent =parent;
		}
		public Cell getParent(){
			return parent;
		}
		public Node getSelf(){
			return self;
		}
		public void setH(double h){
			this.h =h;
		}
		public void setG(double g){
			this.g =g;
		}
		public void setF(double f){
			this.f =f;
		}
		public double getH(){
			return h;
		}
		public double getG(){
			return g;
		}
		public double getF(){
			return f;
		}
		public String toString(){
			return "("+self.x+","+self.y+")";
		}
	}
	public static Comparator<Cell> cellComparator= new Comparator<Cell>(){
		public int compare(Cell o1, Cell o2) {
			double d= o1.f-o2.f;
			if (d>0) return 1;
			else if (d==0) return 0;
			else{
				return -1;
			}
		}		
	};
	List<Cell> open = new ArrayList<Cell>();
	List<Cell> closed = new ArrayList<Cell>();
	public List<Node> search(int startx,int starty,int targetx,int targety){
		result.clear();
		open.clear();
		closed.clear();
		nodeExpand = 0;
		time=0;
		Node start=null;
		Node target=null;
		for (Node n:world.getNodes()){
			if (n.x==startx&&n.y==starty){
				start = n;
			}
			if (n.x==targetx &&n.y==targety){
				target = n;
			}
			if (start!=null&&target!=null){
				break;
			}
		}
		if (start==null||target==null){
			return result;
		}
		List<Cell> resultList= astarSearch(start, target);
		if (resultList.size()==0){
			return result;
		}
		else{
			for (int i=0;i<resultList.size();i++){
				result.add(resultList.get(i).self);
			}
			return result;
		}
	}
	public List<Cell> astarSearch(Node start,Node target){
		long startTime = System.nanoTime();
		resultList.clear();
		boolean isFind= false;
		Cell current = null;
		Cell endcell = new Cell(target, null);
		Cell startcell = new Cell(start,null);
		open.add(startcell);
		while(open.size()>0){
			nodeExpand++;
			current=open.get(0);
			open.remove(0);
			if (current.getSelf()==target){
				isFind = true;
				totalCost = current.getG();
				break;//find target
			}
			for (Node n:current.getSelf().getNeighbours()){
				double cost = current.self.getDistance(n);//actual cost
				checkPath(n,current,endcell,cost);
			}
			closed.add(current);
			Collections.sort(open,cellComparator);
		}
		if (isFind){
			getPath(resultList,current);
		}
		long endTime = System.nanoTime();
		time =endTime-startTime;
		return resultList;
	}
	public boolean checkPath(Node n, Cell parentCell,Cell endCell,double cost){
		Cell cell= new Cell(n,parentCell);
		int index = isContains(cell, closed);
		if (index!=-1){
			return false;
		}
		index = isContains(cell, open);
		if (index!=-1){
		 if((parentCell.getG()+distanceToTime(cost)+waitTime(n,parentCell))< open.get(index).getG()){
             cell.setParent(parentCell);
             countG(n, cell, cost);
             countF(cell);
             
             open.set(index, cell);
         }
		}
		else{
			cell.setParent(parentCell);
			count(n,cell,endCell,cost);
			open.add(cell);
		}
		return true;
	}
	public int isContains(Cell cell, List<Cell> list){
		for (int i=0;i<list.size();i++){
			if (cell.self.equals(list.get(i).self)){
				return i;
			}
		}
		return -1;
	}
	private void count(Node n, Cell cell,Cell endCell, double cost){
		countG(n, cell, cost);
		countH(cell, endCell);
		countF(cell);
	}
	private void countG(Node n,Cell cell,double cost){
        if(cell.getParent()==null){
            cell.setG((distanceToTime(cost)));
        }else{
        	cell.setG(cell.getParent().getG()+waitTime(n,cell)+distanceToTime(cost));
        	}
    }
    //计算H值
    private void countH(Cell cell,Cell endCell){
    	double d = distanceToTime((Math.hypot((cell.self.x-endCell.self.x),(cell.self.y-endCell.self.y))));
        cell.setH(d);
    }
    //计算F值
    private void countF(Cell cell){
        cell.setF(cell.getG()+cell.getH());
    }
    private void getPath(List< Cell> resultList,Cell cell){//path from target to start
        if(cell.getParent()!=null){
            getPath(resultList, cell.getParent());
        }
        resultList.add(cell);
    }
    public long getTime(){
    	return time;
    }
    
    public boolean isRightTurn(Node start,Node last,Node end){
    	int vector1_x = last.x-start.x;
    	int vector1_y = last.y-start.y;
    	int vector2_x = end.x-last.x;
    	int vector2_y = end.y-last.y;
    	double sin = vector1_x * vector2_y - vector2_x * vector1_y;  
    	double cos = vector1_x * vector2_x + vector1_y * vector2_y; 
    	double angle1 = Math.atan2(vector1_x, vector1_y)* (180 / Math.PI);
    	double angle2 = Math.atan2(vector2_x, vector2_y)* (180 / Math.PI);
    	double angle= angle1-angle2;
    	if (angle>=-90&& angle<=-45){
    		return true;
    	}
    	else return false;
    }
    public double waitTime(Node n,Cell Parentcell){
    	int waitTime =0;
    	if (Parentcell==null||Parentcell.getParent()==null){//have not turned
    		return 0;
    	}
    	else{
    		if(!isRightTurn(Parentcell.getParent().self, Parentcell.self, n)){//not right turn
        		int g = (int)Math.ceil(Parentcell.getG());
        		if (g%2==1){// green light
        			return 0;
        		}
        		else{//red light
        			return g-Parentcell.getG();
        		}
    	}
    		else return 0;//right turn
    }
    }
    public double distanceToTime(double distance){
    	 return distance/60.0;
    }
    public double getCost(){
    	double cost = 0;
    	if (resultList.size()==0){
    		return cost;
    	}
    	else{
    		Cell first = resultList.get(0);
    		Cell second = resultList.get(1);
    		cost += distanceToTime(first.self.getDistance(second.self));
    		for (int index = 2;index<resultList.size();index++){
    			Cell third = resultList.get(index);
    			cost+=waitTime(third.self, second,cost);
    			cost+= distanceToTime(second.self.getDistance(third.self));
    			second = resultList.get(index);
    		}
    		return cost;
    	}
    }
    public double waitTime(Node n,Cell Parentcell,double cost){//use for performance
    	if (Parentcell==null||Parentcell.getParent()==null){//have not turned
    		return 0;
    	}
    	else{
    		if(!isRightTurn(Parentcell.getParent().self, Parentcell.self, n)){//not right turn
        		int g = (int)Math.ceil(cost);
        		if (g%2==1){// green light
        			return 0;
        		}
        		else{//red light
        			return g-cost;
        		}
    	}
    		else return 0;//right turn
    }
    }
    public String getPerformance(){
    	return "Myalgorithm(RunningTime:"+time+"   Totalcost:"+getCost()+"   NodeExpand:"+nodeExpand+")";
    }
}
