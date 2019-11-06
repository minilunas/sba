import {Component, OnInit} from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import {AuthenticationService} from "./service/authentication.service";
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent  implements OnInit {

  constructor(    private authenticationService: AuthenticationService,){}

  title = 'sba-app';
  role: string;
  currentUser: any;
  username: any;
  ngOnInit(): void {
    this.authenticationService.printLocalStorage()

    this.role=this.authenticationService.getRole()
    this.currentUser = this.authenticationService.getUser()
    this.username = this.authenticationService.getUsername()
  }

  logout() {
    this.authenticationService.logout();
    this.role=''
    this.currentUser=null
  }
}
