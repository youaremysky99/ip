package service;

import java.util.Optional;

import exceptions.InvalidCommandException;


/**
 * A class represents a Todo task
 */
public class TodoTask extends Task {
    public static final String TASK_WORD = "todo";

    private String description;

    /**
     * Constructor
     * @param tokens an array of tokens
     */
    public TodoTask(String[] tokens) {
        super(tokens, TASK_WORD);
    }

    /**
     * Overriden method, to explicitly parse the task
     * @throws InvalidCommandException when its synax has problems, and to report to users
     */
    @Override
    public void parse() throws InvalidCommandException {
        Optional<String> optDesc = java.util.Arrays.stream(super.tokens, 0, super.tokens.length)
                                    .reduce((a, b) -> a + " " + b);
        if (optDesc.isEmpty()) {
            throw new InvalidCommandException("Description must not be empty");
        } else {
            this.description = optDesc.get();
        }
    }

    /**
     * Get the description of the task
     * @return a string denoting the description
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[T]" + this.getStatusIcon() + " " + this.getDescription();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof TodoTask)) {
            return false;
        }
        TodoTask otherTodo = (TodoTask) other;
        return this.description.equals(otherTodo.description);
    }
}
