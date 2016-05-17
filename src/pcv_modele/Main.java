package pcv_modele;

import java.util.concurrent.CyclicBarrier;

public class Main 
{
	public static final int NUM_DE_PRODUCTEURS=3;
	
	public static void main(String[] args)
	{	
		CyclicBarrier barrier=new CyclicBarrier(NUM_DE_PRODUCTEURS, new Runnable()
		{
			public void run()
			{
				System.out.println("Tous les producteurs arrivent bien!");
			}
		});
		
		new Thread(new Producteur(barrier,1)).start();
		new Thread(new Producteur(barrier,2)).start();
		new Thread(new Producteur(barrier,3)).start();
	}
}