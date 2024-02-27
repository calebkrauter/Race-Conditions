public class Multithreads {
    public static void main(String[] args) {
        CurThread val1 = new CurThread();
        val1.setStart(0);
        CurThread val2 = new CurThread();
        val2.setStart(2);
        CurThread val3 = new CurThread();
        val3.setStart(4);
        CurThread val4 = new CurThread();
        val4.setStart(6);
        
        val1.start(); // Should output
        val2.start();
        val3.start();
        val4.start();

    }
    
}