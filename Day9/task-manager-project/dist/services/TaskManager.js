"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.TaskManager = void 0;
const Task_1 = require("../models/Task");
const ErrorHandler_1 = require("../utils/ErrorHandler");
class TaskManager {
    constructor() {
        this.tasks = [];
        this.taskIdCounter = 1;
    }
    addTask(title, description, priority = Task_1.Priority.MEDIUM) {
        if (!title.trim())
            throw new ErrorHandler_1.ErrorHandler('Task title cannot be empty.');
        if (!description.trim())
            throw new ErrorHandler_1.ErrorHandler('Description cannot be empty.');
        const task = new Task_1.Task(this.taskIdCounter++, title, description, priority);
        this.tasks.push(task);
        console.log(`Task "${task.title}" added successfully.`);
        return task;
    }
    getAllTasks() {
        return this.tasks;
    }
    markTaskAsComplete(id) {
        const task = this.getTaskById(id);
        task.isComplete = true;
        console.log(`Task "${task.title}" marked as complete.`);
    }
    editTask(id, newTitle, newDescription) {
        const task = this.getTaskById(id);
        task.title = newTitle || task.title;
        task.description = newDescription || task.description;
        console.log(`Task "${task.title}" updated successfully.`);
    }
    deleteTask(id) {
        const index = this.tasks.findIndex(task => task.id === id);
        if (index === -1)
            throw new ErrorHandler_1.ErrorHandler(`Task with ID ${id} not found.`);
        const deleteTask = this.tasks.splice(index, 1);
        console.log(`Task "${deleteTask[0].title}" deleted successfully.`);
    }
    searchTasks(query) {
        return this.tasks.filter(task => task.title.toLowerCase().includes(query.toLowerCase()));
    }
    getTaskById(id) {
        const task = this.tasks.find(task => task.id === id);
        if (!task)
            throw new ErrorHandler_1.ErrorHandler(`Task with ID ${id} not found.`);
        return task;
    }
}
exports.TaskManager = TaskManager;
