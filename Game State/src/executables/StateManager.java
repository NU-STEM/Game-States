package executables;

import javax.swing.JFrame;

import states.LoopState;
import states.MenuState;
import states.State;

public class StateManager extends JFrame {
	LoopState loop = null;
	State currentState = null;
	State mainMenu = null;
	
	public StateManager(){
		this.setSize(500, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void activate(){
		loop = new LoopState();
		mainMenu = new MenuState();
		mainMenu.setup(this);
		mainMenu.setIgnoreRepaint(true);
		loop.setIgnoreRepaint(true);
		this.setIgnoreRepaint(true);
		currentState = loop;
		this.add(currentState);
		currentState.setup(this);
		this.paintComponents(this.getGraphics());
		
		while(true){	
			currentState.activate();
			System.out.println("main Finished");
		}
		
		
		
	}
	public void switchState(String state){
		
		if (state.equals("loop")){
			State lastState =currentState;
			
			this.remove(lastState);
			currentState = loop;
			this.add(currentState);
			lastState.deactivate();
			this.repaint();
			this.repaint();
			this.paintComponents(this.getGraphics());
			//currentState.activate();
			
		}
		if(state.equals("mainmenu")){
			System.out.println("change called");
			State lastState =currentState;
			this.remove(lastState);
			currentState = mainMenu;
			this.add(currentState);
			currentState.setVisible(true);
			//mainMenu.activate();
			//mainMenu.repaint(0);
			lastState.deactivate();
			this.repaint();
			this.paintComponents(this.getGraphics());
			
			
		}
	}
}
