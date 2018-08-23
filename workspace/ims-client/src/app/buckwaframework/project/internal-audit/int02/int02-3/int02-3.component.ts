import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { MessageBarService } from "../../../../common/services/message-bar.service";

import { AjaxService } from "../../../../common/services/ajax.service";
import { BaseModel, ManageReq } from '../../../../common/models';
import { toFormData } from '../../../../common/helper';
import { Headers } from '../../../../../../../node_modules/@angular/http';
import { DialogService } from '../../../../common/services';
import { Observable } from '../../../../../../../node_modules/rxjs';
declare var $: any;

const URL = {
  SAVE: "ia/int02/save_qtn_report_detail",
  DATATABLE: AjaxService.CONTEXT_PATH + "ia/int02/queryQuestionnaireDetailByCriteria",
  DATATABLE2: "ia/int02/qtn_report_detail_by_hdr_id/datatable"
  //DATATABLE2: AjaxService.CONTEXT_PATH + "ia/int02/qtn_report_detail_by_hdr_id/datatable"
};

@Component({
  selector: "app-int02-3",
  templateUrl: "./int02-3.component.html",
  styleUrls: ["./int02-3.component.css"]
})
export class Int023Component implements OnInit {

  numbers: number[] = [1];
  // Service Details
  minorDetail: string[] = [];
  mainDetail: string = "";
  // API Datatable
  data: Datatable[] = [];
  datas: any[] = [{text: "5555"}, {text: "5555"}];
  // References Datatable
  datatable: any;
  option: any;
  option2: any;
  // ID from url
  headerId: number;
  // Condition
  cond: Condition[] = [];
  // Checkbox
  chk: any[] = [];
  chk2: Int023FormVo[] = [];
  // Request
  req: ManageReq<Int023FormVo> = new ManageReq<Int023FormVo>();
  // Data
  table: Int023FormVo[] = [];

  constructor(
    private ajax: AjaxService,
    private router: Router,
    private route: ActivatedRoute,
    private msg: MessageBarService,
    private dialog: DialogService,
    private message: MessageBarService
  ) {
    for (let i = 0; i < 3; i++) {
      this.cond.push(new Condition());
    }

    window.addEventListener("beforeunload", (e) => {
      const confirmationMessage = "\o/";
      if (this.unsave()) {
        (e || window.event).returnValue = confirmationMessage;
        return confirmationMessage;
      }
    });

    // Initial Table Request
    this.option2 = {
      draw: 1,
      start: 0,
      length: 5
    };
  }

  ngOnInit() {
    // ID from url
    this.headerId = this.route.snapshot.queryParams["id"];

    this.initDatatable();
    this.initializeTable();

    // Checked chk
    $("#datatable tbody").on("click", "input", e => {
      const { id, checked } = e.target;
      if ("chk" == id.split("-")[0] && checked) {
        this.chk.push(id.split("-")[1]);
      } else {
        this.chk.splice(this.chk.findIndex(obj => id.split("-")[1] == obj), 1);
      }
    });

  }

  canDeactivate(): Observable<boolean> | boolean {
    if (this.unsave()) {
      let confirm: any = this.dialog.confirm("ต้องการออกจากที่นี่หรือไม่?");
      if (confirm.value) {
        console.log("Exited...");
      }
      return confirm;
    }
    return true;
  }

  unsave() {
    return this.req.save.length != 0 || this.req.delete.length != 0;
  }

