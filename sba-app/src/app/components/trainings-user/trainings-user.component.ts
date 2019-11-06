import {Component, OnInit, TemplateRef} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {TrainingService} from "../../service/training.service";
import {AuthenticationService} from "../../service/authentication.service";
import {first} from "rxjs/operators";
import {AlertService} from "../../service/alert.service";
import {BsModalService} from "ngx-bootstrap";

@Component({
  selector: 'app-trainings-user',
  templateUrl: './trainings-user.component.html',
  styleUrls: ['./trainings-user.component.css']
})
export class TrainingsUserComponent implements OnInit {
  searchForm: FormGroup;
  role:any
  private trainList: any;
  status: any;
  private username: string;
  rate: any;
  trainModalRef: any;
  detailList: any;
  payModalRef: any;
  payList: any;
  constructor( private trainingService:TrainingService,
               private alertService: AlertService,
               private authenticationService: AuthenticationService,  private modalService: BsModalService, ) {}

  ngOnInit() {
    this.role = this.authenticationService.getRole()
    this.username = this.authenticationService.getUsername()
    this.status=['apply','accept','reject','start','finish']
    this.searchForm = new FormGroup({
      trainStatus: new FormControl()
    });
    this.onSearch()
  }

  onSearch() {

    this.trainingService.searchTrain({'role':this.role,'username':this.username,'status':''})
      .subscribe( result => {

        /* get请求成功时的回调处理 */
        // console.info(data);
        this.trainList =result.data;

      });
  }

  pay(id: any) {//付款
    this.trainingService.pay(id)
      .subscribe( result => {

        this.onSearch()

      });
  }

  acceptOrReject(id: any, acceptOrRejectStatus: string) {
    this.trainingService.acceptOrReject(id,acceptOrRejectStatus)
      .pipe(first())
      .subscribe(
        data => {

          this.onSearch()
        },
        error => {
          console.log("error")
          this.alertService.error('Error');
          this.alertService.success('pay success')
        });
  }

  finishClass(trainDetailId: any) {
    this.trainingService.finishClass({'id':trainDetailId})
      .subscribe( result => {

        /* get请求成功时的回调处理 */
        // console.info(data);
        // this.onSearch()
        for (let i = 0; i <this.detailList.length ; i++) {
          let id = this.detailList[i].id
          if(id==trainDetailId){
            this.detailList[i].status='finish'
          }
        }
      });
  }

  pointClass( id: any) {
    this.trainingService.commentClass({'id':id,'point':this.rate})
      .subscribe( result => {

      });
  }

  viewDetail(id: any, detail: TemplateRef<any>) {
    this.trainingService.findDetailByTrainId(id)
      .subscribe( result => {

        this.detailList = result.data
        this.trainModalRef = this.modalService.show(detail,
          Object.assign({}, { class: 'modal-lg' }));

      });

  }

  viewPayInfo(id: any,payModal: TemplateRef<any>) {
    this.trainingService.findPayByTrainId(id)
      .subscribe( result => {

        this.payList = result.data
        this.payModalRef = this.modalService.show(payModal,
          Object.assign({}, { class: 'modal-lg' }));

      });
  }
}
