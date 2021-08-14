import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { EquipmentBorrowStatusPipe, EquipmentStatusPipe, FilterPipe, CourseDatePipe, CourseTypePipe, CourseLocationPipe, CourseLocationNamePipe, MeasurementUnitPipe, AdminRolePipe, DepartmentPipe, PurchaseStatusPipe, ReveiveStatusPipe } from './filter.pipe';
import { SearchPipe } from './search.pipe';
import { ShortNamePipe } from './short-name.pipe';

@NgModule({
  declarations: [FilterPipe, SearchPipe, ShortNamePipe, EquipmentBorrowStatusPipe, EquipmentStatusPipe, CourseDatePipe, CourseTypePipe, CourseLocationPipe, CourseLocationNamePipe, MeasurementUnitPipe, AdminRolePipe, DepartmentPipe, PurchaseStatusPipe, ReveiveStatusPipe],
  imports: [CommonModule],
  exports: [FilterPipe, SearchPipe, ShortNamePipe, EquipmentBorrowStatusPipe, EquipmentStatusPipe, CourseDatePipe, CourseTypePipe, CourseLocationPipe, CourseLocationNamePipe, MeasurementUnitPipe, AdminRolePipe, DepartmentPipe, PurchaseStatusPipe, ReveiveStatusPipe]
})
export class PipeModule { }
