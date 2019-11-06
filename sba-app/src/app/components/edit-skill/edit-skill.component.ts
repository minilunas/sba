import {Component, Inject, OnInit} from '@angular/core';
import { MatDialogRef, MatListOption} from "@angular/material";
import {forEachComment} from "tslint";

@Component({
  selector: 'app-edit-skill',
  templateUrl: './edit-skill.component.html',
  styleUrls: ['./edit-skill.component.css']
})
export class EditSkillComponent implements OnInit {

  tenList: any;
  tenSelect:any;

  constructor(
    public dialogRef: MatDialogRef<EditSkillComponent>,
    ) {}

  ngOnInit() {
  let arr= ['Java', 'C', 'C#', 'Go', 'Spring Cloud', 'Spring Boot', 'Html', 'Angular', 'ReactJs','Mysql','Oracle'];
    this. tenList =[]
    for (let i = 0; i < arr.length; i++) {
      this.tenList.push({'text':arr[i],'selecte':false})
    }
  }



  onNoClick(): void {
    debugger
    this.dialogRef.close();
  }

  onAdd(selected: MatListOption[]) {
    let str=''
    for(let i=0;i<selected.length;i++){
      str+=selected[i].value+','
    }

    this.dialogRef.close(str);
  }
}
