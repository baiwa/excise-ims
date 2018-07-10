import { Component, OnInit } from "@angular/core";
import { TextDateTH, formatter } from "../../../common/helper";

declare var $: any;
@Component({
  selector: "app-mgcontrol",
  templateUrl: "./mgcontrol.component.html",
  styleUrls: ["./mgcontrol.component.css"]
})
export class MgcontrolComponent implements OnInit {
  public topic: string;

  constructor() {
    this.topic = "";
  }

  ngOnInit() {
    // context
    $("#context .menu .item").tab({
      context: $("#context")
    });
    // checkbox
    $(".ui.checkbox").checkbox();
    // dropdown
    $(".tag.example .ui.dropdown").dropdown({
      allowAdditions: true
    });
    // calendar
    $("#year").calendar({
      maxDate: new Date(),
      type: "year",
      text: TextDateTH,
      formatter: formatter("year")
    });
  }
}
