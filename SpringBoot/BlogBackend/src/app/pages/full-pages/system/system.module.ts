import { PipeModule } from 'app/shared/pipes/pipe.module';
import { SharedModule } from 'app/shared/shared.module';
import { SystemRoutingModule } from './system-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SystemComponent } from './system.component';
import { AccountAddComponent } from './account-add/account-add.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    SystemComponent,
    AccountAddComponent
  ],
  imports: [
    CommonModule,
    SystemRoutingModule,
    SharedModule,
    PipeModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class SystemModule { }
