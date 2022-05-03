import java.util.concurrent.locks.ReentrantLock;

public class Lucky {
    static int x = 0;
    static int count = 0;

    static class LuckyThread extends Thread {
        ReentrantLock locker;
        public LuckyThread(ReentrantLock locker){
            this.locker = locker;
        }
        @Override
        public void run() {
            while (x < 999999) {
                try{
                    locker.lock();
                    x+=1;
                    if ((x % 10) + (x / 10) % 10 + (x / 100) % 10 == (x / 1000)
                            % 10 + (x / 10000) % 10 + (x / 100000) % 10) {
                        System.out.println(x);
                        count++;
                    }
                }catch (Exception e){
                    System.out.println(e);
                }
                finally {
                    locker.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock locker = new ReentrantLock();
        Thread t1 = new LuckyThread(locker);
        Thread t2 = new LuckyThread(locker);
        Thread t3 = new LuckyThread(locker);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count);
    }
}
