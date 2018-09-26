import { Component, OnInit } from "@angular/core";
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from "@angular/router";
import { Questionnaire, _Questionnaire } from "./int02-m2.mock";
import { AjaxService, MessageBarService } from "../../../../common/services";
import { BaseModel, ManageReq, BreadCrumb } from 'models/index';
declare var $: any;

const URL = {
  DATA: "ia/int02m2/data",
  SAVE: "ia/int02m2/save",
  SAVED: "ia/int02m2/saved"
};

@Component({
  selector: "app-int02-m2",
  // host: { '(window:keydown)': 'hotkeys($event)' },
  templateUrl: "./int02-m2.component.html",
  styleUrls: ["./int02-m2.component.css"]
})
export class Int02M2Component implements OnInit {

  questionnaire: Questionnaire[] = [];
  loading: boolean = true;
  saving: boolean = false;
  savings: boolean = false;

  // BreadCrumb
  breadcrumb: BreadCrumb[];

  constructor(
    private ajax: AjaxService, 
    private msg: MessageBarService
  ) {
    // TODO
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "แบบสอบทานระบบการควบคุมภายใน", route: "#" },
      { label: "สร้างแบบสอบทานระบบการควบคุมภายใน", route: "#" },
      { label: "เพิ่ม/แก้ไข ด้านแบบสอบทาน", route: "#" },
      { label: "เพิ่ม/แก้ไข รายละเอียดแบบสอบทาน", route: "#" },
    ];

  }

  /* hotkeys(event) { // On KeyDown
    if ((event.ctrlKey || event.metaKey) && event.which == 83) {
      event.preventDefault();
      $("#form").submit((event) => {
        this.onSubmit(event);
      }).submit();
    } else {
      return event;
    }
  } */

  ngOnInit() {
    this.questionnaire = _Questionnaire;
    // Pull Data
    this.pull();
  }

  pull() {
    const data = "2561"; // { year: "2561" };
    this.ajax.post(URL.DATA, data, res => {
      this.questionnaire = res.json();
      this.loading = false;
    });
  }

  send() {
    this.msg.comfirm(boo => {
      if (boo) {
        this.savings = true;
        this.ajax.post(URL.SAVED, { result: this.questionnaire }, res => {
          let response = res.json();
          if (response.messageType === "C") {
            this.msg.successModal(response.messageTh, "สำเร็จ");
            // Pull Data
            this.pull();
          } else {
            this.msg.errorModal(response.messageTh, "ล้มเหลว");
          }
          this.savings = false;
        });
      }
    }, "ต้องการส่งผลแบบสอบทานจริงหรือไม่?");
  }

  onSubmit(form) {
    form.preventDefault();
    this.msg.comfirm(boo => {
      if (boo) {
        this.saving = true;
        let promise = new Promise((resolve, reject) => {
          $.each(form.target, (index, val) => {
            const { id, value, checked } = val; // extract construct
            if (id.split("_")[0] == "point" && checked) {
              this.questionnaire.forEach(obj => {
                obj.detail.forEach(ob => {
                  if (id.split("_")[1] == "detail") {
                    if (ob.detailId == parseInt(id.split("_")[2])) {
                      ob.point = value;
                    }
                  }
                  if (id.split("_")[1] == "header") {
                    if (ob.headerId == parseInt(id.split("_")[2])) {
                      ob.point = value;
                    }
                  }
                })
              });
            }
            if (id.split("_")[0] == "conclusion") {
              this.questionnaire.forEach(obj => {
                if (obj.detail.length > 0 && obj.detail[0].headerId == id.split("_")[1]) {
                  if (value.trim() != "") {
                    obj.conclusion = value.trim();
                  }
                }
              });
            }
            if (form.target.length - 1 == index) {
              resolve(true);
            }
          });
        }).then(boo => {
          if (boo) {
            this.ajax.post(URL.SAVE, { result: this.questionnaire }, res => {
              let response = res.json();
              if (response.messageType === "C") {
                this.msg.successModal(response.messageTh, "สำเร็จ");
                // Pull Data
                this.pull();
              } else {
                this.msg.errorModal(response.messageTh, "ล้มเหลว");
              }
              this.saving = false;
            });
          }
        });
      }
    }, "ต้องการบันทึกข้อมูลจริงหรือไม่?");
  }

}