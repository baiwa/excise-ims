import { Component, OnInit } from "@angular/core";
import { TextDateTH } from "../../../../common/helper/datepicker";
import { AjaxService, MessageBarService } from "../../../../common/services";
import { TravelCostHeader } from "../../../../common/models";
import { Router } from "@angular/router";
import { TravelCostDetail } from "app/buckwaframework/common/models/travelcostdetail";
declare var $: any;
@Component({
  selector: "app-int09-1",
  templateUrl: "./int09-1.component.html",
  styleUrls: ["./int09-1.component.css"]
})
export class Int091Component implements OnInit {
  public $form: any;
  public showData: boolean = false;

  topics: String[][];
  topic: String[];
  data: TravelCostHeader;
  status: string;
  selectDoc: String;
  selectTop: String;
  detail: TravelCostDetail[];

  selectedDoc: String;
  selectedTop: String;
  typeDocs: string[];

  sent: boolean;

  constructor(
    private ajax: AjaxService,
    private router: Router,
    private msg: MessageBarService
  ) {
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
      var options = { year: "numeric", month: "long", day: "numeric" };
      this.data.forEach((item, index) => {
        var createdDate = new Date(item.createdDate);
        var updatedDate = new Date(item.updatedDate);
        this.data[index].createdDatetime = createdDate.toLocaleDateString(
          "th-TH",
          options
        );
        this.data[index].updateDatetime = updatedDate.toLocaleDateString(
          "th-TH",
          options
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
  detailHeader(id: any): void {
    this.router.navigate(["int09/1/1"], {
      queryParams: {
        id: id
      }
    });
  }

  deleteData(id): void {
    this.msg.comfirm(
      boo => {
        if (boo) {
          const URL = `ia/int09/lists/${id}`;
          this.ajax.delete(URL, res => {
            this.data = this.data.filter((obj: TravelCostHeader) => {
              return obj.workSheetHeaderId !== id;
            });
          });
        }
      },
      `ต้องการลบข้อมูลที่เลือกหรือไม่?`,
      "การยืนยัน"
    );
  }
}
