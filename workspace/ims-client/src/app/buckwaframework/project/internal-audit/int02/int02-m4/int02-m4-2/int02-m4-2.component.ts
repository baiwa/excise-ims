import { Component, OnInit, Output, EventEmitter } from "@angular/core";
import { Result, _Result } from "./int02-m4-2.mock";
import { forEach } from "@angular/router/src/utils/collection";

declare var $: any;

@Component({
  selector: "app-int02-m4-2",
  templateUrl: "./int02-m4-2.component.html",
  styleUrls: ["./int02-m4-2.component.css"]
})
export class Int02M42Component implements OnInit {

  @Output() showList = new EventEmitter<any>();

  total: number = 0;
  province: Result[] = _Result;

  constructor() {
  }

  ngOnInit() {
    $(".ui.accordion").accordion();
  }

  calcTotal() {
    this.total = 0;
    this.province.forEach(obj => {
      this.total += obj.detail.length;
    });
    return this.total;
  }

  show() {
    this.showList.emit("step2");
  }

  back() {
    this.showList.emit("");
  }
}
