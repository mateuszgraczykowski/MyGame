package ThreadManager;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PooledThread extends Thread {
	
	private static ThreadID threadID=new ThreadID(1);
	
	private ThreadPool threadPool;
	
	public PooledThread(ThreadPool threadPool) {
		super(threadPool, "PooledThread: "+ threadID.nextID());
		this.threadPool=threadPool;
	}
	
	@Override
	public void run() {
		Runnable task=null;
		try {
			task=threadPool.getTask();
		} catch (InterruptedException e) {
			Logger.getLogger(PooledThread.class.getName()).log(Level.SEVERE, null, e);
		}
		
		if(task==null) return;
		
		try {
			task.run();
		} catch (Throwable t) {
			threadPool.uncaughtException(this, t);
		}
	}
	
}
