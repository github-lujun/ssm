import java.util.concurrent.TimeUnit;

public class MyThread {
    public static void main(String[] args) throws InterruptedException {
        //线程的控制
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(this.getState());
                        this.notifyAll();
                        Thread.yield();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        thread.setPriority(Thread.MAX_PRIORITY);
        thread.setDaemon(true);
        thread.start();
        thread.wait();
        thread.join();
        thread.interrupt();
    }
}
