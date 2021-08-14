import { FeedbackQuestion } from './feedbackQuestion';

export class Feedback {
    feedbackId: number;
    feedbackName: string;
    feedbackType: number;
    creationDate: Date;
    createdBy: string;
    updateDate: Date;
    updatedBy: string;

    feedbackQuestionList: FeedbackQuestion[];
}