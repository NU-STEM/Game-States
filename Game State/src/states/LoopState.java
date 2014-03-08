package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel; 

import executables.StateManager;

public class LoopState extends State implements ActionListener {
	boolean running = true;
	JButton btn = new JButton("Button");
	StateManager parent = null;
	int x =0;
	
	@Override
	public void activate(){
		running = true;
		btn.addActionListener(this);
		int LogicHertz = 30;
		int RenderHertz = 60;
		int Time_Between_Logic = 1000000000/LogicHertz;
		int Time_Between_Render = 1000000000/RenderHertz;
		long lastRenderTime = System.nanoTime();
		long lastLogicTime = System.nanoTime();
		while(running){
			logicUpdate();
			lastLogicTime = System.nanoTime();
			while((System.nanoTime()-lastLogicTime) < Time_Between_Logic){
				double interpolation = (System.nanoTime() -lastLogicTime)/ Time_Between_Logic;
				renderUpdate(interpolation);
				
				lastRenderTime = System.nanoTime();
				while((System.nanoTime()-lastRenderTime) < Time_Between_Render){
					//Thread.yield();
				}
			}
		}
		
	}
	
	@Override
	public void deactivate(){
		this.running = false;
		btn.removeActionListener(this);
	}
	
	@Override
	public void setup(StateManager m){
		parent = m;
		btn.setActionCommand("Button");
		btn.setSize(100, 200);
		this.add(btn);
	}
	
	
	public void renderUpdate(double Interpolation){
		this.repaint();
	}
	public void logicUpdate(){
		x++;
		if(x> this.getWidth()){
			x=0;
		}
	}
	@Override
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(new Color(0.5f,0.0f,0.5f));
		g2d.fillRect(x, 0, 20, 20);
		System.out.println("Paint Called");
	}
	public void paintComponent(Graphics g){
		//super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(new Color(0.0f,0.5f,0.5f));
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		System.out.println("Paint Component");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Button")){
			parent.switchState("mainmenu");
		}
		
	}
}
