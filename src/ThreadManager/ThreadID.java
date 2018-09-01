package ThreadManager;


public class ThreadID {
	
	private int id;
	
	ThreadID(int id){
		this.id=id;
	}
	
	public int nextID() {
		return id++;
	}
	
	public int getCurrentID() {
		return id;
	}
}
