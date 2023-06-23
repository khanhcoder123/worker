/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main1;

/**
 *
 * @author khanh
 */
import java.util.ArrayList;
import java.util.List;

// Lớp Worker đại diện cho mỗi công nhân
class Worker {
    private String name;
    private String employeeId;
    private List<Task> tasks;

    public Worker(String name, String employeeId) {
        this.name = name;
        this.employeeId = employeeId;
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void completeTask(Task task) {
        task.complete();
        System.out.println("Công việc '" + task.getDescription() + "' đã hoàn thành.");
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void printTasks() {
        System.out.println("Danh sách công việc của công nhân " + name + ":");
        for (Task task : tasks) {
            System.out.println("- " + task.getDescription() + " - Trạng thái: " + task.getStatus());
        }
    }
}

// Lớp Task đại diện cho mỗi công việc
class Task {
    private String description;
    private String status;

    public Task(String description) {
        this.description = description;
        this.status = "Chưa hoàn thành";
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public void complete() {
        status = "Đã hoàn thành";
    }
}

// Lớp TaskManager quản lý các công việc
class TaskManager {
    private static TaskManager instance;
    private List<Task> tasks;

    private TaskManager() {
        tasks = new ArrayList<>();
    }

    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    public void createTask(String description) {
        Task task = new Task(description);
        tasks.add(task);
        System.out.println("Đã tạo công việc mới: " + task.getDescription());
    }

    public List<Task> getAllTasks() {
        return tasks;
    }
}

public class Main {
    public static void main(String[] args) {
        // Sử dụng Singleton để lấy instance của TaskManager
        TaskManager taskManager = TaskManager.getInstance();

        // Tạo công việc mới
        taskManager.createTask("Dọn văn phòng");
        taskManager.createTask("Chuẩn bị báo cáo");

        // Lấy instance của công nhân
        Worker worker = new Worker("John", "EMP001");

        // Lấy danh sách công việc từ TaskManager và gán cho công nhân
        worker.addTask(taskManager.getAllTasks().get(0));
        worker.addTask(taskManager.getAllTasks().get(1));

        // Hoàn thành công việc thứ hai
        Task secondTask = worker.getTasks().get(1);
        worker.completeTask(secondTask);

        // In danh sách công việc của công nhân
        worker.printTasks();
    }
}

