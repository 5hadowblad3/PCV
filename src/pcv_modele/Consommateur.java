package pcv_modele;

public class Consommateur implements Runnable
{
	Tampon t;
	int num_con;

	public Consommateur(Tampon t)
	{
		this.t=t;
		this.num_con=0;
	}

	public void run() 
	{
		while(true)
		{
			synchronized(t)
			{
				if(t.getNb()==0)
					break;
				this.num_con++;
				t.setNb(t.getNb()-1);
				System.out.println("Consommateur "+Thread.currentThread().getId()+" consomme "+this.num_con+", Nombre de produit maintenant "+t.getNb());
			}
		}
	}
}