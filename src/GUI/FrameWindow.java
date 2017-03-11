package GUI;
import java.awt.Color;

import javax.swing.*;

import Element.World;
public class FrameWindow extends JFrame{
	PaintingScreen ps;
	public FrameWindow(int width, int height , String name, World world) {
		super();
		setBounds(0, 0, width, height);
		setTitle(name);
		ps = new PaintingScreen(width, height, world);
		this.add(ps);
		setVisible(true);
	}
	public PaintingScreen getScreen(){
		return ps;
	}
}
