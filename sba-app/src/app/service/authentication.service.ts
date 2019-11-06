import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {BehaviorSubject, Observable} from 'rxjs';
import {User} from '../models/user.model';
import {UserService} from './user.service';
import {Router} from '@angular/router';
import {environment} from '../../environments/environment';
import {ApiResponse} from "../models/api.response";
import {AppComponent} from "../app.component";
import {__await} from "tslib";

const httpOptions = {
  headers: {
    'Content-Type': 'application/json',
    "Access-Control-Allow-Origin": "*",
    'Accept': 'application/json', 'Access-Control-Max-Age': '1800'
  }
};

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private currentUserSubject: any;
  public currentUser: any;
  private appComponent: AppComponent;


  constructor(private http: HttpClient,
              private userservice: UserService,
              private router: Router,
              ) {
    this.currentUserSubject = localStorage.getItem('currentUser');
    this.currentUser = localStorage.getItem('currentUser');

  }


  public  hasLogin(): boolean {
    return localStorage.getItem('currentUser')!=null;
  }


  login(username, password) {


    return this.http.post<ApiResponse>('http://localhost:9000/security/login',
      {username: username, password: password}, httpOptions)
      .pipe(
        map( async result => {
          localStorage.setItem( 'username', result.username );
          localStorage.setItem( 'role', result.role );
          localStorage.setItem( 'access_token', result.token );
          localStorage.setItem( 'currentUser', JSON.stringify( result ) );
          this.appComponent.role = result.role
          this.appComponent.currentUser = result
          this.appComponent.username = result.username

          //记录token
          return true;
        })
      );
  }

  logout() {
    // remove user from local storage and set current user to null

    localStorage.removeItem('username');
    localStorage.removeItem('role');
    localStorage.removeItem('access_token');
    localStorage.removeItem('currentUser');
    this.currentUserSubject=null;

    this.router.navigate(['/']);
  }

  printLocalStorage() {
    console.log('username', localStorage.getItem('username'))
    console.log('role', localStorage.getItem('role'))
    console.log('access_token', localStorage.getItem('access_token'))
    console.log('currentUser', localStorage.getItem('currentUser'))
  }

  getRole() {
    return localStorage.getItem('role');
  }

  getUser() {
    return JSON.parse(localStorage.getItem('currentUser'));
  }

  setAppComponent(appComponent: AppComponent) {
    this.appComponent = appComponent
  }

  getUsername() {
    return localStorage.getItem('username');
  }
}
