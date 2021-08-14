import { Routes, RouterModule } from '@angular/router';

//Route for content layout with sidebar, navbar and footer.

export const Full_ROUTES: Routes = [
  {
    path: 'system',
    loadChildren: () => import('../../pages/full-pages/system/system.module').then(m => m.SystemModule)
  }
];
