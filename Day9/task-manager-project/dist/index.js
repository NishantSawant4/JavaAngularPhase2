"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const Task_1 = require("./models/Task");
const TaskManager_1 = require("./services/TaskManager");
const taskManager = new TaskManager_1.TaskManager();
try {
    taskManager.addTask('Finish TMS Project', 'Implement all features with TypeScript', Task_1.Priority.HIGH);
    taskManager.addTask('Do Practice', 'Ensure to explore on TypeScript', Task_1.Priority.MEDIUM);
    console.log('All Tasks:', taskManager.getAllTasks());
    taskManager.markTaskAsComplete(1);
    taskManager.editTask(2, 'Submit the Project', 'Make sure to submit task before EOD');
    const searchResults = taskManager.searchTasks('Submit');
    console.log('Search Results:', searchResults);
    taskManager.deleteTask(1);
    console.log('Final Task List:', taskManager.getAllTasks());
}
catch (error) {
    console.log(`${error.message}`);
}
