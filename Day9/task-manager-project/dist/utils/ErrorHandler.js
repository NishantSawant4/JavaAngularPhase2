"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ErrorHandler = void 0;
class ErrorHandler extends Error {
    constructor(message) {
        super(message);
        this.name = 'ErrorHandler';
    }
}
exports.ErrorHandler = ErrorHandler;
