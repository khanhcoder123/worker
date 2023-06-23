/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.main2;

/**
 *
 * @author khanh
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Lớp Task (Công việc)
class Task {

    private String description;
    private boolean status;
    private int progress;

    public Task(String description) {
        this.description = description;
        this.status = false;
        this.progress = 0;
    }

    public String getDescription() {
        return description;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}

// Lớp TaskManager (Quản lý công việc)
// Lớp TaskManager (Quản lý công việc)
class TaskManager {

    private static TaskManager instance;
    private List<Task> tasks;

    private TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    public Task createTask(String description) {
        Task task = new Task(description);
        tasks.add(task);
        return task;
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }
}

// Lớp Worker (Công nhân)
class Worker {

    private String name;
    private String employeeID;
    private List<Task> tasks;

    public Worker(String name, String employeeID) {
        this.name = name;
        this.employeeID = employeeID;
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void checkTaskProgress() {
        System.out.println("Task progress for Worker: " + name + " (ID: " + employeeID + ")");
        for (Task task : tasks) {
            int progress = task.getProgress();
            System.out.println("Task: " + task.getDescription() + ", Progress: " + progress + "%");
        }
    }

    public void updateTaskStatus(Task task, boolean status) {
        task.setStatus(status);
    }

    public void generateReport(TaskManager taskManager) {
        System.out.println("Report for Worker: " + name + " (ID: " + employeeID + ")");
        List<Task> completedTasks = new ArrayList<>();
        for (Task task : taskManager.getTasks()) {
            if (task.getStatus()) {
                completedTasks.add(task);
            }
        }
        System.out.println("Completed Tasks:");
        for (Task task : completedTasks) {
            System.out.println("Task: " + task.getDescription() + ", Progress: " + task.getProgress() + "%");
        }
    }
}

// Lớp Report (Báo cáo)
class Report {

    private List<Task> tasks;

    public Report(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}

// Code demo
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập tên và ID của công nhân
        System.out.print("Enter worker name: ");
        String name = scanner.nextLine();
        System.out.print("Enter worker ID: ");
        String employeeID = scanner.nextLine();

        // Tạo công việc và quản lý công việc
        TaskManager taskManager = TaskManager.getInstance();
        Task task1 = taskManager.createTask("Task 1");
        Task task2 = taskManager.createTask("Task 2");

        // Tạo công nhân và gán công việc
        Worker worker = new Worker(name, employeeID);
        worker.addTask(task1);
        worker.addTask(task2);

        // Hiển thị tiến độ công việc của công nhân
        worker.checkTaskProgress();
    }
}
