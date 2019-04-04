package net.ukr.dandy1988.first_app;

import java.io.Serializable;

public class Task implements Serializable {

    private static final long serialVersionUID = 133754295427625438L;

    private final String name;
    private  final int priority;
    private  final int color;

    public Task(String name, int priority, int color) {
        this.name = name;
        this.priority = priority;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public int getColor() {
        return color;
    }

}
