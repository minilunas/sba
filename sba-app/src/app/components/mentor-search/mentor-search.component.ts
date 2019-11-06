import {Component, OnInit, TemplateRef} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {NgbDate, NgbCalendar} from '@ng-bootstrap/ng-bootstrap';
import {MentorService} from "../../service/mentor.service";
import {User} from "../../models/user.model";
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import {AuthenticationService} from "../../service/authentication.service";
import {ProposalService} from "../../service/proposal.service";
import {AlertService} from "../../service/alert.service";
@Component({
  selector: 'app-mentor-search',
  templateUrl: './mentor-search.component.html',
  styleUrls: ['./mentor-search.component.css']
})
export class MentorSearchComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,private router: Router, private mentorService: MentorService,
              private modalService: BsModalService, private authenticationService: AuthenticationService,
              private proposalService:ProposalService,private alertService: AlertService,
  ) { }

  searchForm: FormGroup;
  mentorList:any;
  user: any ;
  modalRef: BsModalRef;
  requestForm: FormGroup;
  mentorEmail:any;
  mentorId:any;
  role: string;


  ngOnInit() {
    this.searchForm = new FormGroup({

      technologyStr: new FormControl('', Validators.required)
    });

    this.requestForm = new FormGroup({
      remarks: new FormControl('', Validators.required),
      dateRange1: new FormControl('', Validators.required),
    });
    this.user =     this.authenticationService.getUser();
    this.role = this.authenticationService.getRole();

    this.mentorService.searchMentor('')
      .subscribe( result => {

        /* get请求成功时的回调处理 */
        // console.info(data);
        this.mentorList =result.data;

      });
  }
  onSubmit() {

    this.mentorService.searchMentor(this.searchForm.value)
      .subscribe( result => {

        /* get请求成功时的回调处理 */
        // console.info(data);
        this.mentorList =result.data;

      });
  }



  openModal(mentorId,mentorEmail, template: TemplateRef<any>) {
    this.mentorId=mentorId
    this.mentorEmail=mentorEmail
    this.modalRef = this.modalService.show(template);
  }

  submitRequest() {
    this.requestForm.value['mentorId'] = this.mentorId
    this.requestForm.value['mentorEmail'] = this.mentorEmail
    this.user = this.authenticationService.getUser()
    this.requestForm.value['studentEmail'] = this.user.username
    this.requestForm.value['start_date'] = this.requestForm.value.dateRange1[0]
    this.requestForm.value['end_date'] = this.requestForm.value.dateRange1[1]
    this.proposalService.createProposal(this.requestForm.value)
      .subscribe( result => {
        this.modalRef.hide()
        /* 请求成功时的回调处理 */
        // console.info(data);
        this.alertService.info("Proposal create success!")

      });
  }
}
