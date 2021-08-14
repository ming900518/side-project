import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { ApiBackendService } from 'app/shared/services/api-backend.service';
import { FormControl, FormGroup } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { DialogService } from 'primeng/dynamicdialog';
import { DialogComponent } from 'app/shared/dialog/dialog.component';

@Component({
  selector: 'app-account-add',
  templateUrl: './account-add.component.html',
  styleUrls: ['./account-add.component.scss'],
  providers: [DialogService]
})
export class AccountAddComponent implements OnInit {

  accountForm: FormGroup;
  isNew: boolean;
  adminId: number;
  constructor(
    private apiBackendService: ApiBackendService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private dialogService: DialogService
  ) { }

  ngOnInit(): void {
    this.initForm();
    this.activatedRoute.queryParams.subscribe(queryParams => {
      if (queryParams.adminId !== undefined && queryParams.adminId != null) {
        this.isNew = false;
        this.adminId = queryParams.adminId;
        this.isOldInit();
      } else {
        this.isNew = true;
        this.isNewInit();
      }
    })

  }

  isOldInit() {
    this.apiBackendService.query({ adminId: this.adminId }, 'queryAccount').subscribe(data => {
      if (data.result) {
        this.accountForm.patchValue({
          adminId: this.adminId,
          userCode: data.data.userCode,
          name: data.data.name,
          department: data.data.department,
          note: data.data.note,
        })
      }
    })
  }

  isNewInit() {

  }


  private initForm() {
    this.accountForm = new FormGroup({
      adminId: new FormControl(null),
      userCode: new FormControl(null),
      password: new FormControl(null),
      name: new FormControl(null),
      department: new FormControl(null),
      note: new FormControl(null),
      status: new FormControl(1),
    })
  }

  onSubmit() {
    this.apiBackendService.save(this.accountForm.value, 'saveAccount').subscribe(data => {
      if (data.result) {
        const ref = this.dialogService.open(DialogComponent, { data: { type: 1, msg: data.message }, header: 'Result', width: '300px' });
        ref.onClose.subscribe(close => {
          this.router.navigate(['system']);

        })
      } else {
        const ref = this.dialogService.open(DialogComponent, { data: { type: 1, msg: data.message }, header: 'Result', width: '300px' });
        ref.onClose.subscribe(close => {

        })
      }
    })
  }

  onCancel() {
    const refe = this.dialogService.open(DialogComponent, { data: { type: 2, msg: 'Exit this page?' }, header: 'Information', width: '300px' });
    refe.onClose.subscribe(close => {
      if (close === 1) {

        this.router.navigate(['system']);
      } else {

      }

    })
  }

}
