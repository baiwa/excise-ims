import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { Observable } from "rxjs";
import { DialogService, IaService, MessageBarService, AjaxService, AuthService } from "../../../../common/services";
import { Headers } from "@angular/http";
import { toFormData } from "../../../../common/helper";
import { BaseModel } from "../../../../common/models";

declare var $: any;

const URL = {
  DELETE_REPORT: "ia/int02/delete_qtn_report_header",
  SAVE_REPORT: "ia/int02/save_qtn_report_header",
  FIND_MASTER: "ia/int02/qtn_master_by_id",
  SAVE_MASTER: "ia/int02/save_qtn_master",
  DELETE_MASTER: "ia/int02/delete_qtn_master",
  UPDATE_MASTER: "ia/int02/update_qtn_master",
  COMBOBOX: "combobox/controller/comboboxHeaderQuestionnaire",
  // DATATABLE: `${AjaxService.CONTEXT_PATH}ia/int02/qtn_report_header_by_master_id/datatable`
  DATATABLE: `ia/int02/qtn_report_header_by_master_id/datatable`
}

@Component({
  selector: "app-int02-2",
  templateUrl: "./int02-2.component.html",
  styleUrls: ["./int02-2.component.css"]
})
export class Int022Component implements OnInit {
  departmentNameArr: any = "";
  departmentNameNew: any = "";
  departmentName: any;
  datatable: Datatable[];
  datas: Condition[] = [];
  chk: Datatable[] = [];
  chkDel: any = [];
  qtnMaster: any;
  qtnMasterId: any;
  table: any;
  id: any;
  private saving: boolean = false;
  private unsave: boolean = false;
  private finished: boolean = false;
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private ajax: AjaxService,
    private message: MessageBarService,
    private dialog: DialogService,
    private iaService: IaService,
    private auth: AuthService
  ) {

    for (let i = 0; i < 3; i++) {
      this.datas.push(new Condition());
    }

    window.addEventListener("beforeunload", (e) => {
      const confirmationMessage = "\o/";
      if (this.unsave) {
        (e || window.event).returnValue = confirmationMessage;
        return confirmationMessage;
      }
    });

    // Initial Table Request
    this.table = {
      draw: 1,
      start: 0,
      length: 5
    };

  }

  ngOnInit(): void {
    // Dropdown
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    // Initial Page
    this.init();
  }

  canDeactivate(): Observable<boolean> | boolean {
    if (this.unsave) {
      let confirm: any = this.dialog.confirm("ต้องการออกจากที่นี่หรือไม่?");
      if (confirm.value) {
        console.log("Exited...");
      }
      return confirm;
    }
    return true;
  }

  init() {
    // QtnMasterId
    this.qtnMasterId = this.route.snapshot.queryParams["id"] || "";
    // QtnMaster Initial
    if (this.qtnMasterId !== "") {
      this.ajax.get(`${URL.FIND_MASTER}/${this.qtnMasterId}`, res => {
        this.finished = true; // can click saved() `send questionaire`
        this.qtnMaster = res.json();
      });
    } else {
      this.unsave = true;
      this.qtnMaster = this.iaService.getData();
    }
    // Combobox
    this.ajax.post(URL.COMBOBOX, {}, res => {
      this.departmentNameArr = res.json();
    });
    // Datatable
    this.ajax.post(`${URL.DATATABLE}/${this.qtnMasterId}`, toFormData(this.table), res => {
      this.datatable = res.json().data;
    }, null, new Headers());
  }

  onAdd(event: any): void {
    event.preventDefault();
    var departmentValue = "";
    if (this.isNotNull(this.departmentName) || this.isNotNull(this.departmentNameNew)) {
      // Checking not value
      if (this.isNotNull(this.departmentNameNew)) {
        departmentValue = this.departmentNameNew;
      }
      if (this.isNotNull(this.departmentName)) {
        departmentValue = this.departmentName;
      }
      // Add Data to Datatable Array
      const data = new Datatable();
      data.qtnReportHdrId = `NEW_${this.getRndInteger(100, 999)}`;
      data.qtnReportHdrName = departmentValue;
      data.createdBy = this.auth.getUser().username;
      data.status = "NEW";
      this.datatable.push(data);
      // After Saved ** Cleared Form
      $("#departmentName").dropdown("restore defaults");
      this.departmentNameNew = "";
      this.unsave = true; // change status `unsave`
    } else {
      alert("ระบบสามารถเพิ่มข้อมูลได้เพียงหนึ่งช่องทาง กรุณาเพิ่มข้อมูลใหม่");
    }
  }
  
  onDelete(): void {
    this.message.comfirm(foo => {
      // let msg = "";
      if (foo) {
        this.chk.forEach(obj_ => {
          if (obj_.status === undefined) {
            this.chkDel.push(obj_.qtnReportHdrId);
          }
          this.datatable.splice(this.datatable.findIndex(_obj => _obj.qtnReportHdrId == obj_.qtnReportHdrId), 1);
        });
        this.chk = [];
        $("#chk").prop("checked", false);
        this.unsave = true; // change status `unsave`
      }
    }, "ต้องลบหรือไม่ ? ");
  }
  
  onSave(): void {
    this.saving = true; // show loading button
    if (this.qtnMaster.qtnMasterId == undefined) {
      this.ajax.post(URL.SAVE_MASTER, this.qtnMaster, res => {
        this.finished = true; // can click saved() `send questionaire`
        const id = res.json().data.qtnMasterId;
        this.qtnMaster = res.json().data;
        this.qtnMasterId = id;
        if (this.datatable != []) {
          this.datatable.forEach(each => {
            if (each.status !== undefined) {
              each.qtnReportHdrId = null;
              each.qtnMasterId = id;
            }
          });
          this.ajax.post(URL.SAVE_REPORT, { data: this.datatable }, res => {
            const msg = res.json();
            this.unsave = false; // change status `unsave`
            this.saving = false; // hide loading button
            if (msg.messageType == "C") {
              this.message.successModal(msg.messageTh);
            } else {
              this.message.errorModal(msg.messageTh);
            }
          });
        } else {
          this.saving = false; // hide loading button
        }
      });
    } else {
      const id = this.qtnMasterId;
      if (this.datatable != []) {
        this.datatable.forEach(each => {
          if (each.status !== undefined) {
            each.qtnReportHdrId = null;
            each.qtnMasterId = id;
          }
        });
        if (this.unsave) {
          if (this.chkDel.length != 0) {
            this.ajax.delete(`${URL.DELETE_REPORT}/${this.chkDel.toString()}`, res => {
              const msg = res.json();
              this.chkDel = []; // clear delete list
              if (msg.messageType == "C") {
                this.message.successModal(msg.messageTh);
              } else {
                this.message.errorModal(msg.messageTh);
              }
            });
          }
          this.ajax.post(URL.SAVE_REPORT, { data: this.datatable }, res => {
            const msg = res.json();
            this.unsave = false; // change status `unsave`
            this.saving = false; // hide loading button
            if (msg.messageType == "C") {
              this.message.successModal(msg.messageTh);
            } else {
              this.message.errorModal(msg.messageTh);
            }
          });
        }
      }
    }
  }

  onSaved(): void {
    this.message.comfirm(foo => {
      if (foo) {
        this.qtnMaster.qtnFinished = "Y";
        this.ajax.post(`${URL.UPDATE_MASTER}/${this.qtnMasterId}`, this.qtnMaster, res => {
          console.log(res.json());
          alert("ส่งแบบสอบถามสำเร็จเรียบร้อยแล้ว");
        });
      }
    }, "ต้องลบหรือไม่ ? ");
  }
  
  onCancel(): void {
    this.router.navigate(['/int02/1']);
  }

  isNotNull(variables): boolean {
    return variables != null && variables != undefined && variables != "" && variables != 0;
  }

  clickChk = (event, index) => {
    if (event.target.checked) {
      this.chk.push(this.datatable[index]);
    } else {
      $("#chk").prop("checked", false);
      this.chk.splice(this.chk.findIndex(ob => ob.qtnReportHdrId == this.datatable[index].qtnReportHdrId), 1);
    }
  }

  clickChkAll = event => {
    if (event.target.checked) {
      this.datatable.map((obj, index) => {
        this.chk.push(obj);
        $(`#chk${index}`).prop('checked', true);
      });
    } else {
      this.datatable.map((obj, index) => {
        $(`#chk${index}`).prop('checked', false);
      });
      this.chk = [];
    }
  }

  linkToDetail(headerId): void {
    this.router.navigate(["/int02/3"], {
      queryParams: {
        id: headerId
      }
    });
  }

  setDepartmentName(what): void {
    switch (what) {
      case "new":
        $("#departmentName").dropdown("restore defaults");
        break;
      case "old":
        this.departmentNameNew = "";
        break;
    }
  }

  addRow(): void {
    this.datas.length < 5 && this.datas.push(new Condition());
  }

  delRow(index): void {
    this.datas.splice(index, 1);
  }

  getRndInteger(min, max) {
    return Math.floor(Math.random() * (max - min)) + min;
  }

}

class Datatable extends BaseModel {
  [x: string]: any;
  qtnReportHdrId: any;
  qtnReportHdrName: string;
  creator: string;
  qtnMasterId: any;
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