package pcv_modele;

import java.util.concurrent.CyclicBarrier;

public class Main 
{
	public static final int NUM_DE_PRODUCTEUR=3;
	
	public static void main(String[] args)
	{
		Tampon t=new Tampon();
		
		CyclicBarrier barrier=new CyclicBarrier(NUM_DE_PRODUCTEUR, new Runnable()
		{
			public void run()
			{
				System.out.println("Consommateur peut consommer maintenant!");
				new Thread(new Consommateur(t)).start();
				new Thread(new Consommateur(t)).start();
			}
		});
		
		new Thread(new Producteur(t, barrier)).start();
		new Thread(new Producteur(t, barrier)).start();
		new Thread(new Producteur(t, barrier)).start();
	}
}