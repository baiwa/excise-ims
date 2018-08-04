import { Component, OnInit, Output, EventEmitter } from "@angular/core";

declare var $: any;

@Component({
  selector: "app-int02-m4-2",
  templateUrl: "./int02-m4-2.component.html",
  styleUrls: ["./int02-m4-2.component.css"]
})
export class Int02M42Component implements OnInit {

  @Output() showList = new EventEmitter<any>();

  topics: any[][];
  province: any[];

  constructor() {
    this.topics = [
      ["ด้านการเงิน", "2", "6", "1"],
      ["ด้านเจ้าหนี้", "2", "4", "3"],
      ["ด้านระบบ e-payment", "4", "1", "7"],
      ["ด้านระบบ GFMIS", "1", "3", "10"]
    ];

    this.province = ["สสพ.แม่ฮ่องสอน", "สสพ.นนทบุรี", "สสพ.ลำปาง"];
  }

  ngOnInit() {
    $(".ui.accordion").accordion({
      selector: {
        trigger: ".title"
      }
    });
  }

  show() {
    this.showList.emit("step2");
  }

  back() {
    this.showList.emit("");
  }
}
