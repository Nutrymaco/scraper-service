public class Test {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Service start");
        while (true) {
            System.out.println("Do some work...");
            Thread.sleep(10_000);
        }
    }
}
