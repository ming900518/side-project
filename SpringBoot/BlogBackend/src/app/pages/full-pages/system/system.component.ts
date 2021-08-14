import { DialogService } from 'primeng/dynamicdialog';
import { Router } from '@angular/router';
import { ApiBackendService } from 'app/shared/services/api-backend.service';
import { AdminInfo } from '../../../shared/model/adminInfo';
import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { DialogComponent } from 'app/shared/dialog/dialog.component';

@Component({
  selector: 'app-system',
  templateUrl: './system.component.html',
  styleUrls: ['./system.component.scss'],
  providers: [DialogService]
})
export class SystemComponent implements OnInit {


  accountList$: Subject<AdminInfo[]> = new Subject<AdminInfo[]>();
  cols: any[];
  data: AdminInfo[];

  constructor(
    private apiBackendService: ApiBackendService,
    private router: Router,
    private dialogService: DialogService
  ) { }

  ngOnInit(): void {
    this.loadTable();
    this.queryAccounts();
  }


  loadTable() {
    this.cols = [
      { field: 'userCode', header: 'User Code' },
      { field: 'name', header: 'Name' },
      { field: 'department', header: 'Permission' },
      { field: '', header: 'Action' }
    ];
  }

  onAddAccount() {
    this.router.navigate(['system/account-add']);
  }


  onEdit(id: number){
    this.router.navigate(['system/account-add'], {
      queryParams: {
        adminId: id
      }
    });
  }

  onChangeStatus(id: number, status: number, account: string) {
    const refe = this.dialogService.open(DialogComponent, { data: { type: 2, msg: `${status === 1 ? 'Enable' : 'Disable'} account: ${account}?` }, header: 'Confirmation', width: '300px' });
    refe.onClose.subscribe(close => {
      if (close === 1) {
        this.apiBackendService.save({ 'adminId': id, 'status': status }, 'saveAccount').subscribe(data => {
          if (data.result) {
            const ref = this.dialogService.open(DialogComponent, { data: { type: 1, msg: data.message }, header: 'Result', width: '300px' });
            ref.onClose.subscribe(close => {
              this.queryAccounts();

            })
          }
        })
      } else {

      }

    })
  }


  queryAccounts() {

    this.apiBackendService.query({}, 'queryAccountList').subscribe(
      data => {
        if (data.result) {
          this.data = data.data
          this.accountList$.next(data.data);
        } else {
          this.accountList$.next([]);
          this.data = [];
        }
      },
      error => {
        this.accountList$.next([]);
        this.data = [];

      }
    )

  }

}
