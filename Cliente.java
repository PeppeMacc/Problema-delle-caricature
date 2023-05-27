import java.util.Random;
import java.util.concurrent.Semaphore;

public class Cliente extends Thread
{
	private int IDCliente;
	private static Semaphore chairsSemaphore;
	private Semaphore completionMutex;
	
	public Cliente(int id, Semaphore chairs, Semaphore completion) {
		this.IDCliente=id;
		this.chairsSemaphore=chairs;
		this.completionMutex=completion;
	}

	@Override
	public void run() {
		System.out.println("Il cliente: "+this.IDCliente+" è appena arrivato");
		
		try {
			if(chairsSemaphore.tryAcquire()) {
				System.out.println("Il cliente  "+this.IDCliente+" si siede");
				
				completionMutex.acquire();
				System.out.println("Il cliente "+this.IDCliente+" ha completato il ritratto");
				chairsSemaphore.release();
				
			}
			else {
				System.out.println("Cliente: "+this.IDCliente+" se ne va");
			}
		}catch(InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
}
