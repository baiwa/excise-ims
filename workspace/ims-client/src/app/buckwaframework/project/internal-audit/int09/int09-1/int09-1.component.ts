import { Component, OnInit } from "@angular/core";
import { TextDateTH } from "../../../../common/helper/datepicker";
import { AjaxService } from "../../../../common/services";
import { TravelCostHeader } from "../../../../common/models";
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
        this.data[index].createdDatetime = new Date(item.createdDatetime);
        this.data[index].updateDatetime = new Date(item.updateDatetime);
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
}
