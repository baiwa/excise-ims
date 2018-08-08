import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { MessageBarService } from "../../../../common/services/message-bar.service";

import { AjaxService } from "../../../../common/services/ajax.service";
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

  datas: Condition[];

  chk: any;

  constructor(
    private ajax: AjaxService,
    private router: Router,
    private route: ActivatedRoute,
    private msg: MessageBarService

  ) {
    this.datas = [];
    this.chk = [];
    for (let i = 0; i < 3; i++) {
      this.datas.push(new Condition());
    }
  }

  ngOnInit() {
    this.headerId = this.route.snapshot.queryParams["id"];
    this.numbers = [1];
    this.minorDetail = [];

    this.initDatatable();

    // Checked ???
    $("#datatable tbody").on("click", "input", e => {
      const { id, checked } = e.target;
      if ("chk" == id.split("-")[0] && checked) {
        this.chk.push(id.split("-")[1]);
      } else {
        this.chk.splice(this.chk.findIndex(obj => id.split("-")[1] == obj), 1);
      }
    });
  }

  initDatatable() {
    const URL =
      AjaxService.CONTEXT_PATH + "ia/int02/queryQuestionnaireDetailByCriteria";
    this.datatable = $('#datatable').DataTable({
      "lengthChange": false,
      "searching": true,
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
        "data": {}
      },
      "columns": [
        {
          render: (data, type, full, meta) => {
            return `<input type="checkbox" name="chk-${
              full.qtnDetailId
              }" id="chk-${
              full.qtnDetailId
              }">`;
          },
          className: "center"
        },
        {
          "data": "qtnMainDetail",
          "className": "left"
        }
      ]

    });
  }

  clickChkAll = event => {
    var node = $('#datatable').DataTable().rows().nodes();
    if (event.target.checked) {
      $.each(node, (index, value) => {
        const id = $(value).find('input')[0].id;
        this.chk.push(id.split("-")[1]);
        $(value).find('input')[0].checked = true;
      });
    } else {
      $.each(node, (index, value) => {
        const id = $(value).find('input')[0].id;
        this.chk.splice(this.chk.findIndex(obj => id.split("-")[1] == obj), 1);
        $(value).find('input')[0].checked = false;
      });
    }
  }

  reTable = () => {
    this.chk = [];
    $("#chk").prop('checked', false);
    this.datatable.destroy();
    this.initDatatable();
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

  addRow() {
    this.datas.length < 5 && this.datas.push(new Condition());
  }

  delRow(index) {
    this.datas.splice(index, 1);
  }
}

class Condition {
  [x: string]: any;
  seq: any;
  operator: any;
  value1: any;
  value2: any;
  risk: any;
  score: any;
}
