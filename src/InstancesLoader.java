import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InstancesLoader {
    private String filename;

    public InstancesLoader(String filename) {
        this.filename = filename;
    }

    public Instance[] load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            int instancesCount = readNumber(reader);
            Instance[] instances = new Instance[instancesCount];
            int tasksCount;

            for(int instanceNumber=0; instanceNumber<instancesCount; instanceNumber++) {
                tasksCount = readNumber(reader);
                instances[instanceNumber] = readInstance(reader, tasksCount);
            }

            return instances;
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return new Instance[] {};
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    private int readNumber(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        line = line.replaceAll("\\s+","");
        return Integer.parseInt(line);
    }

    private Instance readInstance(BufferedReader reader, int tasksCount) throws IOException {
        String line;
        Instance instance = new Instance();
        for(int taskNumber=0; taskNumber<tasksCount; taskNumber++) {
            line = reader.readLine();
            String[] values = line.split("\\s+");
            instance.addTask(new Task(taskNumber, Integer.parseInt(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[3])));
        }
        return instance;
    }
}
