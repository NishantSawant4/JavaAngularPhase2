export enum Priority{
    LOW = "Low",
    MEDIUM = "Medium",
    HIGH = "High"
}

export class Task{
    constructor(public id: number,
        public title: string,
        public description: string,
        public priority: Priority = Priority.MEDIUM,
        public isComplete: boolean = false
    ){}
}