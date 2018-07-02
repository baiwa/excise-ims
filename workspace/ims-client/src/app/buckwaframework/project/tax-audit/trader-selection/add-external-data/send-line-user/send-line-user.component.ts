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
  private listItem: any[];
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
    const URL = "working/test/getCoordinates";
    this.ajax.post(URL, {}, res => {
      this.coordinatesArr = res.json();
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
    console.log("analysNumber: ", this.analysNumber);
    console.log("from: ", this.from);
    console.log("month: ", this.month);

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
      '<th rowspan="2" style="text-align: center !important">พื้นที่</th> ' +
      '<th colspan="2" style="text-align: center !important">การชำระภาษีในสภาวะปกติ (บาท)</th> ' +
      '<th rowspan="2" style="text-align: center !important">เปลี่ยนแปลง (ร้อยละ)</th> ' +
      '<th rowspan="2" style="text-align: center !important">ชำระภาษี(เดือน)</th> ' +
      '<th colspan="3" style="text-align: center !important">การตรวจสอบภาษีย้อนหลัง 3 ปีงบประมาณ</th> ' +
      '<th rowspan="2" style="text-align: center !important">ภาค</th> ' +
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

  ngAfterViewInit() {}

  // initDatatable(): void {

  //     var d = new Date();
  //     const URL = AjaxService.CONTEXT_PATH + "/filter/exise/list";
  //     console.log(URL);
  //     console.log(this.analysNumber);

  //     this.sendLineUser = $('#sendLineUser').DataTable({
  //         "lengthChange":false,
  //         "searching":false,
  //         "select":true,
  //         "ordering":true,
  //         "processing":true,
  //         "serverSide":true,
  //         "paging":false,
  //         "ajax":{
  //            "type":"POST",
  //            "url":"/ims-webapp//filter/exise/list",
  //            "data":{
  //               "paging":false,
  //               "flag":"N",
  //               "productType":"",
  //               "analysNumber":"25610627-01-00581"
  //            }
  //         },
  //         "columns":[
  //            {
  //               "data":"worksheetHeaderId",
  //               "className":"center"
  //            },
  //            {
  //               "data":"exciseId",
  //               "className":"center"
  //            },
  //            {
  //               "data":"companyName"
  //            },
  //            {
  //               "data":"companyName"
  //            },
  //            {
  //               "data":"exciseOwnerArea"
  //            },
  //            {
  //               "data":"firstMonth",
  //               "className":"center"
  //            },
  //            {
  //               "data":"lastMonth",
  //               "className":"center"
  //            },
  //            {
  //               "data":"percentage",
  //               "className":"center"
  //            },
  //            {
  //               "data":"totalMonth",
  //               "className":"center"
  //            },
  //            {
  //               "data":"no1"
  //            },
  //            {
  //               "data":"no2"
  //            },
  //            {
  //               "data":"no3"
  //            },
  //            {
  //               "data":"exciseOwnerArea1"
  //            },
  //            {
  //               "data":"productType"
  //            },
  //            {
  //               "data":"factoryAddress"
  //            },
  //            {
  //               "data":"registeredCapital"
  //            },
  //            {
  //               "data":"status"
  //            }
  //         ]
  //      });

  //     // $(document).ready(function() {
  //     //     $('#sendLineUser').DataTable( {
  //     //         columnDefs: [ {
  //     //             orderable: false,
  //     //             className: 'select-checkbox',
  //     //             targets:   0
  //     //         } ],
  //     //         select: {
  //     //             style:    'os',
  //     //             selector: 'td:first-child'
  //     //         },
  //     //         order: [[ 1, 'asc' ]]
  //     //     } );
  //     // } );
  //     // on init table
  //     $('#sendLineUser tbody tr').css({ "background-color": "white", "cursor": "pointer" });

  //     // on click row
  //     $('#sendLineUser tbody').on('click', 'tr', function () {
  //         $("#exciseBtn").prop('disabled', false);
  //         $('#sendLineUser tbody tr').css({ "background-color": "white", "cursor": "pointer" });
  //         (<HTMLInputElement>document.getElementById("exciseId")).value = $(this).children().toArray()[1].innerHTML;
  //         $(this).css("background-color", "rgb(197,217,241)");
  //     });
  // }
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

      ajax: {
        type: "POST",
        url: URL,
        data: {
          paging: false,
          flag: "N",
          productType: "",
          analysNumber: this.analysNumber
        }
      },
      columns: [
        // {
        //     data:   "active",
        //     render: function ( data, type, row ) {
        //         if ( type === 'display' ) {
        //             return '<input type="checkbox">';
        //         }
        //         return data;
        //     },
        //     className: "center"
        // },
        {
          render: function(data, type, full, meta) {
            console.log(
              "data: ",
              data,
              "type",
              type,
              "full",
              full,
              "meta",
              meta.row
            );
            return `<input type="checkbox" name="chk${meta.row}" id="chk${
              meta.row
            }" value="${$("<div/>")
              .text(data)
              .html()}">`;
          },
          className: "center"
        },
        { data: "worksheetHeaderId", className: "center" },
        { data: "exciseId", className: "center" },
        { data: "companyName" },
        { data: "companyName" },
        { data: "exciseOwnerArea" },
        { data: "firstMonth", className: "center" },
        { data: "lastMonth", className: "center" },
        { data: "percentage", className: "center" },
        { data: "totalMonth", className: "center" },
        { data: "no1" },
        { data: "no2" },
        { data: "no3" },
        { data: "exciseOwnerArea1" },
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
    $("#select-all").on("click", function() {
      // Check/uncheck all checkboxes in the table
      var rows = sendLineUserCheckbox.rows({ search: "applied" }).nodes();
      $('input[type="checkbox"]', rows).prop("checked", this.checked);
    });
  }

  linkToDetail() {
    this.router.navigate(["/add-data"], {
      queryParams: {
        id: (<HTMLInputElement>document.getElementById("exciseId")).value,
        num: this.analysNumber
      }
    });
  }

  onSend = e => {
    // Prevent actual form submission
    e.preventDefault();

    var data = this.sendLineUser.rows().data();
    for (let i = 0; i < data.length; i++) {
      if ((<HTMLInputElement>document.getElementById(`chk${i}`)).checked) {
        this.exciseId.push(data[i].exciseId);
        // console.log("exciseId" + (i + 1) + ": ", this.exciseId);
        // console.log("analysNumber: ", this.analysNumber);
      }
      //   else {
      //     var unCheck = "''";
      //   }
    } //end for loops
    console.log(this.exciseId);

    if (this.exciseId.length != 0) {
      //   const URL =
      //     AjaxService.CONTEXT_PATH + "filter/exise/listFullDataNoPaging";
      //   $.post(
      //     URL,
      //     {
      //       analysNumber: this.analysNumber,
      //       exiceList: this.exciseId,
      //       flag: "S"
      //     },
      //     function(data) {
      //       var succ = data;
      //       console.log(succ);
      //       this.messageBarService.successModal(
      //         "สร้างกระดาษทำการเรียบร้อยแล้ว",
      //         "สำเร็จ"
      //       );
      //     }
      //   ).fail(function() {
      //     console.log("error");
      //   });

      const URL = "filter/exise/listFullDataNoPaging";
      this.ajax.post(
        URL,
        {
          analysNumber: this.analysNumber,
          exiceList: this.exciseId,
          flag: "S"
        },
        res => {
          var succ = res;
          console.log(succ);
          this.messageBarService.successModal(
            "สร้างกระดาษทำการเรียบร้อยแล้ว",
            "สำเร็จ"
          );
        }
      );
    } else {
      this.messageBarService.errorModal(
        "ไม่สามารถทำรายการได้",
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
}
