import { Equipment } from "./equipment";
import { EquipmentBorrow } from './equipmentBorrow';

export class EquipmentBorrowResponse extends EquipmentBorrow {
    equipment: Equipment;
}