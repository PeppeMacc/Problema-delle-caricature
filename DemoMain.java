import java.util.Random;
import java.util.concurrent.Semaphore;

public class DemoMain 
{
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		final int NUM_CHAIRS = 4;
		final int NUM_OF_ACTIONS = 10;
	    Semaphore chairsSemaphore = new Semaphore(NUM_CHAIRS, true);
	    Semaphore completionMutex = new Semaphore(0, true);
	    Random random = new Random();

	    Artista artist=new Artista(chairsSemaphore,completionMutex,NUM_OF_ACTIONS);
	    artist.start();
	    
	    for (int i = 1; i <= NUM_OF_ACTIONS; i++) {
            Thread clientThread = new Thread(new Cliente(i,chairsSemaphore,completionMutex));
            clientThread.start();

            try {
                Thread.sleep(random.nextInt(2000)); // Intervallo di tempo casuale tra l'arrivo di un cliente e l'altro
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	}

}
