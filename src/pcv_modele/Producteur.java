package pcv_modele;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Producteur implements Runnable
{
	Tampon t;
	CyclicBarrier barrier;
	private int num_pro;
	private final int NUM_PRO=10;
	
	public Producteur(Tampon t, CyclicBarrier barrier)
	{
		this.t=t;
		this.barrier=barrier;
		this.num_pro=0;
	}

	public void run() 
	{
		int i;
		for(i=0;i<NUM_PRO;i++)
		{
			synchronized(t)
			{
				this.num_pro++;
				t.setNb(t.getNb()+1);
				System.out.println("Producteur "+Thread.currentThread().getId()+" produit "+this.num_pro+", Nombre de produit maintenant "+t.getNb());
			}
		}
		
		try 
		{
			System.out.println("Producteur "+Thread.currentThread().getId()+" est attendu");
			barrier.await();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		catch (BrokenBarrierException e) 
		{
			e.printStackTrace();
		}
	}
}