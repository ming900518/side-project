import { DatePipe } from '@angular/common';
import { Equipment } from './../model/equipment';
import { Pipe, PipeTransform } from '@angular/core';
import { EquipmentBorrow } from '../model/equipmentBorrow';
import { Course } from '../model/course';
import { CourseLocation } from '../model/courseLocation';
import { DomSanitizer } from '@angular/platform-browser';

@Pipe({
  name: 'filter'
})
export class FilterPipe implements PipeTransform {
  transform(items: any[], searchTerm: string, labelKey?: string): any {
    if (!items || !searchTerm) {
      return null;
    }

    return items.filter(
      item =>
        item[labelKey || 'name']
          .toLowerCase()
          .includes(searchTerm.toLowerCase()) === true
    ).slice(0, 10);
  }
}



@Pipe({
  name: 'equipmentBorrowStatus'
})
export class EquipmentBorrowStatusPipe implements PipeTransform {
  transform(equipmentBorrow: EquipmentBorrow): string {

    if (equipmentBorrow.returnDate != null) {
      return '已歸還'
    } else if (new Date() >= equipmentBorrow.estimatedReturnDate) {
      return '已逾期'
    } else {
      return '借用中';
    }
  }
}


@Pipe({
  name: 'equipmentStatus'
})
export class EquipmentStatusPipe implements PipeTransform {
  transform(equipment: Equipment): string {
    switch (equipment.equipmentStatus) {
      case 0:
        return "異常";
      case 1:
        return "正常";
      case 2:
        return "遺失";
      case 3:
        return "報廢";
    }
  }
}


@Pipe({
  name: 'courseDatePipe'
})
export class CourseDatePipe implements PipeTransform {

  constructor(
    private datePipe: DatePipe
  ) { }
  // type=> 1: 課程日期, 2: 報名日期
  transform(course: Course, type: any): string {
    if (type == 1) {
      const startDate = this.datePipe.transform(course.postStartDate, 'yyyy/MM/dd hh:mm')
      const endDate = this.datePipe.transform(course.postEndDate, 'yyyy/MM/dd hh:mm')

      if (this.datePipe.transform(course.postStartDate, 'yyyy/MM/dd') == this.datePipe.transform(course.postEndDate, 'yyyy/MM/dd')) {
        return this.datePipe.transform(course.postStartDate, 'yyyy/MM/dd') + '<br>' + this.datePipe.transform(course.postStartDate, 'hh:mm') + "-" + this.datePipe.transform(course.postEndDate, 'hh:mm');
      } else {
        return '起 ' + startDate + '<br>迄 ' + endDate;
      }

    } else {
      const startDate = this.datePipe.transform(course.applyStartDate, 'yyyy/MM/dd')
      const endDate = this.datePipe.transform(course.applyEndDate, 'yyyy/MM/dd')
      return '起 ' + startDate + '<br>迄 ' + endDate;
    }
  }
}


@Pipe({
  name: 'courseTypePipe'
})
export class CourseTypePipe implements PipeTransform {
  transform(num: number): string {
    if (num == 1) {
      return '觀課'
    } else if (num == 2) {
      return '活動'
    } else {
      return '研習';
    }
  }
}

@Pipe({
  name: 'courseLocationPipe'
})
export class CourseLocationPipe implements PipeTransform {
  constructor(private sanitized: DomSanitizer) { }
  transform(locationList: CourseLocation[]): any {
    let result: string = '';
    locationList.forEach(location => {
      let campus: string = '';
      if (location.isChecked == 1) {
        switch (location.campus) {
          case 0:
            campus = '其他';
            break;
          case 1:
            campus = '新店';
            break;
          case 2:
            campus = '宜蘭';
            break;
          case null:
            campus = '';
        }
        result += (campus + '校區 地點: ' + location.location + '<br>')
      }
    })
    return this.sanitized.bypassSecurityTrustHtml(result);
  }
}


@Pipe({
  name: 'courseLocationNamePipe'
})
export class CourseLocationNamePipe implements PipeTransform {
  constructor() { }
  transform(locationId: number): any {
    switch (locationId) {
      case 0:
        return '其他';
      case 1:
        return '新店';
      case 2:
        return '宜蘭';
      case null:
        return '';
    }
  }
}


@Pipe({
  name: 'measurementUnitPipe'
})
export class MeasurementUnitPipe implements PipeTransform {
  constructor() { }
  transform(type: number): any {
    switch (type) {
      case 1:
        return 'kg';
      case 2:
        return 'g';
      case 3:
        return 'ml';
      case null:
        return '';
    }
  }
}


@Pipe({
  name: 'adminRolePipe'
})
export class AdminRolePipe implements PipeTransform {
  constructor() { }
  transform(role: number): any {
    switch (role) {
      case 1:
        return '主管';
      case 2:
        return '承辦';
      case null:
        return '';
    }
  }
}


@Pipe({
  name: 'departmentPipe'
})
export class DepartmentPipe implements PipeTransform {
  constructor() { }
  transform(department: number): any {
    switch (department) {
      case 1:
        return '管理部';
      case 2:
        return '業務部';
      case 3:
        return '採購部';
      case 4:
        return '生產部';
      case 5:
        return '品管部';
      case 6:
        return '倉管部';
      case null:
        return '';
    }
  }
}


@Pipe({
  name: 'purchaseStatusPipe'
})
export class PurchaseStatusPipe implements PipeTransform {
  constructor() { }
  transform(status: number): any {
    switch (status) {
      case 1:
        return '未簽核';
      case 2:
        return '未進料';
      case 3:
        return '已進料';
      case null:
        return '';
    }
  }
}


@Pipe({
  name: 'reveiveStatusPipe'
})
export class ReveiveStatusPipe implements PipeTransform {
  constructor() { }
  transform(status: number): any {
    switch (status) {
      case 1:
        return '申請領料';
      case 2:
        return '已領料';
      case null:
        return '';
    }
  }
}