  initDatatable() {
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
        "url": URL.DATATABLE,
        "data": {}
      },
      "columns": [
        {
          render: (data, type, full, meta) => {
            if (parseInt(full.qtnDetailId) % 2 == 0)
              return `<input type="checkbox" name="chk-${full.qtnDetailId}" id="chk-${full.qtnDetailId}">`;
            else
              return `<input type="checkbox" style="margin-left: 2em" name="chk-${full.qtnDetailId}" id="chk-${full.qtnDetailId}">`;
          },
          className: "left"
        },
        {
          "data": "qtnMainDetail",
          "className": "left"
        }
      ],
      "drawCallback": (e, settings) => {
        const data = e.json.data;
        this.data = data;
      }
    });
  }

  initializeTable(): void {
    this.table = [];
    this.ajax.post(`${URL.DATATABLE2}/${this.headerId}`, toFormData(this.option2), res => {
      let d = res.json().data;
      d.forEach(data => {
        let qtn = new Int023FormVo();
        qtn.qtnReportHdrId = data.qtnReportHdrId;
        qtn.qtnReportManId = data.qtnReportManId;
        qtn.qtnMainDetail = data.qtnMainDetail;
        this.table.push(qtn);
        data.detail.forEach(obj => {
          let qtn = new Int023FormVo();
          qtn.qtnReportManId = data.qtnReportManId;
          qtn.qtnReportDtlId = obj.qtnReportDtlId;
          qtn.qtnMainDetail = obj.qtnMainDetail;
          this.table.push(qtn);
        });
      });
    }, null, new Headers());
  }

  clickChkAll = (event, id) => {
    var node = $(`#datatable${id}`).DataTable().rows().nodes();
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

  chck = (id, what, e) => {
    e.preventDefault();
    switch (what) {
      case 'man':
        this.chk2 = this.chk2.filter(obj => obj.qtnReportManId != id);
        this.table.map((obj, index) => {
          if (obj.qtnReportManId == id) {
            if (e.target.checked) {
              this.chk2.push(obj);
              $(`#chk2-${index}`)[0].checked = true;
            } else {
              const del = this.chk2.findIndex(ob => ob.qtnReportManId == id);
              del != -1 && this.chk2.splice(del, 1);
              $(`#chk2-${index}`)[0].checked = false;
            }
          }
        })
        break;
      case 'dtl':
        this.table.map((obj, index) => {
          if (obj.qtnReportDtlId == id) {
            if (e.target.checked) {
              this.chk2.push(obj);
              $(`#chk2-${index}`)[0].checked = true;
            } else {
              const del = this.chk2.findIndex(ob => ob.qtnReportDtlId == id);
              del != -1 && this.chk2.splice(del, 1);
              $(`#chk2-${index}`)[0].checked = false;
            }
          }
        })
        break;
    }
    const { table, chk2 } = this;
    table.length == chk2.length ? $("#chk2").prop('checked', true) : $("#chk2").prop('checked', false);
  }

  chkAll = (event) => {
    if (event.target.checked) {
      this.chk2 = this.table;
      this.table.map((obj, index) => {
        $(`#chk2-${index}`)[0].checked = true;
      });
    } else {
      this.chk2 = [];
      this.table.map((obj, index) => {
        $(`#chk2-${index}`)[0].checked = false;
      });
    }
  }

  delete = () => {
    this.msg.comfirm(boo => {
      if (boo) {
        this.chk2.forEach(obj => {
          if (obj.qtnReportHdrId) {
            obj.qtnFor = "M";
          } else {
            obj.qtnFor = "D";
          }
          if (obj.status === undefined) {
            this.req.delete.push(obj);
          }
          this.table = this.table.filter(ob => ob != obj);
          this.req.save = this.table;
        });
        this.chk2 = [];
        $("#chk2").prop('checked', false);
      }
    }, "ต้องการลบข้อมูลจริงหรือไม่?");
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
  }

  onSave = () => {
    this.req.save.forEach( obj => {
      if (obj.status !== undefined) {
        if (obj.qtnFor === "M") {
          obj.qtnReportManId = null;
        } else {
          obj.qtnReportManId = null;
          obj.qtnReportDtlId = null;
        }
      }
    });
    this.ajax.post(URL.SAVE, this.req, res => {
      this.req = new ManageReq<Int023FormVo>();
      const msg = res.json();
      if (msg.messageType == "C") {
        this.message.successModal(msg.messageTh);
        this.initializeTable();
      } else {
        this.message.errorModal(msg.messageTh);
      }
    });
  }

  onAdd2Save = (e) => {
    e.preventDefault();
    let main: Int023FormVo = new Int023FormVo();
    let detail: Int023FormVo;
    let mainId = `MAN_${this.getRndInteger(10000, 99999)}`;
    main.qtnReportHdrId = this.headerId.toString();
    main.qtnReportManId = mainId;
    main.qtnMainDetail = this.mainDetail;
    main.qtnFor = "M";
    main.status = "NEW";
    this.table.push(main);
    for(let i=0; i<this.numbers.length; i++) {
      detail = new Int023FormVo();
      detail.qtnReportDtlId = `DTL_${this.getRndInteger(10000, 99999)}`;
      detail.qtnMainDetail = this.minorDetail[i];
      detail.qtnReportManId = mainId;
      detail.qtnFor = "D";
      detail.status = "NEW";
      this.table.push(detail);
    }
    this.table.forEach( obj => {
      this.mainDetail = "";
      this.minorDetail = [];
      this.numbers = [1];
      if (obj.qtnReportHdrId === undefined) {
        obj.qtnFor = "D";
        this.req.save = this.table;
      } else {
        obj.qtnFor = "M";
        this.req.save = this.table;
      }
    });
  }

  addCond() {
    this.cond.length < 5 && this.cond.push(new Condition());
  }

  delCond(index) {
    this.cond.splice(index, 1);
  }

  getRndInteger(min, max) {
    return Math.floor(Math.random() * (max - min)) + min;
  }

}

class Datatable extends BaseModel {
  qtnMainDetail: any;
  qtnReportDtlId: any;
  qtnReportHdrId: any;
}

class Int023FormVo extends BaseModel {
  [x: string]: any;
  qtnReportHdrId: string;
  qtnReportManId: string;
  qtnReportDtlId: string;
  qtnMainDetail: string = "";
  qtnFor: string = "";
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
