import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import {Observable} from "rxjs/index";
import {ApiResponse} from "../models/api.response";
import {Mentor} from "../models/mentor.model";
@Injectable()
export class TrainingService {

  constructor(private http: HttpClient) { }
  baseUrl: string = 'http://localhost:9000/train/user/';

  searchTrain(obj) : Observable<ApiResponse> {debugger
  if(obj.role=='user'){
    this.baseUrl= 'http://localhost:9000/train/proposal/queryStudentProp?email='+obj.username+'&status=';
  }else{
    this.baseUrl= 'http://localhost:9000/train/proposal/queryMentorProp?email='+obj.username+'&status=';
  }

    return this.http.get<ApiResponse>(this.baseUrl
      +(typeof obj.technologyStr=='undefined'?"":obj.technologyStr));

  }

  acceptOrReject(id: any, acceptOrRejectStatus: string) {
    return this.http.post<ApiResponse>( 'http://localhost:9000/train/proposal/acceptOrReject', {'status':acceptOrRejectStatus,'id':id});
  }

  pay(id: any) {
    return this.http.get<ApiResponse>('http://localhost:9000/train/proposal/payProposals?trainingId='+id);
  }

  finishClass(body) {
    return this.http.post<ApiResponse>( 'http://localhost:9000/train/training/completeClass', body);
  }

  findDetailByTrainId(id: any) {
    return this.http.get<ApiResponse>('http://localhost:9000/train/training/queryTrainDetail?trainId='+id);
  }

  commentClass(body) {
    return this.http.post<ApiResponse>( 'http://localhost:9000/train/training/commentClass', body);
  }

  findPayByTrainId(id: any) {
    return this.http.get<ApiResponse>('http://localhost:9000/train/training/queryPayInfo?trainId='+id);
  }
}
