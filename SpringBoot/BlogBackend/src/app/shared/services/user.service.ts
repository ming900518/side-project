import { AdminInfo } from '../model/adminInfo';
import { User } from '../model/user';
import { environment } from 'environments/environment';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import jwt_decode from 'jwt-decode';

const API_URL = environment.serviceUrl;

@Injectable({
  providedIn: 'root'
})
export class UserService {
  public userSubject: BehaviorSubject<User>;
  public user: Observable<User>;
  public url: string;
  public urlRoute: ActivatedRouteSnapshot;

  constructor(
    private router: Router,
    private http: HttpClient
  ) {
    this.userSubject = new BehaviorSubject<any>(JSON.parse(sessionStorage.getItem('user')));
    this.user = this.userSubject.asObservable();
  }

  public get userValue(): User {
    return this.userSubject.value;
  }
  userLogin(param) {
    return this.http.post<any>(`${API_URL}/login_backend`, param)
      .pipe(map(user => {
        if (user.result) {
          let userInfo: any = user.data;
          sessionStorage.setItem('user', JSON.stringify(userInfo));
          this.userSubject.next(userInfo);
        }
        return user;
      }));
  }

  logout() {
    // remove user from local storage and set current user to null
    sessionStorage.removeItem('user');
    this.userSubject.next(null);
    this.router.navigate(['pages/login']);
  }

  resetPassword(param: any) {
    return this.http.post<any>(`${API_URL}/api_app/resetPassword`, param).pipe(map(data => {
      return data;
    }));
  }
}
