package MainMethod;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import Algorithm.AStarAlgorithm;
import Algorithm.AStarAlgorithmWithDirectDistance;
import Algorithm.MyAlgorithm;
import Algorithm.MyAlgorithmWithManhattan;
import Element.Edge;
import Element.Node;
import Element.World;
import GUI.FrameWindow;
import GUI.Function.ReadMap;

public class ActivateMethod {
	public static void main(String[] args) {
		World world = new World();
		try {
			ReadMap rm = new ReadMap(world.getEdges(), world.getNodes());
			FrameWindow window = new FrameWindow(1000, 1000, "Map", world);
			window.getScreen().update();
			AStarAlgorithm astar = new AStarAlgorithm(world);
			MyAlgorithm ma = new MyAlgorithm(world);
			AStarAlgorithmWithDirectDistance aawdd = new AStarAlgorithmWithDirectDistance(world);
			MyAlgorithmWithManhattan mawm = new MyAlgorithmWithManhattan(world);
			System.out.println(astar.search(1121,7568,1455,7920));
			System.out.println(ma.search(1121,7568,1455,7920));
			System.out.println(mawm.search(1121,7568,1455,7920));
			System.out.println(aawdd.search(1121,7568,1455,7920));
			window.getScreen().path= astar.search(1121,7568,1455,7920);
			window.getScreen().update();
			System.out.println(astar.getPerformance());
			System.out.println(ma.getPerformance());
			System.out.println(mawm.getPerformance());
			System.out.println(aawdd.getPerformance());
			//test1
			System.out.println(astar.search(1121,7568,2179,8595));
			System.out.println(ma.search(1121,7568,2179,8595));
			System.out.println(mawm.search(1121,7568,2179,8595));
			System.out.println(aawdd.search(1121,7568,2179,8595));
			window.getScreen().path= astar.search(1121,7568,2179,8595);
			window.getScreen().update();
			System.out.println(astar.getPerformance());
			System.out.println(ma.getPerformance());
			System.out.println(mawm.getPerformance());
			System.out.println(aawdd.getPerformance());
			//test2
			System.out.println(astar.search(2197,8966,1961,7800));
			System.out.println(ma.search(2197,8966,1961,7800));
			System.out.println(mawm.search(2197,8966,1961,7800));
			System.out.println(aawdd.search(2197,8966,1961,7800));
			window.getScreen().path= ma.search(2197,8966,1961,7800);
			window.getScreen().update();
			System.out.println(astar.getPerformance());
			System.out.println(ma.getPerformance());
			System.out.println(mawm.getPerformance());
			System.out.println(aawdd.getPerformance());
			//test3
			//test4
//			double mean = 0;
//			for(Edge e:world.getEdges()){
//				mean+=e.weight;
//			}
//			System.out.println(mean/world.getEdges().size()/60.0);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
