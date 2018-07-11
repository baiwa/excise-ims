import { Component, OnInit } from "@angular/core";

import { MessageBarService } from "../../../../common/services/message-bar.service";
import { Router } from "@angular/router";
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
  private count: number = 1;
  numbers: number[];
  minorDetail: String[];
  mainDetail: string;

  constructor(
    private messageBarService: MessageBarService,
    private ajax: AjaxService
  ) {}

  ngOnInit() {
    this.numbers = [1];
    this.minorDetail = [];
    //set loop <5
    for (let i = 0; i < 5; i++) {
      this.minorDetail.push(" ");
    }
  }

  onAddField = () => {
    let num = this.numbers.length;
    if (num < 5) {
      this.numbers.push(num + 1);
    } else {
      this.messageBarService.errorModal(
        "ไม่สามารถทำรายการได้เกิน 5 ข้อ",
        "เกิดข้อผิดพลาด"
      );
    }
  };
  onDelField = index => {
    this.numbers.splice(index, 1);
  };

  onSend = () => {
    this.mainDetail = (<HTMLInputElement>(
      document.getElementById("mainDetail")
    )).value;
    console.log("mainDetail :: " + this.mainDetail);
    console.log(this.minorDetail);

    const URL = "ia/int02/createQuestionnaireMainDetail";
    this.ajax.post(
      URL,
      { mainDetail: this.mainDetail, minorDetail: this.minorDetail },
      res => {
        console.log(res.json());
        var message = res.json();
        console.log(message.messageType);
        if (message.messageType == "E") {
          this.messageBarService.errorModal(message.messageTh, "แจ้งเตือน");
        } else {
          this.messageBarService.successModal(
            message.messageTh,
            "บันทึกข้อมูลสำเร็จ"
          );
        }
      }
    );
  };
}
