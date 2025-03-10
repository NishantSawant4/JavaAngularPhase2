"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Task = exports.Priority = void 0;
var Priority;
(function (Priority) {
    Priority["LOW"] = "Low";
    Priority["MEDIUM"] = "Medium";
    Priority["HIGH"] = "High";
})(Priority || (exports.Priority = Priority = {}));
class Task {
    constructor(id, title, description, priority = Priority.MEDIUM, isComplete = false) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.isComplete = isComplete;
    }
}
exports.Task = Task;
