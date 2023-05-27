import java.util.Random;
import java.util.concurrent.Semaphore;

public class Artista extends Thread
{
	private static final int MAX_EXECUTION_TIME = 5; 
	private static Semaphore chairsSemaphore;
	private Semaphore completionMutex;
	private static int NUM_OF_ACTIONS;
	
	public Artista(Semaphore chairs, Semaphore completion, int NUM_OF_ACTIONS) {
		this.chairsSemaphore=chairs;
		this.completionMutex=completion;
		this.NUM_OF_ACTIONS = NUM_OF_ACTIONS;
	}
	
	@Override
	public void run() {
		for(int i=1;i<=NUM_OF_ACTIONS;i++) //Attenzione l'artista va in starvation perché i Clienti dopo un tot di tempo se ne vanno e lui continuerà lo stesso
		{
			try {
				chairsSemaphore.acquire();
				System.out.println("L'artista inizia a disegnare");
				
				int tempoDiLavoro = new Random().nextInt(MAX_EXECUTION_TIME); 
				Thread.sleep(tempoDiLavoro*1000);
				
				System.out.println("Disegno Fatto");
				completionMutex.release();
				chairsSemaphore.release();
				
			}catch(InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
	}

}
