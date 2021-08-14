import { AccountAddComponent } from './account-add/account-add.component';
import { SystemComponent } from './system.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    component: SystemComponent,
  },
  {
    path: 'account-add',
    component: AccountAddComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SystemRoutingModule { }
