import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {CommonModule} from '@angular/common';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';

import {RouterModule, Routes} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {BsDatepickerModule} from 'ngx-bootstrap/datepicker';
import {setTheme} from 'ngx-bootstrap/utils';
import {MentorSearchComponent} from './components/mentor-search/mentor-search.component';

import {MentorService} from './service/mentor.service';
import {UserService} from './service/user.service';

import {UserComponent} from './components/user/user.component';
import {UserSignupComponent} from './components/user-signup/user-signup.component';
import {TimepickerModule, ModalModule, RatingModule} from "ngx-bootstrap";
import {TrainingsUserComponent} from './components/trainings-user/trainings-user.component';
import {TrainingsMentorComponent} from './components/trainings-mentor/trainings-mentor.component';
import {Proposals2mentorComponent} from './components/proposals2mentor/proposals2mentor.component';
import {ProposalsComponent} from './components/proposals/proposals.component';
import {EditSkillComponent} from './components/edit-skill/edit-skill.component';
import {PaymentsComponent} from './components/payments/payments.component';
import {
  MatCardModule,
  MatCheckboxModule,
  MatDatepickerModule,
  MatDialog,
  MatDialogModule,
  MatFormFieldModule,
  MatInputModule,
  MatListModule,
  MatNativeDateModule, MatSelectModule,
  MatSnackBar,
  MatSnackBarModule
} from "@angular/material";
import {AlertService} from "./service/alert.service";
import {Overlay} from "@angular/cdk/overlay";
import {AuthenticationService} from "./service/authentication.service";
import {JwtModule} from "@auth0/angular-jwt";
import {ProposalService} from "./service/proposal.service";
import {TrainingService} from "./service/training.service";

export function tokenGetter() {
  return localStorage.getItem('access_token');
}
const appRoutes: Routes = [
  {path: 'mentor-search', component: MentorSearchComponent},
  {path: 'user-login', component: UserComponent},
  {path: 'user-signup', component: UserSignupComponent},
  {path: 'trainings-user', component: TrainingsUserComponent},
  {path: 'trainings-mentor', component: TrainingsMentorComponent},
  {path: 'mentors-proposals', component: Proposals2mentorComponent},
  {path: 'users-proposals', component: ProposalsComponent},
  {path: 'edit-skill', component: EditSkillComponent},
  {path: 'payments', component: PaymentsComponent},
  {path: '', component: MentorSearchComponent},
];

@NgModule({
  declarations: [
    AppComponent,
    MentorSearchComponent,
    UserComponent,
    UserSignupComponent,
    TrainingsUserComponent,
    TrainingsMentorComponent,
    Proposals2mentorComponent,
    ProposalsComponent,
    EditSkillComponent,
    PaymentsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CommonModule,
    RouterModule.forRoot(
      appRoutes,
      {enableTracing: true} // <-- debugging purposes only
    ),
    FormsModule,
    BrowserAnimationsModule,
    BsDatepickerModule.forRoot(),
    ReactiveFormsModule,
    TimepickerModule.forRoot(),
    ModalModule.forRoot(),
    MatFormFieldModule,
    MatInputModule,
    MatSnackBarModule,
    MatNativeDateModule,
    JwtModule.forRoot( {
      config: {
        tokenGetter: tokenGetter,
        whitelistedDomains: ['localhost:9000'],
        blacklistedRoutes: ['localhost:9000/user/user']
      }
    } ),
    MatCheckboxModule,
    MatCardModule,
    MatListModule,
    MatDialogModule,
    MatDatepickerModule,
    MatSelectModule,
    RatingModule,

  ],
  providers: [MentorService, TrainingService,UserService, AlertService, MatSnackBar, Overlay,AuthenticationService,  MatDialog,ProposalService,],
  bootstrap: [AppComponent],

})
export class AppModule {
  constructor() {

    setTheme('bs4');
  }
}
