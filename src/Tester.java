import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tester {
    public static boolean test(String instanceFile, String outputFile) {
        int n = Integer.parseInt(outputFile.substring(outputFile.indexOf("n") + 1, outputFile.indexOf("k")));
        int k = Integer.parseInt(outputFile.substring(outputFile.indexOf("k") + 1, outputFile.indexOf("h")));
        double h = Double.parseDouble(outputFile.substring(outputFile.indexOf("h") + 1, outputFile.indexOf("."))) / 10;

        Task tasks[] = new InstancesLoader(instanceFile).load()[k - 1].getTasks();
        String[] file = loadTestingFile(outputFile).split("\\s");

        if(h != Double.parseDouble(file[1]))
            return false;

        if(file.length - 3 != n)
            return false;

        Instance testingInstance = new Instance();
        for(int i=3; i<file.length; i++) {
            testingInstance.addTask(tasks[Integer.parseInt(file[i])]);
        }
        testingInstance.setStartTime(Integer.parseInt(file[2]));

        if(Integer.parseInt(file[0]) != testingInstance.computeGoalFunction(h, (int) Math.floor(testingInstance.sumProcessingTime() * h)))
            return false;

        return true;
    }

    private static String loadTestingFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return reader.readLine();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
