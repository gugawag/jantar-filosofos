/**
 * Original: http://www.baeldung.com/java-dining-philoshophers
 * Pequenas alterações: Gustavo Wagner, gwmendes@trt13.jus.br
 * Criada em: 30/08/2017
 */

public class Philosopher implements Runnable {

    // The forks on either side of this Philosopher
    private Object leftFork;
    private Object rightFork;

    public Philosopher(Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(
                Thread.currentThread().getName() + " " + action);
        /*TODO-1 descomente a linha abaixo para que o deadlock ocorra mais tardiamente.
           Execute o código, depois de descomentado, várias vezes. Em algum momento também ocorrerá o deadlock.
           O código abaixo faz o filosófo aguardar um tempo aleatório para fazer a ação. */

//        Thread.sleep(((int) (Math.random() * 100)));
    }

    @Override
    public void run() {
        try {
            while (true) {

                // thinking
                doAction(System.nanoTime() + ": Thinking");
                synchronized (leftFork) {
                    doAction(
                            System.nanoTime()
                                    + ": Picked up left fork");
                    synchronized (rightFork) {
                        // eating
                        doAction(
                                System.nanoTime()
                                        + ": Picked up right fork - eating");

                        doAction(
                                System.nanoTime()
                                        + ": Put down right fork");
                    }

                    // Back to thinking
                    doAction(
                            System.nanoTime()
                                    + ": Put down left fork. Back to thinking");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
}