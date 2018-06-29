import { Component, OnInit } from '@angular/core';
import { AjaxService } from '../../../../common/services/ajax.service';
import { MessageBarService } from '../../../../common/services/message-bar.service';


@Component({
  selector: 'app-int02-2',
  templateUrl: './int02-2.component.html',
  styleUrls: ['./int02-2.component.css']
})
export class Int022Component implements OnInit {

  departmentNameArr: any;
  departmentName: any;
  departmentNameNew: any;
  constructor(private ajax: AjaxService, private messageBarService: MessageBarService) {



  }

  ngOnInit() {
    this.departmentNameArr = "";
    const URL = "combobox/controller/comboboxHeaderQuestionnaire";

    this.ajax.post(URL, {}, res => {
      console.log(res.json());
      this.departmentNameArr = res.json();
    });
  }

  addHeaderQuestionnaire() {
    console.log(this.departmentNameNew);
    console.log(this.departmentName);
    var departmentValue = '';
    if (this.departmentName != null && this.departmentName != undefined && this.departmentName != "") {
      departmentValue = this.departmentName;
    }
    if (this.departmentNameNew != null && this.departmentNameNew != undefined && this.departmentNameNew != "") {
      if (departmentValue != '') {
        this.messageBarService.errorModal('ไม่สามารถเข้าสู่ระบบได้', 'เกิดข้อผิดพลาด');
        return '';
      } else {
        departmentValue = this.departmentNameNew;
      }
    }


  }






}
