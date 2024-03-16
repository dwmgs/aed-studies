package aed;

public interface Queue {
	
	public void offer(int element);
	
	public int peek();
	
	public int poll();
		
	public int getSize();
	
}
