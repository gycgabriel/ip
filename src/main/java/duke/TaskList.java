package duke;

import java.util.ArrayList;

/**
 * TaskList represents a list of tasks and the methods to modify that list.
 *
 * @author Gabriel Goh
 */
public class TaskList {

    private final ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Empty constructor for fresh start.
     */
    public TaskList() {
    }

    /**
     * Mark task in tasklist as done via task description.
     *
     * @param description Task description
     * @param tt          Type of Task
     * @return Task marked as done
     * @throws DukeException Task not found
     */
    public Task markDone(String description, Duke.TaskTypes tt) throws DukeException {
        int index = getTaskIndex(description, tt);
        if (index == -1) {
            throw new TaskNotFound(tt + ": " + description);
        } else {
            return markDone(index);
        }
    }

    /**
     * Mark task in tasklist as done via index.
     *
     * @param index Index of task in list
     * @return Task marked as done
     * @throws TaskIndexOutOfBounds Index out of bounds
     */
    public Task markDone(int index) throws TaskIndexOutOfBounds {
        if (index >= 0 && index < taskList.size()) {
            Task t = taskList.get(index);
            t.markAsDone();
            return t;
        } else {
            throw new TaskIndexOutOfBounds(index, taskList.size());
        }
    }

    /**
     * Returns number of tasks in list.
     *
     * @return Size of task list
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Removes task from list via index.
     *
     * @param index Index of task in list
     * @return Task removed
     * @throws TaskIndexOutOfBounds Index out of bounds
     */
    public Task remove(int index) throws TaskIndexOutOfBounds {
        if (index >= 0 && index < taskList.size()) {
            return taskList.remove(index);
        } else {
            throw new TaskIndexOutOfBounds(index, taskList.size());
        }
    }

    /**
     * Removes task from list via description.
     *
     * @param description Task description
     * @param tt          Type of Task
     * @return Task removed
     * @throws DukeException Task not found
     */
    public Task remove(String description, Duke.TaskTypes tt) throws DukeException {
        int index = getTaskIndex(description, tt);
        if (index == -1) {
            throw new TaskNotFound(tt + ": " + description);
        } else {
            return remove(index);
        }
    }

    /**
     * Format list of tasks into savable string
     *
     * @return String to save
     */
    public String saveString() {
        StringBuilder toWrite = new StringBuilder();
        taskList.forEach((t) -> toWrite.append(t.saveString()).append("\n"));
        return toWrite.toString();
    }

    /**
     * Return index of task by task description.
     *
     * @param description Task description
     * @param tt          Type of Task
     * @return Index of task if found else -1
     */
    public int getTaskIndex(String description, Duke.TaskTypes tt) {
        int i;
        switch (tt) {
        case TODO:
            i = taskList.indexOf(new Todo(description));
            break;
        case DEADLINE:
            i = taskList.indexOf(new Deadline(description));
            break;
        case EVENT:
            i = taskList.indexOf(new Event(description));
            break;
        default:
            i = -1; // unreachable by design if all task cases are there
        }
        return i;
    }

    /**
     * Search tasklist for tasks that matches search key.
     * Returns new TaskList object of matching tasks.
     *
     * @param searchKey Search string to match
     * @return TaskList containing matching tasks
     */
    public TaskList find(String searchKey) {
        TaskList results = new TaskList();
        taskList.forEach((task) -> {
            if (task.containsString(searchKey)) {
                results.add(task);
            }
        });
        return results;
    }

    /**
     * String representation of Tasklist
     *
     * @return String to print
     */
    @Override
    public String toString() {
        if (taskList.size() == 0) {
            return "No tasks, meow!";
        }

        StringBuilder s = new StringBuilder();
        s.append("Here are the tasks in your list, meow:");
        for (int i = 0; i < taskList.size(); i++) {
            s.append("\n   ").append(i + 1).append(". ").append(taskList.get(i));
        }
        return s.toString();
    }


}
