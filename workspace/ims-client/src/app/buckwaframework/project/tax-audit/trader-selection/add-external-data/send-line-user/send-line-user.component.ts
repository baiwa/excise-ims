import { Component, OnInit } from "@angular/core";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { ExciseService } from "../../../../../common/services/excise.service";
import { TextDateTH, digit } from "../../../../../common/helper/datepicker";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { CurrencyPipe } from "@angular/common";

declare var jQuery: any;
declare var $: any;
@Component({
  selector: "app-send-line-user",
  templateUrl: "./send-line-user.component.html",
  styleUrls: ["./send-line-user.component.css"]
})
export class SendLineUserComponent implements OnInit {
  sendLineUser: any;
  listItem: any[];
  before: any;
  last: any;
  month: any;
  from: any;
  analysNumber: any;
  exciseProductType: any;
  flag: any;
  coordinates: any;
  coordinatesArr: any;
  workSheetNumber: any;
  exciseId: any[];
  sector: any;
  sectorArr: any;

  constructor(
    private route: ActivatedRoute,
    private messageBarService: MessageBarService,
    private ex: ExciseService,
    private router: Router,
    private ajax: AjaxService
  ) {
    this.exciseId = [];
  }

  ngOnInit() {

    console.log("ngOnInit");
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    //get coordinates in select option
    const URL = "combobox/controller/getCoordinates";
    this.ajax.post(URL, {}, res => {
      this.coordinatesArr = res.json();
    });

    //get Sector in select option
    const URL2 = "combobox/controller/getSector";
    this.ajax.post(URL2, {}, res => {
      this.sectorArr = res.json();
    });

    //call ExciseService
    // var { currYear } = this.ex.getformValues();
    // var { analysNumber } = this.ex.getformNumber();
    var {
      before,
      last,
      analysNumber,
      workSheetNumber,
      from,
      month,
      currYear
    } = this.ex.getToSendlineUser();

    //set values
    this.before = before;
    this.last = last;
    this.from = from;
    this.month = month;
    this.analysNumber = analysNumber;
    this.workSheetNumber = workSheetNumber;

    //split function
    var from_split = this.from.split("/");

    //default month & year
    var month = from_split[0];
    var year_before = from_split[1];

    var m = parseInt(month) + 1;
    var mm = parseInt(this.month);
    var yy = parseInt(year_before);

    var items: string[] = [];
    for (var i = 1; i <= mm; i++) {
      m = m - 1;
      if (m == 0) {
        m = 12;
        yy = yy - 1;
      }
      items.push(
        '<th style="text-align: center !important">' +
        TextDateTH.monthsShort[m - 1] +
        " " +
        (yy + "").substr(2) +
        "</th>"
      );
    }

    var trHeaderColumn = "";
    for (var i = items.length - 1; i >= 0; i--) {
      trHeaderColumn += items[i];
    }
    document.getElementById("trDrinamic").innerHTML =
      '<tr><th rowspan="2" style="text-align: center !important"><input type="checkbox" name="select-all" id="select-all"></th>' +
      '<th rowspan="2" style="text-align: center !important">ลำดับ</th>' +
      '<th rowspan="2" style="text-align: center !important">ทะเบียนสรรพสามิต เดิม/ใหม่</th> ' +
      '<th rowspan="2" style="text-align: center !important">ชื่อผู้ประกอบการ</th> ' +
      '<th rowspan="2" style="text-align: center !important">ชื่อโรงอุตสาหกรรม/สถานบริการ</th> ' +
      '<th rowspan="2" style="text-align: center !important">ภาค</th> ' +
      '<th rowspan="2" style="text-align: center !important">พื้นที่</th> ' +
      '<th colspan="2" style="text-align: center !important">การชำระภาษีในสภาวะปกติ (บาท)</th> ' +
      '<th rowspan="2" style="text-align: center !important">เปลี่ยนแปลง (ร้อยละ)</th> ' +
      '<th rowspan="2" style="text-align: center !important">ชำระภาษี(เดือน)</th> ' +
      '<th colspan="3" style="text-align: center !important">การตรวจสอบภาษีย้อนหลัง 3 ปีงบประมาณ</th> ' +
      
      '<th rowspan="2" style="text-align: center !important">พิกัด</th> ' +
      '<th rowspan="2" style="text-align: center !important">ที่อยู่โรงอุตสาหกรรม/สถานบริการ</th> ' +
      '<th rowspan="2" style="text-align: center !important">ทุนจดทะเบียน</th> ' +
      '<th rowspan="2" style="text-align: center !important">สถานะ/วันที่</th> ' +
      "</tr>" +
      '<tr><th style="border-left: 1px solid rgba(34,36,38,.1);">' +
      this.month / 2 +
      " เดือนแรก</th>" +
      '<th style="text-align: center !important">' +
      this.month / 2 +
      " เดือนหลัง </th>" +
      '<th style="text-align: center !important">' +
      (currYear - 3) +
      "</th>" +
      '<th style="text-align: center !important">' +
      (currYear - 2) +
      "</th>" +
      '<th style="text-align: center !important">' +
      (currYear - 1) +
      "</th>" +
      "</tr>";

    this.initDatatable();
  }

