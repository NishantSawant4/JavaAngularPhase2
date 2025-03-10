import { Priority, Task } from "../models/Task";
import { ErrorHandler } from "../utils/ErrorHandler";

export class TaskManager{
    private tasks: Task[] = [];
    private taskIdCounter: number = 1;

    addTask(title: string, description: string, priority: Priority = Priority.MEDIUM): Task{
        if(!title.trim()) throw new ErrorHandler('Task title cannot be empty.');
        if(!description.trim()) throw new ErrorHandler('Description cannot be empty.');

        const task = new Task(this.taskIdCounter++, title, description, priority);
        this.tasks.push(task);
        console.log(`Task "${task.title}" added successfully.`);
        return task;
    }

    getAllTasks(): Task[]{
        return this.tasks;
    }

    markTaskAsComplete(id: number): void{
        const task = this.getTaskById(id);
        task.isComplete = true;
        console.log(`Task "${task.title}" marked as complete.`);
    }

    editTask(id: number, newTitle: string, newDescription: string): void {
        const task = this.getTaskById(id);
        task.title = newTitle || task.title;
        task.description = newDescription || task.description;
        console.log(`Task "${task.title}" updated successfully.`);
    }

    deleteTask(id: number): void{
        const index = this.tasks.findIndex(task => task.id === id);
        if(index === -1) throw new ErrorHandler(`Task with ID ${id} not found.`);
        const deleteTask = this.tasks.splice(index, 1);
        console.log(`Task "${deleteTask[0].title}" deleted successfully.`);
    }

    searchTasks(query: string): Task[]{
        return this.tasks.filter(task => task.title.toLowerCase().includes(query.toLowerCase()));

    }

    private getTaskById(id: number): Task{
        const task = this.tasks.find(task => task.id === id);
        if (!task) throw new ErrorHandler(`Task with ID ${id} not found.`);
        return task;
    }
}