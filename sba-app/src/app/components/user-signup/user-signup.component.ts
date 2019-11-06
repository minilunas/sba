import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../service/user.service";
import {User} from "../../models/user.model";
import { TimepickerModule } from 'ngx-bootstrap/timepicker';
import {AlertService} from "../../service/alert.service";
import {MatDialog} from "@angular/material";
import {EditSkillComponent} from "../edit-skill/edit-skill.component";
@Component({
  selector: 'app-user-signup',
  templateUrl: './user-signup.component.html',
  styleUrls: ['./user-signup.component.css']
})
export class UserSignupComponent implements OnInit {
  enrollForm: FormGroup;
  user: User = new User();

  loading = false;
  submitted = false;
  returnUrl: string;
  startTime: any;
  technologyStr: any;

  constructor(
    private router: Router,
    private userService:UserService,
    private route: ActivatedRoute,
    private alertService: AlertService,
    public dialog: MatDialog
    // private authenticationService: AuthenticationService,
  ) {
    // redirect to home if already logged in
    // if (this.authenticationService.currentUserValue) {
    //   this.router.navigate(['/']);
    // }
  }

  ngOnInit() {
    this.enrollForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required]),
      role: new FormControl('', [Validators.required]),
      firstname:new FormControl('',null),
      lastName:new FormControl('',null),
      linkedinUrl:new FormControl('',null),
      technologyStr:new FormControl('',null),
      yearsofExperience:new FormControl('',null),
      mentorProfile:new FormControl('',null),
    });
    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }



  enroll(enrollFormValue) {
    if (this.enrollForm.valid) {
      this.submitted = true;

      this.loading = true;

      this.userService.createUser(enrollFormValue)

        .subscribe(
          data => {

            if(data.code=='200') {
              this.alertService.success(data.message)
              if(this.returnUrl=="/"){
                this.returnUrl="/user-login"
              }
              this.router.navigate([this.returnUrl]);
            }else{
              this.alertService.error(data.message)
            }
          },
          error => {

            // this.alertService.error('Username or Password invalid');
            this.loading = false;
          });
    }
}

  editSkill() {
    const dialogRef = this.dialog.open(EditSkillComponent, {
      height: '700px',
      width: '600px',
      data:this.technologyStr,
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      if(result!=null)
        this.technologyStr=result
      // this.animal = result;
    });
  }
}
