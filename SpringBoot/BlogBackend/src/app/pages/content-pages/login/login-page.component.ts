import { environment } from './../../../../environments/environment';
import { Component, ViewChild } from '@angular/core';
import { NgForm, FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from "@angular/router";
import { AuthService } from 'app/shared/auth/auth.service';
import { NgxSpinnerService } from "ngx-spinner";
import { UserService } from 'app/shared/services/user.service';
import { first } from 'rxjs/operators';
import jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})

export class LoginPageComponent {
  msg: any;
  loginFormSubmitted = false;
  isLoginFailed = false;

  loginForm = new FormGroup({
    account: new FormControl("", [Validators.required]),
    password: new FormControl("", [Validators.required]),
  });

  constructor(
    private router: Router,
    private authService: AuthService,
    private spinner: NgxSpinnerService,
    private route: ActivatedRoute,
    private userService: UserService,
  ) {
  }

  get lf() {
    return this.loginForm.controls;
  }

  // On submit button click
  onSubmit() {
    this.loginFormSubmitted = true;
    if (this.loginForm.invalid) {
      return;
    }

    this.spinner.show(undefined,
      {
        type: 'ball-triangle-path',
        size: 'medium',
        bdColor: 'rgba(0, 0, 0, 0.8)',
        color: '#fff',
        fullScreen: true
      });


    this.userService.userLogin(this.loginForm.value).pipe(first()).subscribe(res => {
      console.log(this.loginForm.value)
      console.log(res.result)
      if (res.result) {
        this.spinner.hide();
        let department = this.userService.userValue.adminInfo.department;
        if (this.userService.url != null || this.userService.url != undefined) {
          this.router.navigate([this.userService.url], { queryParams: this.userService.urlRoute.queryParams });
          this.userService.url = null;
          this.userService.urlRoute = null;
        } else {
            this.router.navigate(['system'])
        }
      } else {
        this.isLoginFailed = true;
        this.msg = res.message;
        this.spinner.hide();
      }
    }, error => {
      this.isLoginFailed = true;
      this.msg = error.statusText;
      this.spinner.hide();
    })
  }

}
