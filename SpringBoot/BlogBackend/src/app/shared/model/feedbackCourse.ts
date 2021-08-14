import { CourseSignup } from "./courseSignup";
import { FeedbackCourseDetails } from "./feedbackCourseDetails";

export class FeedbackCourse {
    feedbackCourseId: number;
    courseId: number;
    feedbackId: number;
    userCode: string;
    userName: string;
    creationDate: Date;
    createdBy: string;
    updateDate: Date;
    updatedBy: string;

    feedbackCourseDetailsList: FeedbackCourseDetails[];
    courseSignup: CourseSignup;
}