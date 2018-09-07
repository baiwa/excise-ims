import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { MessageBarService } from "../../../../common/services/message-bar.service";

import { AjaxService } from "../../../../common/services/ajax.service";
import { BaseModel, ManageReq } from '../../../../common/models';
import { toFormData } from '../../../../common/helper';
import { Headers } from '@angular/http';
import { DialogService } from '../../../../common/services';
import { Observable } from '../../../../../../../node_modules/rxjs';
declare var $: any;

const URL = {
  SAVE: "ia/int02/save_qtn_report_detail",
  DATATABLE: AjaxService.CONTEXT_PATH + "ia/int02/queryQuestionnaireDetailByCriteria",
  DATATABLE2: "ia/int02/qtn_report_detail_by_hdr_id/datatable",
  _DATATABLE: "ia/int02/questionnaire_detail/datatable"
  //DATATABLE2: AjaxService.CONTEXT_PATH + "ia/int02/qtn_report_detail_by_hdr_id/datatable"
};

@Component({
  selector: "app-int02-3",
  templateUrl: "./int02-3.component.html",
  styleUrls: ["./int02-3.component.css"]
})
export class Int023Component implements OnInit {

  // Service Details
  minorDetail: string[] = [""];
  mainDetail: string = "";
  // API Datatable
  data: Datatable[] = [];
  // References Datatable
  datatable: any;
  option: any;
  option2: any;
  // ID from url
  headerId: number;
  // Condition
  cond: Condition[] = [];
  // Checkbox
  chk1: Int023FormVo[] = [];
  chk2: Int023FormVo[] = [];
  // Request
  req: ManageReq<Int023FormVo> = new ManageReq<Int023FormVo>();
  // Data
  table: Int023FormVo[] = [];
  _table: Int023FormVo[] = [];
  // Table
  dt1: any;
  dt2: any;

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
    this.option = {
      draw: 1,
      start: 0,
      length: 10
    };
    this.option2 = {
      draw: 1,
      start: 0,
      length: 10
    };

  }

  ngOnInit() {
    // ID from url
    this.headerId = this.route.snapshot.queryParams["id"];
    this._initialTable();
    this.initialTable();
    
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

  _initialTable(loadmore = false): void {
    if (!loadmore) {
      this._table = [];
    }
    this.ajax.post(`${URL._DATATABLE}`, toFormData(this.option), res => {
      let d = res.json().data;
      d.forEach(data => {
        let qtn = new Int023FormVo();
        qtn.qtnReportHdrId = "NOT NULL";
        qtn.qtnReportManId = `OLD_${data.qtnReportManId}`;
        qtn.qtnMainDetail = data.qtnMainDetail;
        if (this._table.findIndex(obj => obj.qtnReportHdrId == "NOT NULL" && obj.qtnReportManId == data.qtnReportManId) == -1) {
          this._table.push(qtn);
        }
        data.detail.forEach((obj, index) => {
          let _qtn = new Int023FormVo();
          _qtn.qtnReportManId = `OLD_${obj.mainId}`;
          _qtn.qtnReportDtlId = obj.qtnMinorId;
          _qtn.qtnMainDetail = obj.qtnMinorDetail;
          if (this._table.findIndex(o => o.qtnReportDtlId == obj.qtnMinorId) == -1) {
            $("#chk1").prop("checked", false);
            this._table.push(_qtn);
          }
        });
      });
    }, null, new Headers());
  }

  initialTable(loadmore = false): void {
    if (!loadmore) {
      this.table = [];
    }
    this.ajax.post(`${URL.DATATABLE2}/${this.headerId}`, toFormData(this.option2), res => {
      let d = res.json().data;
      d.forEach(data => {
        let qtn = new Int023FormVo();
        qtn.qtnReportHdrId = data.qtnReportHdrId;
        qtn.qtnReportManId = data.qtnReportManId;
        qtn.qtnMainDetail = data.qtnMainDetail;
        if (this.table.findIndex(obj => obj.qtnReportHdrId == data.qtnReportHdrId && obj.qtnReportManId == data.qtnReportManId) == -1) {
          this.table.push(qtn);
        }
        data.detail.forEach((obj,index) => {
          let qtn = new Int023FormVo();
          qtn.qtnReportManId = data.qtnReportManId;
          qtn.qtnReportDtlId = obj.qtnReportDtlId;
          qtn.qtnMainDetail = obj.qtnMainDetail;
          if (this.table.findIndex(o => o.qtnReportDtlId == obj.qtnReportDtlId) == -1) {
            $("#chk1").prop("checked", false);
            this.table.push(qtn);
          }
        });
      });
    }, null, new Headers());
  }

  chck = (id, what, e, i, table) => {
    e.preventDefault();
    switch (what) {
      case 'man':
        this[`chk${i}`] = this[`chk${i}`].filter(obj => obj.qtnReportManId != id);
        this[`${table}`].map((obj, index) => {
          if (obj.qtnReportManId == id) {
            if (e.target.checked) {
              this[`chk${i}`].push(obj);
              $(`#chk${i}-${index}`)[0].checked = true;
            } else {
              const del = this[`chk${i}`].findIndex(ob => ob.qtnReportManId == id);
              del != -1 && this[`chk${i}`].splice(del, 1);
              $(`#chk${i}-${index}`)[0].checked = false;
            }
          }
        })
        break;
      case 'dtl':
        this[`${table}`].map((obj, index) => {
          if (obj.qtnReportDtlId == id) {
            if (e.target.checked) {
              this[`chk${i}`].push(obj);
              $(`#chk${i}-${index}`)[0].checked = true;
            } else {
              const del = this[`chk${i}`].findIndex(ob => ob.qtnReportDtlId == id);
              del != -1 && this[`chk${i}`].splice(del, 1);
              $(`#chk${i}-${index}`)[0].checked = false;
            }
          }
        })
        break;
    }
    this[`${table}`].length == this[`chk${i}`].length ? $(`#chk${i}`).prop('checked', true) : $(`#chk${i}`).prop('checked', false);
  }

  chkAll = (event, table, i) => {
    if (event.target.checked) {
      this[`chk${i}`] = this[`${table}`];
      if (this[`${table}`].length > 0) {
        this[`${table}`].map((obj, index) => {
          if ($(`#chk${i}-${index}`)[0]) {
            $(`#chk${i}-${index}`)[0].checked = true;
          }
        });
      }
    } else {
      this[`chk${i}`] = [];
      if (this[`${table}`].length > 0) {
        this[`${table}`].map((obj, index) => {
          if ($(`#chk${i}-${index}`)[0]) {
            $(`#chk${i}-${index}`)[0].checked = false;
          }
        });
      }
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

  onScroll(e, option) {
    const { children, offsetHeight, scrollTop } = e.target;
    if (scrollTop >= children[0].offsetHeight - offsetHeight) {
      this[`${option}`].start += 10;
      if (option === "option") {
        this._initialTable(true);
      } else {
        this.initialTable(true);
      }
    }
  }

  onAddField = () => {
    if (this.minorDetail.length < 5) {
      this.minorDetail.push("");
    } else {
      this.msg.errorModal(
        "ไม่สามารถทำรายการได้เกิน 5 ข้อ",
        "เกิดข้อผิดพลาด"
      );
    }
  };

  onDelField = index => {
    this.minorDetail.splice(index, 1);
  }

  onSave = () => {
    this.req.save.forEach(obj => {
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
        this.initialTable();
      } else {
        this.message.errorModal(msg.messageTh);
      }
    });
  }

  onAdd2Save = (e) => {
    e.preventDefault();
    if (this.mainDetail) {
      let main: Int023FormVo = new Int023FormVo();
      let detail: Int023FormVo;
      let mainId = `MAN_${this.getRndInteger(10000, 99999)}`;
      main.qtnReportHdrId = this.headerId.toString();
      main.qtnReportManId = mainId;
      main.qtnMainDetail = this.mainDetail;
      main.qtnFor = "M";
      main.status = "NEW";
      this.mainDetail = "";
      this.table.push(main);
      this.req.save = this.table;
      for (let i = 0; i < this.minorDetail.length; i++) {
        detail = new Int023FormVo();
        detail.qtnReportDtlId = `DTL_${this.getRndInteger(10000, 99999)}`;
        detail.qtnMainDetail = $(`#minorDetail${i}`).val();
        detail.qtnReportManId = mainId;
        detail.qtnFor = "D";
        detail.status = "NEW";
        $(`#minorDetail${i}`).val("");
        this.table.push(detail);
        if (i == this.minorDetail.length - 1) {
          this.table.forEach(obj => {
            if (!obj.qtnReportHdrId) {
              obj.qtnFor = "D";
            } else {
              obj.qtnFor = "M";
            }
            this.req.save = this.table;
          });
          this.onAdd2SaveList();
        }
      }
    } else {
      this.onAdd2SaveList();
    }
  }

  onAdd2SaveList = () => {
    this.mainDetail = "";
    this.minorDetail = [""];
    for (let j = 0; j < this.chk1.length; j++) {
      if (this.chk1[j].qtnReportHdrId) {
        this.chk1[j].qtnFor = "M";
        this.chk1[j].qtnReportHdrId = this.headerId.toString();
      } else {
        this.chk1[j].qtnFor = "D";
      }
      this.chk1[j].status = "NEW";
      this.table.push(this.chk1[j]);
      if (j == this.chk1.length - 1) {
        this.chk1 = [];
        this.table.forEach(obj => {
          if (!obj.qtnReportHdrId) {
            obj.qtnFor = "D";
          } else {
            obj.qtnFor = "M";
          }
          this.req.save = this.table;
        });
      }
    }
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
  qtnReportHdrId: string = null;
  qtnReportManId: string = null;
  qtnReportDtlId: string = null;
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
