import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import {Observable} from "rxjs/index";
import {ApiResponse} from "../models/api.response";
import {Mentor} from "../models/mentor.model";
@Injectable()
export class MentorService {

  constructor(private http: HttpClient) { }
  baseUrl: string = 'http://localhost:9000/user/user/';

  searchMentor(obj) : Observable<ApiResponse> {

      return this.http.get<ApiResponse>(this.baseUrl+"queryMentor?skill="
        +(typeof obj.technologyStr=='undefined'?"":obj.technologyStr));

  }

  editSkill(id: number): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.baseUrl + id);
  }

}
