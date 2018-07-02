import { Component, OnInit } from '@angular/core';
import { AjaxService } from '../../../../common/services/ajax.service';
import { MessageBarService } from '../../../../common/services/message-bar.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { ExciseService } from '../../../../common/services/excise.service';
import { TextDateTH, digit } from '../../../../common/helper/datepicker';
import { CurrencyPipe } from '@angular/common';


declare var jQuery: any;
declare var $: any;
@Component({
  selector: 'app-int02-2',
  templateUrl: './int02-2.component.html',
  styleUrls: ['./int02-2.component.css']
})
export class Int022Component implements OnInit {

  departmentNameArr: any;
  departmentName: any;
  departmentNameNew: any;
  datatable: any;
  constructor(private ajax: AjaxService, private messageBarService: MessageBarService) {



  }

  ngOnInit() {
    this.departmentNameArr = "";
    const URL = "combobox/controller/comboboxHeaderQuestionnaire";

    this.ajax.post(URL, {}, res => {
      console.log(res.json());
      this.departmentNameArr = res.json();
    });
    this.initDatatable();
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
        this.messageBarService.errorModal('ระบบสามารถเพิ่มข้อมูลได้เพียงหนึ่งช่องทาง กรุณาเพิ่มข้อมูลใหม่', 'แจ้งเตือน');
        return '';
      } else {
        departmentValue = this.departmentNameNew;
      }
    }
    const URL = "ia/int02/addHeaderQuestionnaire";
    this.ajax.post(URL, { qtnReportHdrName: departmentValue }, res => {
      console.log(res.json());
      var message = res.json();
      if (res.json().messageType == 'E') {
        this.messageBarService.errorModal(message.messageTh, 'แจ้งเตือน');
      } else {
        this.messageBarService.success
      }


    });
    this.datatable.destroy();
    this.initDatatable();

  }

  initDatatable(): void {
    const URL = AjaxService.CONTEXT_PATH + "ia/int02/queryQtnReportHeaderByCriteria";

    this.datatable = $('#datatable').DataTable({
      "lengthChange": false,
      "searching": false,
      "select": true,
      "ordering": true,
      "pageLength": 10,
      "processing": true,
      "serverSide": true,
      "paging": true,
      "pagingType": "full_numbers",
      "ajax": {
        "type": "POST",
        "url": URL,
        "data": {


        }
      },
      "columns": [
        {
          "data": "qtnReportHdrId",
          "className": "center"
        },
        {
          "data": "qtnReportHdrName",
          "className": "center"
        },
        {
          "data": "creator",
          "className": "center"
        },
        {
          "data": "createdBy",
          "className": "center"
        }
      ]
    });
  }

}





