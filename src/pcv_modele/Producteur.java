package pcv_modele;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Producteur implements Runnable
{
	CyclicBarrier barrier;
	private final int NUM_PRO=10;
	private int rate;
	
	public Producteur(CyclicBarrier barrier, int rate)
	{
		this.barrier=barrier;
		this.rate=rate;
	}

	public void run() 
	{
		int i;
		for(i=0;i<NUM_PRO;i++)
		{
			try
			{
				Thread.sleep(this.rate*500);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}	
		}
		
		try 
		{
			System.out.println("Producteur "+Thread.currentThread().getId()+" est attendu");
			System.out.println((barrier.getNumberWaiting()+1)+" thread est attendu");
			this.barrier.await();
		} 
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		catch (BrokenBarrierException e) 
		{
			e.printStackTrace();
		}
		
		System.out.println("Producteur "+Thread.currentThread().getId()+" est r¨¦veil");
	}
}