import { Router, ActivatedRoute } from "@angular/router";
import { Component, OnInit, ViewChild, ElementRef, QueryList, ViewChildren, AfterViewInit } from "@angular/core";
import { Headers } from "@angular/http";
import { Observable } from "rxjs";

import { DialogService, IaService, MessageBarService, AjaxService, AuthService } from "services/index";
import { BaseModel, ManageReq, TableReq, BreadCrumb } from "models/index";
import { toFormData, TextDateTH, formatter } from "helpers/index";
import { QtnMaster, Int022FormVo } from "../int02.models";
import { stringToDate, toDateLocale, digit } from "helpers/datepicker";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";

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
export class Int022Component implements OnInit, AfterViewInit {
  RISK_TYPE: string = "QTN_MASTER";
  PAGE: string = "int02-2";
  @ViewChildren('r') r: QueryList<ElementRef>;
  @ViewChild('to') to: ElementRef;
  @ViewChild('from') from: ElementRef;
  formGroup: FormGroup;

  departmentNameArr: any = "";
  departmentNameNew: any = "";
  departmentName: any;
  req: ManageReq<Datatable> = new ManageReq<Datatable>();
  datatable: Datatable[] = undefined;
  datas: Condition[] = [];
  chk: Datatable[] = [];
  chkDel: Datatable[] = [];
  qtnMaster: QtnMaster;
  qtnMasterId: any;
  table: TableReq = new TableReq();
  id: any;
  private saving: boolean = false;
  private unsave: boolean = false;
  private rl: boolean = false;
  private finished: boolean = false;
  private submitted: boolean = false;
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
    private auth: AuthService,
    private authService: AuthService,
    private formBuilder: FormBuilder
  ) {

    this.formGroup = formBuilder.group({
      to: ['', Validators.required],
      from: ['', Validators.required],
      cal1: [{ value: '', disabled: true }],
      cal2: [{ value: '', disabled: true }],
      cal3: [{ value: '', disabled: true }],
      chk1: [{ value: false, disabled: true }],
      chk2: [{ value: false, disabled: true }],
      chk3: [{ value: false, disabled: true }]
    })

    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "แบบสอบถามระบบการควบคุมภายใน", route: "#" },
      { label: "สร้างแบบสอบถามระบบการควบคุมภายใน", route: "#" },
      { label: "เพิ่ม/แก้ไข ด้านแบบสอบถาม", route: "#" },
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
    this.authService.reRenderVersionProgram('INT-02200');
    // Dropdown
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    // Initial Page
    this.init();
  }

  ngAfterViewInit(): void {
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

  async init() {
    // QtnMasterId
    this.qtnMasterId = await this.route.snapshot.queryParams["id"] || "";
    // Datatable
    this.loadTable();
    // QtnMaster Initial
    if (this.qtnMasterId !== "") {
      this.ajax.get(`${URL.FIND_MASTER}/${this.qtnMasterId}`, res => {
        this.finished = true; // can click saved() `send questionaire`
        this.qtnMaster = res.json() as QtnMaster;
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
      const data: QtnMaster = {
        qtnMasterId: 0,
        qtnName: "สสส",
        qtnSector: "01",
        qtnYear: "2561"
      };
      this.qtnMaster = data;
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
        this.qtnMaster = res.json().data as QtnMaster;
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
    this.formGroup.get('to').setValue("");
    this.formGroup.get('from').setValue("");
    this.formGroup.get('cal1').setValue("");
    this.formGroup.get('cal2').setValue("");
    this.formGroup.get('cal3').setValue("");
    $('#saved').modal('show');
    $("#fromCalendar").calendar({
      type: "date",
      minDate: new Date(),
      endCalendar: $("#toCalendar"),
      text: TextDateTH,
      formatter: formatter("date"),
      onChange: (date, text, mode) => {
        $('#from').val(text);
        this.formGroup.get('from').patchValue(text);
        if ($('#to').val()) {
          const day = this.datediff(date, stringToDate($('#to').val()));
          const from = stringToDate($('#from').val());
          const _date = new Date();
          let _dates;
          if (day > 3) {
            _dates = {
              date1: this.nextDate(from, Math.floor(day / 3)),
              date2: this.nextDate(from, Math.floor(day / 2)),
              date3: this.nextDate(from, Math.floor(day / 1)),
            }
          } else {
            if (day == 2) {
              _dates = {
                date1: this.nextDate(from, 1),
                date2: this.nextDate(from, 2),
                date3: ""
              }
            } else {
              _dates = {
                date1: this.nextDate(from, 1),
                date2: this.nextDate(from, 2),
                date3: this.nextDate(from, 3),
              }
            }
          }
          $("#r1Calendar").calendar(this.propertyCalendar(date, stringToDate($('#to').val()), 1));
          $("#r2Calendar").calendar(this.propertyCalendar(date, stringToDate($('#to').val()), 2));
          $("#r3Calendar").calendar(this.propertyCalendar(date, stringToDate($('#to').val()), 3));
          this.formGroup.get('cal1').setValue(_dates.date1);
          this.formGroup.get('cal2').setValue(_dates.date2);
          this.formGroup.get('cal3').setValue(_dates.date3);
          this.formGroup.get('chk1').enable();
          this.formGroup.get('chk2').enable();
          this.formGroup.get('chk3').enable();
        }
      }
    });
    $("#toCalendar").calendar({
      type: "date",
      minDate: new Date(),
      startCalendar: $("#fromCalendar"),
      text: TextDateTH,
      formatter: formatter("date"),
      onChange: (date, text, mode) => {
        $('#to').val(text);
        this.formGroup.get('to').patchValue(text);
        if ($('#from').val()) {
          const day = this.datediff(stringToDate($('#from').val()), date);
          const from = stringToDate($('#from').val());
          let _dates;
          if (day > 3) {
            _dates = {
              date1: this.nextDate(from, Math.floor(day / 3)),
              date2: this.nextDate(from, Math.floor(day / 2)),
              date3: this.nextDate(from, Math.floor(day / 1)),
            }
          } else {
            if (day == 2) {
              _dates = {
                date1: this.nextDate(from, 1),
                date2: this.nextDate(from, 2),
                date3: ""
              }
            } else {
              _dates = {
                date1: this.nextDate(from, 1),
                date2: this.nextDate(from, 2),
                date3: this.nextDate(from, 3),
              }
            }
          }
          $("#r1Calendar").calendar(this.propertyCalendar(from, date, 1));
          $("#r2Calendar").calendar(this.propertyCalendar(from, date, 2));
          $("#r3Calendar").calendar(this.propertyCalendar(from, date, 3));
          this.formGroup.get('cal1').setValue(_dates.date1);
          this.formGroup.get('cal2').setValue(_dates.date2);
          this.formGroup.get('cal3').setValue(_dates.date3);
          this.formGroup.get('chk1').enable();
          this.formGroup.get('chk2').enable();
          this.formGroup.get('chk3').enable();
        }
      }
    });
  }

  saveToFinish() {
    this.submitted = true;
    this.formGroup.get('cal1').enable();
    this.formGroup.get('cal2').enable();
    this.formGroup.get('cal3').enable();
    if (this.formGroup.valid) {
      const { to, from, cal1, cal2, cal3 } = this.formGroup.value;
      console.log(this.formGroup.value);
      const { qtnMasterId, qtnName, qtnSector, qtnYear } = this.qtnMaster;
      const dataRequest: Int022FormVo = {
        alerts: [
          { qtnAlertId: null, qtnAlertTime: stringToDate(cal1), qtnMasterId: qtnMasterId, qtnTimes: 1, status: "N" },
          { qtnAlertId: null, qtnAlertTime: stringToDate(cal2), qtnMasterId: qtnMasterId, qtnTimes: 2, status: "N" },
          { qtnAlertId: null, qtnAlertTime: stringToDate(cal3), qtnMasterId: qtnMasterId, qtnTimes: 3, status: "N" },
        ],
        qtnMasterId: qtnMasterId,
        qtnName: qtnName,
        qtnSector: qtnSector,
        qtnYear: qtnYear,
        qtnStart: stringToDate(from),
        qtnEnd: stringToDate(to),
      }
      this.ajax.post(`${URL.UPDATE_MASTER}/${this.qtnMasterId}`, dataRequest, res => {
        const response = res.json();
        const { messageTh, messageType } = response.msg;
        if (messageType == "C") {
          this.message.successModal(messageTh);
          this.router.navigate(['/int02/1']);
        } else {
          this.message.errorModal(messageTh);
        }
      }, err => {
        console.error(err);
        this.message.errorModal("มันพังอ่าาา");
      });
    }
  }

  dateToStrddMMyyyy = (date) => {
    let day = date.getDate();
    let _month = toDateLocale(date)[0].split("/")[1];
    let _year = toDateLocale(date)[0].split("/")[2];
    return digit(day) + "/" + digit(_month) + "/" + _year.toString();
  }

  nextDate(_date: Date, num: number) {
    let date = new Date();
    date.setDate(_date.getDate() + num);
    return this.dateToStrddMMyyyy(date);
  }

  datediff(first, second) {
    return Math.round((second - first) / (1000 * 60 * 60 * 24));
  }

  propertyCalendar(min: Date, max: Date, index: number) {
    return {
      type: "date",
      minDate: min,
      maxDate: max,
      text: TextDateTH,
      formatter: formatter()
    };
  }

  onCancel(): void {
    this.router.navigate(['/int02/1']);
  }

  loadTable(): void {
    this.ajax.post(`${URL.DATATABLE}/${this.qtnMasterId}`, toFormData(this.table), async res => {
      let len: number = await parseInt(res.json().recordsTotal) / 5;
      this.table.recordsTotal = await Math.ceil(len);
      this.datatable = await res.json().data;
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

  toggleChk(i, e) {
    if (e.target.checked == true) {
      this.formGroup.get(`cal${i}`).enable();
    } else {
      this.formGroup.get(`cal${i}`).disable();
    }
  }

  pageChange(e) {
    // Change Table Object
    this.table.start = e - 5;
    // Loading Table
    this.loadTable();
  }

  controlValid(control) {
    return this.formGroup.get(control).invalid && this.submitted;
  }

  get noDateFromTo() {
    if (this.formGroup.get('to').value == '' || this.formGroup.get('from').value == '') {
      return true;
    } else {
      return null;
    }
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