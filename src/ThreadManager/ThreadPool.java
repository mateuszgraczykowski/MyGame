package ThreadManager;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool extends ThreadGroup {
	
	private static ThreadID poolID= new ThreadID(1);
	
	private boolean isRunning;
	private List<Runnable> taskQueue;
	private int id;
	
	
	public ThreadPool(int numberOfTreads) {
		super("ThreadPool:"+poolID.nextID());
		this.id=poolID.getCurrentID();
		setDaemon(true);
		taskQueue=new LinkedList<Runnable>();
		isRunning=true;
		for(int i=0; i<numberOfTreads; ++i) {
			new PooledThread(this).start();
		}
	}
	
	public synchronized void runTask(Runnable task) {
		if(task!=null) {
			taskQueue.add(task);
			notify();
		}
	}
	
	public synchronized void close() {
		if(!isRunning) return;
		isRunning=false;
		taskQueue.clear();
		interrupt();
	}
	
	public void join() {
		synchronized (this) {
			isRunning=false;
			notifyAll();
		}
		
		Thread[] threads=new Thread[activeCount()];
		int count=enumerate(threads);
		
		for(int i=0; i<count; ++i) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected synchronized Runnable getTask() throws InterruptedException {
		while(taskQueue.size()==0) {
			if(!isRunning) return null;
			wait();
		}
		return taskQueue.remove(0);
	}

}
