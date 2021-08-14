import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router, CanLoad } from '@angular/router';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { UserService } from '../services/user.service';

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(
    private authService: AuthService,
    private router: Router,
    private userService: UserService
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    let user = this.userService.userValue;
    let url = state.url;
    let x = url.indexOf("?");
    if (x > 0) {
      url = url.substring(0, x);
    }
    if (user === null) {
      this.userService.url = url;
      this.userService.urlRoute = route;
      this.router.navigate(['/pages/login']);
    }
    else {
      return true;
    }
  }
}
