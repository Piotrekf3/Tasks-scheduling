import java.util.Comparator;

public class Task {

    private int id;
    private int processingTime;
    private int earlyPenalty;
    private int latePenalty;

    public Task(int id, int processingTime, int earlyPenalty, int latePenalty) {
        this.id = id;
        this.processingTime = processingTime;
        this.earlyPenalty = earlyPenalty;
        this.latePenalty = latePenalty;
    }

    public Task() {
        this.id = 0;
        this.processingTime = 0;
        this.earlyPenalty = 0;
        this.latePenalty = 0;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }

    public int getEarlyPenalty() {
        return earlyPenalty;
    }

    public void setEarlyPenalty(int earlyPenalty) {
        this.earlyPenalty = earlyPenalty;
    }

    public int getLatePenalty() {
        return latePenalty;
    }

    public void setLatePenalty(int latePenalty) {
        this.latePenalty = latePenalty;
    }

    public int getId() {
        return id;
    }
}

class SortByEarlyPenalty implements Comparator<Task> {
    @Override
    public int compare(Task task1, Task task2) {
        return task1.getEarlyPenalty() - task2.getEarlyPenalty();
    }
}

class SortByLatePenalty implements  Comparator<Task> {
    @Override
    public int compare(Task task1, Task task2) {
        return task2.getLatePenalty() - task1.getLatePenalty();
    }
}