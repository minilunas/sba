import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {NgbDate, NgbCalendar} from '@ng-bootstrap/ng-bootstrap';
import {UserService} from "../../service/user.service";
import {AlertService} from "../../service/alert.service";
import {AuthenticationService} from "../../service/authentication.service";
import {first, map} from "rxjs/operators";
import {AppComponent} from "../../app.component";
import {ApiResponse} from "../../models/api.response";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  loginForm: FormGroup;

  loading = false;
  submitted = false;
  returnUrl: string;

  constructor(
    private router: Router,
    private userService: UserService,
    private route: ActivatedRoute,
    private alertService: AlertService,
    private authenticationService: AuthenticationService,
    public appComponent: AppComponent
  ) {
    //redirect to home if already logged in
    if (this.authenticationService.hasLogin()) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit() {
    this.loginForm = new FormGroup({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
    });
    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    this.authenticationService.setAppComponent(this.appComponent)
  }


  login(loginFormValue) {
    if (this.loginForm.valid) {
      this.submitted = true;

      this.loading = true;
      this.authenticationService.login(loginFormValue.username, loginFormValue.password)
        .pipe(first())
        .subscribe(
          data => {

            this.router.navigate([this.returnUrl]);
          },
          error => {debugger
            console.log("error")
            this.alertService.error('Username or Password invalid');
            this.loading = false;
          });

    }

  }


}
