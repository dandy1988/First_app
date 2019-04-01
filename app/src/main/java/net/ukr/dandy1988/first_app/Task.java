package net.ukr.dandy1988.first_app;

public class Task {
    private final String name;
    private  final int priority;

    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }


}
