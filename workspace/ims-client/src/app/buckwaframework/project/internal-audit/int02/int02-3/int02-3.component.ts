import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { MessageBarService } from "../../../../common/services/message-bar.service";

import { ExciseService } from "../../../../common/services/excise.service";
import { AjaxService } from "../../../../common/services/ajax.service";

declare var jQuery: any;
declare var $: any;

@Component({
  selector: "app-int02-3",
  templateUrl: "./int02-3.component.html",
  styleUrls: ["./int02-3.component.css"]
})
export class Int023Component implements OnInit {
  //private count: number = 1;
  numbers: number[];

  minorDetail: String[];
  mainDetail: string;

  datatable: any;

  headerId: number;

  constructor(
    private ajax: AjaxService,
    private router: Router,
    private route: ActivatedRoute,
    private msg: MessageBarService
    
  ) { }

  ngOnInit() {
    this.headerId = this.route.snapshot.queryParams["id"];

    var router = this.router;
    console.log("initDatatable");
    const URL =
      AjaxService.CONTEXT_PATH + "ia/int02/queryQuestionnaireDetailByCriteria";
    this.datatable = $('#datatable').DataTable({
      "lengthChange": false,
      "searching": false,
      "select": true,
      "ordering": false,
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
          render: function(data, type, full, meta) {
            return `<input type="checkbox" name="chk${meta.row}" id="chk${
              meta.row
            }" value="${$("<div/>")
              .text(data)
              .html()}">`;
          },
          className: "center"
        },
        {
          "data": "qtnMainDetail",
          "className": "left"
        }
      ]
  
    });
    



    /*-------add row--------*/
    this.numbers = [1];
    this.minorDetail = [];


  }

  








  onAddField = () => {
    let num = this.numbers.length;
    if (num < 5) {
      this.numbers.push(num + 1);
      this.minorDetail.push("");
    } else {
      this.msg.errorModal(
        "ไม่สามารถทำรายการได้เกิน 5 ข้อ",
        "เกิดข้อผิดพลาด"
      );
    }
  };
  onDelField = index => {
    this.numbers.splice(index, 1);
    this.minorDetail.splice(index, 1);
  };

  onSend = () => {

    this.mainDetail = (<HTMLInputElement>document.getElementById("mainDetail")).value;
    console.log("mainDetail :: " + this.mainDetail)
    console.log(this.minorDetail);

    /*--------------------Go Send back-end-----------------------------------*/
    const insretURL = "ia/int02/createQuestionnaireDetail";
    this.ajax.post(insretURL, {
      mainDetail: this.mainDetail,
      minorDetail: this.minorDetail
    }, res => {
      console.log(res.json());
      var message = res.json();
      console.log(message.messageType);
      if (message.messageType == 'E') {
        this.msg.errorModal(message.messageTh, 'แจ้งเตือน');
      } else {
        this.msg.successModal(message.messageTh, 'บันทึกข้อมูลสำเร็จ');
      }
    });
  };
}
