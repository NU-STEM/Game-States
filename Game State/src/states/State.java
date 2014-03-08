package states;

import javax.swing.JPanel;

import executables.StateManager;

public class State extends JPanel implements IState {
	StateManager parent = null;
	@Override
	public void setup(StateManager m) {
		parent = m;
		
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		
	}
	
	
}
