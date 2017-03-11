package GUI.Function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import Element.Edge;
import Element.Node;


public class ReadMap {
	File file = new File("d:/program/AI_Project/map.txt");
	FileReader fileReader;
	BufferedReader bufferedReader;
	public ReadMap(List<Edge> edges,List<Node> nodes) throws FileNotFoundException{
		fileReader = new FileReader(file);
		bufferedReader = new BufferedReader(fileReader);
		String line;
		try {
			line = bufferedReader.readLine();
			while (line!=null){
				StringTokenizer st = new StringTokenizer(line, " ");
				String isBoth = st.nextToken();
				String firstx = st.nextToken();
				String firsty = st.nextToken();
				String secondx = st.nextToken();
				String secondy = st.nextToken();
				int x1= Integer.parseInt(firstx);
				int x2= Integer.parseInt(secondx);
				int y1= Integer.parseInt(firsty);
				int y2= Integer.parseInt(secondy);
				int b = Integer.parseInt(isBoth);
				Node first = new Node(x1,y1);
				if (!nodes.contains(first)){
					nodes.add(first);
				}
				else{
					first = nodes.get(nodes.indexOf(first));
				}
				Node second = new Node(x2,y2);
				if (!nodes.contains(second)){
					nodes.add(second);
				}
				else{
					second = nodes.get(nodes.indexOf(second));
				}
				Edge e1= new Edge(first,second);
				edges.add(e1);
				first.addNeighbour(second);
				if (b==2){
				Edge e2 = new Edge(second,first);
				edges.add(e2);
				e1.setBoth();
				e2.setBoth();
				second.addNeighbour(first);
				}
				line = bufferedReader.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
