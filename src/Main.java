public class Main {
    public static void main(String[] args) {
        String instancesPath = "instances/";
        double h = 0.4;
        Instance[] instances = new InstancesLoader(instancesPath + "sch100.txt").load();
        long startTime;
        long timeElapsed;
        for(int i=0; i<instances.length; i++) {
            startTime = System.nanoTime();
            instances[i].schedule(h);
            timeElapsed = System.nanoTime() - startTime;
            System.out.println(timeElapsed / 1000000.0);
            instances[i].saveToFile(i, h);
        }

//        if(Tester.test(instancesPath + "sch10.txt", "results/n10k3h2.txt"))
//            System.out.println("Ok");
//        else
//            System.out.println("!OK");
    }
}
