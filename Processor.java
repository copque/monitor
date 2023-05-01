package monitor;

import java.util.Scanner;

public class Processor{

    public void produce() throws InterruptedException{
        synchronized (this){                                            // esse metodo nao executa enquanto nao adquirir o lock intrinseco de Processor
            System.out.println("Thread produtora executando...");
            wait();                                                     // essa thread "largar" o lock de Processor, aguardando ate: 1) outra thread usar 
                                                                        // notify() no mesmo objeto para acorda-la, e 2) reobter o lock, ou seja,
                                                                        // a outra thread finalize seu bloco synchronized
            System.out.println("Thread produtora continuacao.");
        }
    }

    public void consume() throws InterruptedException{

        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);                                       // gambiarra pro produce() ir primeiro

        synchronized (this){
            System.out.println("Aguardando press. enter");
            scanner.nextLine();
            System.out.println("Enter foi pressionado");
            notify();                                                   // acorda a outra thread travada no objeto Processor
            Thread.sleep(2500);
            System.out.println("ainda estou aqui");                   // como dito, ao contrario de wait, notify nao libera imediatamente a trava
            Thread.sleep(2500);
        }
    }
}