  ngAfterViewInit() { }

  initDatatable(): void {
    const URL = AjaxService.CONTEXT_PATH + "/filter/exise/list";
    var sendLineUserCheckbox = (this.sendLineUser = $(
      "#sendLineUser"
    ).DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      processing: true,
      serverSide: true,
      paging: false,
      pagingType: "full_numbers",
      fixedColumns : { 
        leftColumns : 2 
      },
      ajax: {
        type: "POST",
        url: URL,
        data: {
          paging: false,
          flag: "NOT N S",
          //viewStatus : 
          productType: this.coordinates == undefined ? "" : this.coordinates,
          analysNumber: this.analysNumber,
          sector: this.sector
        }
      },
      columns: [
        {
          render: function (data, type, full, meta) {
            return `<input type="checkbox" name="chk${meta.row}" id="chk${
              meta.row
              }" value="${$("<div/>")
                .text(data)
                .html()}">`;
          },
          className: "center"
        },
        {
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "center"
        },
        { data: "exciseId", className: "center" },
        { data: "companyName" },
        { data: "companyName" },
        { data: "exciseOwnerArea1" },
        { data: "exciseOwnerArea" },
        { data: "firstMonth", className: "center" },
        { data: "lastMonth", className: "center" },
        { data: "percentage", className: "center" },
        { data: "totalMonth", className: "center" },
        { data: "no1" },
        { data: "no2" },
        { data: "no3" },
        
        { data: "productType" },
        { data: "factoryAddress" },
        { data: "registeredCapital" },
        { data: "status" }
      ]
    }));

    let tableId = "#userManagementDt";

    let backgroundRowColor = (data, type, row, meta) => {
      if (!data) {
        let table = $(tableId).DataTable();
        let cell = table.cell(meta.row, meta.col).node();
        $(cell).addClass("bg-row-highlight");
      }

      return data;
    };

    // Handle click on "Select all" control
    $("#select-all").on("click", function () {
      // Check/uncheck all checkboxes in the table
      var rows = sendLineUserCheckbox.rows({ search: "applied" }).nodes();
      $('input[type="checkbox"]', rows).prop("checked", this.checked);
    });
  }

  linkToDetail() {
    this.router.navigate(["/add-external-data/add-data"], {
      queryParams: {
        id: (<HTMLInputElement>document.getElementById("exciseId")).value,
        num: this.analysNumber
      }
    });
  }

  onSend(viewStatus) {

    $(".ui.modal.confirm").modal("hide");
    var data = this.sendLineUser.rows().data();
    for (let i = 0; i < data.length; i++) {
      if ((<HTMLInputElement>document.getElementById(`chk${i}`)).checked) {
        this.exciseId.push(data[i].exciseId);
      }
    } //end for loops

    if (this.exciseId.length != 0) {
      const URL = "filter/exise/listFullDataNoPaging";
      this.ajax.post(
        URL,
        {
          analysNumber: this.analysNumber,
          exiceList: this.exciseId,
          flag: "S",
          viewStatus: viewStatus
        },
        res => {
          var data = res.json();
          if (data.messageId == 3) {
            this.messageBarService.successModal(data.messageTh, "สำเร็จ");
            this.sendLineUser.destroy().draw();
            this.initDatatable();

          } else {
            this.messageBarService.errorModal(data.messageTh, "เกิดข้อผิดพลาด");
          }
        }
      );
    } else {
      this.messageBarService.errorModal(
        "กรุณาเลือกข้อมูลอย่างน้อย 1 ชุด",
        "เกิดข้อผิดพลาด"
      );
    }
  };

  changeCoordinates = () => {
    this.coordinates = (<HTMLInputElement>(
      document.getElementById("coordinates")
    )).value;
    this.sendLineUser.destroy();
    this.initDatatable();
  };

  changeSector = () => {
    this.sector = (<HTMLInputElement>document.getElementById("sector")).value;
    this.sendLineUser.destroy();
    this.initDatatable();
  };

  openModel() {
    //$(".ui.modal.condition").modal("hide");
    $(".ui.modal.confirm").modal("show");
  }

}
