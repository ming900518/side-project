import { Equipment } from "./equipment";

export class EquipmentBorrow extends Equipment {
    equipmentBorrowId: number;
    equipmentId: number;
    quantity: number;
    equipmentBorrowStatus: number;
    equipmentBorrowType: number;
    userCode: string;
    userName: string;
    email: string;
    notification: number;
    estimatedReturnDate: Date;
    borrowDate: Date;
    returnDate: Date;
    auditUserCode: string;
    auditUserName: string;
    remarks: string;
    creationDate: Date;
    createdBy: string;
    updateDate: Date;
    updatedBy: string;
}