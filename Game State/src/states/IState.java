package states;

import executables.StateManager;

public interface IState {
	public void setup(StateManager m);
	public void activate();
	public void deactivate();
}
