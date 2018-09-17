import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { Observable } from "rxjs";
import { DialogService, IaService, MessageBarService, AjaxService, AuthService } from "services/index";
import { Headers } from "@angular/http";
import { toFormData } from "helpers/index";
import { BaseModel, ManageReq, TableReq, BreadCrumb } from "models/index";

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
  RISK_TYPE: string = "QTN_MASTER";
  PAGE: string = "int02-2";

  departmentNameArr: any = "";
  departmentNameNew: any = "";
  departmentName: any;
  req: ManageReq<Datatable> = new ManageReq<Datatable>();
  datatable: Datatable[] = undefined;
  datas: Condition[] = [];
  chk: Datatable[] = [];
  chkDel: Datatable[] = [];
  qtnMaster: any;
  qtnMasterId: any;
  table: TableReq = new TableReq();
  id: any;
  private saving: boolean = false;
  private unsave: boolean = false;
  private rl: boolean = false;
  private finished: boolean = false;
  breadcrumb: BreadCrumb[];
  toggleRL: boolean = false;
  rlLen: number = 0;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private ajax: AjaxService,
    private message: MessageBarService,
    private dialog: DialogService,
    private iaService: IaService,
    private auth: AuthService
  ) {

    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "แบบสอบทานระบบการควบคุมภายใน", route: "#" },
      { label: "สร้างแบบสอบทานระบบการควบคุมภายใน", route: "int01/1" },
      { label: "เพิ่มด้านแบบสอบทาน", route: "#" },
    ];

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
    // Datatable
    this.loadTable();
    // QtnMaster Initial
    if (this.qtnMasterId !== "") {
      this.ajax.get(`${URL.FIND_MASTER}/${this.qtnMasterId}`, res => {
        this.finished = true; // can click saved() `send questionaire`
        this.qtnMaster = res.json();
        var url = "ia/condition/findConditionByParentId";
        this.ajax.post(url, { parentId: this.qtnMasterId, riskType: this.RISK_TYPE, page: this.PAGE }, res => {
          var data = res.json();
          if (data != undefined && data.length > 0) {
            this.rlLen = data.length;
            this.rl = true;
          } else {
            this.rl = false;
          }
        });
      });
    } else {
      this.unsave = true;
      this.qtnMaster = this.iaService.getData();
    }
    // Combobox
    const request = {
      type: ""
    };
    this.ajax.post(URL.COMBOBOX, request, res => {
      this.departmentNameArr = res.json();
    });
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
      data.qtnReportHdrId = `NEW_${this.getRndInteger(10000, 99999)}`;
      data.qtnReportHdrName = departmentValue;
      data.createdBy = this.auth.getUser().username;
      data.status = "NEW";
      this.datatable.push(data);
      // After Saved ** Cleared Form
      $("#departmentName").dropdown("restore defaults");
      this.departmentNameNew = "";
      this.unsave = true; // change status `unsave`
      $("#chk").prop("checked", false);
    } else {
      alert("ระบบสามารถเพิ่มข้อมูลได้เพียงหนึ่งช่องทาง กรุณาเพิ่มข้อมูลใหม่");
    }
  }

  onDelete(): void {
    this.message.comfirm(foo => {
      if (foo) {
        this.chk.forEach(obj_ => {
          if (obj_.status === undefined) {
            this.chkDel.push(obj_);
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
          this.req.save = this.datatable; // save object to save 
          this.ajax.post(URL.SAVE_REPORT, this.req, res => {
            const msg = res.json();
            this.table = new TableReq();
            this.req = new ManageReq<Datatable>();
            this.unsave = false; // change status `unsave`
            this.saving = false; // hide loading button
            this.loadTable(); // Datatable
            if (msg.messageType == "C") {
              this.message.successModal(msg.messageTh);
              if (!this.route.snapshot.queryParams["id"]) {
                this.router.navigate(
                  [`/int02/2`],
                  { queryParams: { id: this.qtnMasterId } }
                );
              }
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
          this.req.save = this.datatable; // save object to save 
          this.req.delete = this.chkDel; // save object to delete 
          this.chkDel = []; // after save clear delete list
          this.ajax.post(URL.SAVE_REPORT, this.req, res => {
            const msg = res.json();
            this.table = new TableReq();
            this.req = new ManageReq<Datatable>();
            this.unsave = false; // change status `unsave`
            this.saving = false; // hide loading button
            this.loadTable(); // Datatable
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
          const response = res.json();
          const { messageTh, messageType } = response.msg;
          if (messageType == "C") {
            this.message.successModal(messageTh);
            this.router.navigate(['/int02/1']);
          } else {
            this.message.errorModal(messageTh);
          }
        });
      }
    }, "ต้องส่งแบบสอบทานหรือไม่ ? ");
  }

  onCancel(): void {
    this.router.navigate(['/int02/1']);
  }

  loadTable(): void {
    this.ajax.post(`${URL.DATATABLE}/${this.qtnMasterId}`, toFormData(this.table), res => {
      let len: number = parseInt(res.json().recordsTotal) / 5;
      this.table.recordsTotal = Math.ceil(len);
      this.datatable = res.json().data;
    }, null, new Headers());
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

  notNullDepartment(): boolean {
    return !this.isNotNull(this.departmentName) && !this.isNotNull(this.departmentNameNew);
  }

  setRL(e) {
    if (e == 0) {
      this.toggleRL = true;
    } else {
      this.toggleRL = false;
    }
  }

  chkRL(e) {
    this.rl = e;
    var url = "ia/condition/findConditionByParentId";
    this.ajax.post(url, { parentId: this.qtnMasterId, riskType: this.RISK_TYPE, page: this.PAGE }, res => {
      var data = res.json();
      if (data != undefined && data.length > 0) {
        this.rlLen = data.length;
        this.rl = true;
      } else {
        this.rl = false;
      }
    });
  }

  pageChange(e) {
    // Change Table Object
    this.table.start = e - 5;
    // Loading Table
    this.loadTable();
  }

}

class Datatable extends BaseModel {
  [x: string]: any;
  qtnReportHdrId: any;
  qtnReportHdrName: string;
  creator: string;
  qtnMasterId: any;
  hasChild: any = "FALSE";
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