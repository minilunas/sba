import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {User} from "../models/user.model";
import {Observable} from "rxjs/index";
import {ApiResponse} from "../models/api.response";
import {Training} from "../models/training.model";

@Injectable()
export class ProposalService {

  constructor(private http: HttpClient) { }
  baseUrl: string = 'http://localhost:9000/train/proposal';


  getUsers(email) : Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.baseUrl+"check");
  }

  getUserById(id: number): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.baseUrl + id);
  }

  createProposal(training: Training): Observable<ApiResponse> {
    console.log("begin to create training",training)
    return this.http.post<ApiResponse>(this.baseUrl+"/addProposals", training);
  }

  updateUser(user: User): Observable<ApiResponse> {
    return this.http.put<ApiResponse>(this.baseUrl + user.id, user);
  }


}
