import { Student } from './student.model';
import { FileModel } from "./file.model";

export class Course {
  constructor(
    public courseId: number,
    public courseName: String,
    public courseContent: String,
    public courseStartDate: Date,
    public courseEndDate: Date,
    public courseHours: number,
    public applyStartDate: Date,
    public applyEndDate: Date,
    public applyDateLimit: number,
    public courseLocation: String,
    public studentLimit: number,
    public applyRole: number,
    public teacherName: String,
    public teacherEmail: String,
    public teacherPhone: String,
    public checkInFeedback: number,
    public checkInQRcode: number,
    public files: FileModel,
    public students: Student
  ) { }
}
