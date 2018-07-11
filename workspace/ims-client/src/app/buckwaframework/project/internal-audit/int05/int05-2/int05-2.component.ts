import { Component, OnInit } from "@angular/core";

declare var $: any;
@Component({
  selector: "int05-2",
  templateUrl: "./int05-2.component.html",
  styleUrls: ["./int05-2.component.css"]
})
export class Int052Component implements OnInit {
  zoneList: any[];
  areaList: any[];
  branchList: any[];

  showData: boolean = false;

  constructor() {}

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    this.zoneList = [
      { value: "สำนักงานสรรพสามิตภาคที่ 1" },
      { value: "สำนักงานสรรพสามิตภาคที่ 2" },
      { value: "สำนักงานสรรพสามิตภาคที่ 3" },
      { value: "สำนักงานสรรพสามิตภาคที่ 4" },
      { value: "สำนักงานสรรพสามิตภาคที่ 5" },
      { value: "สำนักงานสรรพสามิตภาคที่ 6" },
      { value: "สำนักงานสรรพสามิตภาคที่ 7" }
    ];

    this.areaList = [
      { value: "สำนักงานสรรพสามิตพื้นที่สมุทรลำปาง" },
      { value: "สำนักงานสรรพสามิตพื้นที่สมุทรสาคร" },
      { value: "สำนักงานสรรพสามิตพื้นที่สมุทรปราการ" }
    ];

    this.branchList = [
      { value: "สำนักงานสรรพสามิตพื้นที่ลำปางสาขาเมืองลำปาง" },
      { value: "สำนักงานสรรพสามิตพื้นที่สมุทรสาครสาขาเมืองสมุทรสาคร" },
      { value: "สำนักงานสรรพสามิตพื้นที่สมุทรปราการสาขาเมืองสมุทรปราการ" }
    ];
  }

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }

  editData() {
    $("#modalCheckStampBranch").modal("show");
  }

  closeModal() {
    $("#modalCheckStampBranch").modal("hide");
  }
}
