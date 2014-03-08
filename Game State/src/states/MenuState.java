package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import executables.StateManager;

public class MenuState extends State implements ActionListener{
	private boolean running = true;
	int x = 0;
	@Override
	public void activate(){
		running = true;
		this.add(btn);
		btn.setActionCommand("Loop");
		btn.addActionListener(this);
		
		
		
		while(running){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			x++;
			this.repaint();
			System.out.println("repaint called");
		}
	}
	public void deactivate(){
		btn.removeActionListener(this);
		running = false;
		
	}
	
	@Override
	public void setup(StateManager m){
		parent = m;
	}
	StateManager parent = null;
	JButton btn = new JButton("to Loop");
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
		g2d.setColor(new Color(0.0f,1.0f,1.0f));
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		System.out.println("Paint Component");
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if("Loop".equals(arg0.getActionCommand())){
			parent.switchState("loop");
		}
		
	}

}
