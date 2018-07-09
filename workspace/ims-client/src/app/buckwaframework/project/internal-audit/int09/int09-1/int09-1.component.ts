import { Component, OnInit } from "@angular/core";
import {
  TextDateTH,
  ThaiFormatter
} from "app/buckwaframework/common/helper/datepicker";
import { AjaxService } from "app/buckwaframework/common/services";
import { TravelCostHeader } from "app/buckwaframework/common/models";
import { Router } from "@angular/router";
declare var $: any;
@Component({
  selector: "app-int09-1",
  templateUrl: "./int09-1.component.html",
  styleUrls: ["./int09-1.component.css"]
})
export class Int091Component implements OnInit {
  public $form: any;
  public showData: boolean = false;
  typeDocs: String[];
  topics: String[][];
  topic: String[];
  data: TravelCostHeader[];

  selectDoc: String;
  selectTop: String;

  selectedDoc: String;
  selectedTop: String;

  sent: boolean;

  constructor(private ajax: AjaxService, private router: Router) {
    this.typeDocs = ["ทั่วไป", "วิชาการ", "อำนวยการ", "บริหาร"];
    this.topics = [
      ["ปฏิบัติงาน", "ชำนาญงาน", "อาวุโส", "ทักษะพิเศษ"],
      ["ปฏิบัติการ", "ชำนาญการ", "ชำนาญการพิเศษ", "เชี่ยวชาญ", "ทรงคุณวุฒิ"],
      ["ระดับต้น", "ระดับสูง"],
      ["ระดับต้น", "ระดับสูง"]
    ];
    this.topic = [];
    this.sent = false; // false
    this.selectedTop = ""; // ''
  }

  ngOnInit() {
    const URL_LIST = "ia/int09/lists";
    this.ajax.get(URL_LIST, res => {
      this.data = res.json();
      this.data.forEach((item, index) => {
        this.data[index].createdDatetime = ThaiFormatter(
          new Date(item.createdDate)
        );
        this.data[index].updateDatetime = ThaiFormatter(
          new Date(item.updatedDate)
        );
      });
    });
    $("#example2").calendar({
      type: "date",
      text: TextDateTH
    });
    $("#example3").calendar({
      type: "date",
      text: TextDateTH
    });
    this.$form = $("#int09-1");
  }
  onSelectDoc = event => {
    this.topic = this.topics[event.target.value];
    this.selectDoc = this.typeDocs[event.target.value];
  };

  onSelectTop = event => {
    this.selectTop = this.topic[event.target.value];
  };

  onSubmitt = () => {
    // show form generate pdf
    this.sent = true;
    this.selectedTop = this.selectTop;
  };

  onDiscard = event => {
    // hide form generate pdf
    this.sent = event;
  };
  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }

  createLoan(id: any): void {
    this.router.navigate(["int09/2"], {
      queryParams: {
        id: id
      }
    });
  }

  editHeader(id: any): void {
    this.router.navigate(["int09/1/1"], {
      queryParams: {
        id: id
      }
    });
  }
}
