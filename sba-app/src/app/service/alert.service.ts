import { Injectable } from '@angular/core';

import {MatSnackBar, MatSnackBarConfig} from "@angular/material";

@Injectable()
export class AlertService {

  constructor(private _snackBar: MatSnackBar) { }

  //注意
  warning(message:string, action?: string, config?: MatSnackBarConfig){
    this._snackBar.open(message, "warning", {
      duration: 3000,
      panelClass:["alert","alert-warning"]
    });
  }

  //错误
  error(message:string, action?: string, config?: MatSnackBarConfig){
    this._snackBar.open(message, "error", {
      panelClass:["alert","alert-danger"]
    });
  }

  //成功
  success(message:string, action?: string, config?: MatSnackBarConfig){
    this._snackBar.open(message, "success", {
      duration: 3000,
      panelClass:["success","alert-success"]
    });
  }

  //信息
  info(message:string, action?: string, config?: MatSnackBarConfig){
    this._snackBar.open(message, "info", {
      duration: 3000,
      panelClass:["alert","alert-info"]
    });
  }

}
