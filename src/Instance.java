import java.io.*;
import java.util.*;

public class Instance {
    private ArrayList<Task> tasks;
    private int goalFunction;
    private int startTime;

    public Instance() {
        this.tasks = new ArrayList<>();
        this.goalFunction = 0;
        this.startTime = 0;
    }

    public Instance(Task tasks[]) {
        this.tasks = new ArrayList<>(Arrays.asList(tasks));
        this.goalFunction = 0;
        this.startTime = 0;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task[] getTasks() {
        return this.tasks.toArray(new Task[0]);
    }

    public void saveToFile(int instanceNumber, double h) {
        String filename = "results//n" + this.tasks.size() + "k" + (instanceNumber + 1) + "h" + (int)(h*10) + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(this.goalFunction + " " + h + " " + this.startTime + " " + getTasksId());
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public int sumProcessingTime() {
        int sum = 0;
        for(Task task : this.tasks) {
            sum += task.getProcessingTime();
        }
        return sum;
    }

    private String getTasksId() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : this.tasks) {
            stringBuilder.append(task.getId());
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    public void schedule(double h) {
        int stepsNumber = 10;
        int deadline = (int) Math.floor(sumProcessingTime() * h);
        int step = deadline / stepsNumber;

        int bestGoalFunction = Integer.MAX_VALUE;
        int index = 0;

        for(int j=0; j<stepsNumber; j++) {
            int currentTime = this.startTime;
            Collections.sort(this.tasks, new SortByEarlyPenalty());

            for(int i=0; i<this.tasks.size(); i++) {
                currentTime += this.tasks.get(i).getProcessingTime();
                if(currentTime > deadline) {
                    Collections.sort(this.tasks.subList(i, this.tasks.size()), new SortByLatePenalty());
                    break;
                }
            }
            int currentGoalFunction = computeGoalFunction(h, deadline);
            if(currentGoalFunction < bestGoalFunction) {
                bestGoalFunction = currentGoalFunction;
                index = j;
            }
            this.startTime += step;
        }

        this.startTime = index * step;
        int currentTime = this.startTime;
        Collections.sort(this.tasks, new SortByEarlyPenalty());

        for(int i=0; i<this.tasks.size(); i++) {
            currentTime += this.tasks.get(i).getProcessingTime();
            if(currentTime > deadline) {
                Collections.sort(this.tasks.subList(i, this.tasks.size()), new SortByLatePenalty());
                break;
            }
        }
        computeGoalFunction(h, deadline);

    }

    public int computeGoalFunction(double h, int deadline) {
        this.goalFunction = 0;
        int currentTime = startTime;
        int offset = 0;
        for(Task task : this.tasks) {
            currentTime += task.getProcessingTime();
            offset = deadline - currentTime;
            this.goalFunction += offset > 0 ? Math.abs(offset) * task.getEarlyPenalty() : Math.abs(offset) * task.getLatePenalty();
        }
        return this.goalFunction;
    }

//    private int findMinPenaltyId(boolean earlyPenaly = false) {
//        int min = this.tasks.get(0).getEarlyPenalty();
//        int index = 0;
//        int penalty;
//        for(int i=1; i<this.tasks.size(); i++) {
//            penalty = this.tasks.get(i).getEarlyPenalty();
//            if(penalty < min) {
//                min = penalty;
//                index = i;
//            }
//        }
//
//        return index;
//    }


    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        if(startTime >= 0)
            this.startTime = startTime;
        else
            this.startTime = 0;
    }
}
