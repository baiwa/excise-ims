import { Component, OnInit, AfterViewInit } from "@angular/core";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { TextDateTH, digit } from "../../../../../common/helper/datepicker";
import { DecimalFormat } from "../../../../../common/helper";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { AuthService } from "services/auth.service";
import { BreadCrumb } from "models/breadcrumb";

declare var $: any;
@Component({
  selector: "ope04-2",
  templateUrl: "./ope04-2.component.html",
  styleUrls: ["./ope04-2.component.css"]
})
export class Ope042Component implements OnInit, AfterViewInit {

  breadcrumb: BreadCrumb[] = [
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'การตรวจสอบภาษี', route: '#' },
    { label: 'สร้างกระดาษทำการตรวจสอบภาษี', route: '#' },
    { label: 'สร้างกระดาษทำการจ่ายวัตถุดิบ', route: '#' },
  ];

  obj: Data;
  exciseId: any;
  exciseIdArr: any;
  firstDataList: any;
  startDate: any;
  endDate: any;
  MonthDataList: any;
  fileExel: File[];
  row: any;
  diff: any;
  monthRecieveArr: any;
  showDt: any;
  startDateSplit: any;
  endDateSplit: any;
  dataTB: any;
  dataHeader: any;
  emptyColumn: any;
  allData: any;

  constructor(
    private authService: AuthService,
    private ajax: AjaxService,
    private messageBarService: MessageBarService
  ) {
    this.exciseIdArr = "";
    this.firstDataList = {
      companyName: "",
      productType: ""
    };
    this.obj = new Data();

    this.startDateSplit = "";
    this.endDateSplit = "";
    this.emptyColumn = "";
    this.row = [];
    this.fileExel = new Array<File>(); // initial file array
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram("OPE-04200");
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ope04-1").css("width", "100%");

    $("#calendarFront").calendar({
      endCalendar: $("#calendarLast"),
      maxDate: new Date(),
      type: "month",
      text: TextDateTH,
      formatter: {
        header: function (date, mode, settings) {
          //return a string to show on the header for the given 'date' and 'mode'
          return date.getFullYear() + 543;
        },
        date: function (date, settings) {
          if (!date) {
            return "";
          }
          const month = date.getMonth();
          const year = date.getFullYear() + 543;
          return TextDateTH.months[month] + " " + year;
        }
      }
    });

    $("#calendarLast").calendar({
      startCalendar: $("#calendarFront"),
      maxDate: new Date(),
      type: "month",
      text: TextDateTH,
      formatter: {
        header: function (date, mode, settings) {
          //return a string to show on the header for the given 'date' and 'mode'
          return date.getFullYear() + 543;
        },
        date: function (date, settings) {
          if (!date) {
            return "";
          }
          const month = date.getMonth();
          const year = date.getFullYear() + 543;
          return TextDateTH.months[month] + " " + year;
        }
      }
    });

    // get exciseId in select option
    const URL = "combobox/controller/getExciseId";
    this.ajax.post(URL, {}, res => {
      this.exciseIdArr = res.json();
      this.exciseId = this.exciseIdArr[0];

      // get exciseId in input box
      const URL =
        AjaxService.CONTEXT_PATH + "/filter/exise/getDataExciseIdList";
      $.post(URL, { exciseId: this.exciseId }, res => {
        this.obj = res[0];
        this.firstDataList = res[0];
      });
    });
  }

  ngAfterViewInit(): void {
    $("#showData").hide();
  }

  ngOnDestroy(): void { }

  changeExiseId = () => {
    this.exciseId = (<HTMLInputElement>(
      document.getElementById("exciseId")
    )).value;
    const URL = AjaxService.CONTEXT_PATH + "/filter/exise/getDataExciseIdList";
    $.post(URL, { exciseId: this.exciseId }, res => {
      this.obj = res[0];
    });
  };

  clearAll = () => {
    $("#exciseId").dropdown('restore defaults');
    $("#fileExel").val('');
    $("#showData").hide();
    // this.showDt.fnClearTable();
    this.showDt.clear().draw();
  };

  onUpload = (event: any) => {
    // Prevent actual form submission
    event.preventDefault();

    this.dataTB = [];
    for (var i = 0; i < this.showDt.data().length; i++) {
      this.dataTB.push(this.showDt.data()[i]);
    }

    //send form data
    const form = $("#upload-form")[0];
    let formBody = new FormData(form);
    for (var i = 0; i < this.showDt.data().length; i++) {
      formBody.append("product" + (i + 1), this.dataTB[i].product);
      formBody.append("monthRecieve" + (i + 1), this.dataTB[i].monthRecieve);
    }

    let url = "/ope041/excel";
    this.ajax.upload(
      url,
      formBody,
      res => {
        this.row = res.json();
        this.emptyColumn = this.row.data[0].columnName;
        this.allData = [];
        for (i = 0; i < this.row.data.length; i++) {
          this.allData.push(this.row.data[i]);
        }

        if (this.showDt != null && this.showDt != undefined) {
          this.showDt.destroy();
        }

        //render check number is null or empty
        let renderfn = function (data, type, row, meta) {
          return $.trim(data) == ""
            ? "-"
            : $.fn.dataTable.render.number(",", ".", 0, "").display(data);
        };
        //render check string is null or empty
        let renderStr = function (data, type, row, meta) {
          return $.trim(data) == "" ||
            $.trim(data) == null ||
            $.trim(data) == "null"
            ? "-"
            : data;
        };

        this.showDt = $("#showDt").DataTable({
          lengthChange: false,
          searching: false,
          ordering: false,
          //pageLength: 10,
          processing: true,
          serverSide: false,
          paging: true,
          data: this.allData,
          columns: [
            {
              render: function (data, type, row, meta) {
                return meta.row + meta.settings._iDisplayStart + 1;
              }
            },
            {
              data: "product",
              render: renderStr,
              className: "left"
            },
            {
              data: "taxInvoice",
              render: renderfn
            },
            {
              data: "dayRecieve",
              render: renderfn
            },
            {
              data: "monthRecieve",
              render: renderfn
            },
            {
              data: "exd1",
              render: renderfn
            },
            {
              data: "calMax",
              render: renderfn
            },
            {
              data: "diff",
              render: renderfn
            }
          ],
          columnDefs: [
            { targets: [0, 1], className: "left" },
            { targets: [2, 3, 4, 5, 6], className: "right" },
            { targets: [7], className: "right amount" }
          ],
          fnDrawCallback: function (oSettings) {
            if ($(".amount").length > 0) {
              $(".amount").each(function () {
                if (this.innerHTML === "0") {
                  this.className = "right amount green";
                }
                if (
                  +this.innerHTML.split(",").join("") > 0 ||
                  +this.innerHTML.split(",").join("") < 0
                ) {
                  this.className = "right amount red";
                }
                if (this.innerHTML == null || this.innerHTML === "") {
                  this.className = "center amount null";
                  this.innerHTML = "-";
                }
                if ($(".right").length > 0) {
                  $(".right").each(function () {
                    // if (this.innerHTML === "-") {
                    //   this.className = "center";
                    // }
                  });
                }
              });
            }
          }
        });
      },
      err => {
        this.messageBarService.errorModal(
          "ไม่สามารถอัพโหลดข้อมูลได้",
          "เกิดข้อผิดพลาด"
        );
      }
    );
  };

  onChangeUpload = (event: any) => {
    if (event.target.files && event.target.files.length > 0) {
      let reader = new FileReader();

      reader.onload = (e: any) => {
        const f = {
          name: event.target.files[0].name,
          type: event.target.files[0].type,
          value: e.target.result
        };
        this.fileExel = [f];
      };
      reader.readAsDataURL(event.target.files[0]);
    }
  };

  onClickShow = e => {
    $("#showData").show();
    // Prevent actual form submission
    e.preventDefault();
    this.startDate = e.target["startDate"].value;
    this.endDate = e.target["endDate"].value;

    //set for [(NgModel)]
    this.obj.startDate = this.startDate;
    this.obj.endDate = this.endDate;

    //change formatter first date input value
    const date_str1 = this.startDate.split(" ");
    date_str1[0] = digit(TextDateTH.months.indexOf(date_str1[0]) + 1);
    this.startDateSplit = date_str1[0] + "/" + date_str1[1];

    //change formatter end date input value
    const date_str2 = this.endDate.split(" ");
    date_str2[0] = digit(TextDateTH.months.indexOf(date_str2[0]) + 1);
    this.endDateSplit = date_str2[0] + "/" + date_str2[1];

    if (this.showDt != null && this.showDt != undefined) {
      this.showDt.destroy();
    }
    this.initDatatable();
  };

  initDatatable(): void {
    //render check number is null or empty
    let renderfn = function (data, type, row, meta) {
      return $.trim(data) == ""
        ? "-"
        : $.fn.dataTable.render.number(",", ".", 0, "").display(data);
    };
    //render check string is null or empty
    let renderStr = function (data, type, row, meta) {
      return $.trim(data) == "" || $.trim(data) == null ? "-" : data;
    };
    const URL = AjaxService.CONTEXT_PATH + "/ope041/excel";
    this.showDt = $("#showDt").DataTableTh({
      lengthChange: false,
      searching: false,
      ordering: false,
      processing: true,
      serverSide: false,
      paging: false,

      ajax: {
        type: "POST",
        url: URL,
        data: {
          exciseId: this.exciseId,
          type: "T",
          startDate: this.startDateSplit,
          endDate: this.endDateSplit
        }
      },
      columns: [
        {
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          }
        },
        {
          data: "product",
          render: renderStr,
          className: "left"
        },
        {
          data: "taxInvoice",
          render: renderfn
        },
        {
          data: "dayRecieve",
          render: renderfn
        },
        {
          data: "monthRecieve",
          render: renderfn
        },
        {
          data: "exd1",
          render: renderfn
        },
        {
          data: "calMax",
          render: renderfn
        },
        {
          data: "diff",
          render: renderfn
        }
      ],
      columnDefs: [
        { targets: [0, 1], className: "left" },
        { targets: [2, 3, 4, 5, 6], className: "right" },
        { targets: [7], className: "right amount" }
      ],
      fnDrawCallback: function (oSettings) {
        if ($(".amount").length > 0) {
          $(".amount").each(function () {
            if (this.innerHTML === "0") {
              this.className = "right amount green";
            }
            if (
              +this.innerHTML.split(",").join("") > 0 ||
              +this.innerHTML.split(",").join("") < 0
            ) {
              this.className = "right amount red";
            }
            if (
              this.innerHTML == null ||
              this.innerHTML === "" ||
              this.innerHTML == "-"
            ) {
              this.className = "center amount null";
              this.innerHTML = "-";
            }
          });
        }
        if ($(".right").length > 0) {
          $(".right").each(function () {
            if (this.innerHTML === "-") {
              this.className = "center";
            }
          });
        }
      }
    });
  }

  saveTable = () => {
    this.messageBarService.comfirm((res) => {
      if (res) {
        this.dataTB = [];
        //push data Criteria header
        this.dataTB.push({
          exciseId: this.exciseId,
          analysNumber: this.obj.analysNumber,
          startDate: this.startDateSplit,
          endDate: this.endDateSplit
        });

        //push datatable #showDt
        for (var i = 0; i < this.showDt.data().length; i++) {
          this.dataTB.push(this.showDt.data()[i]);
        }

        const URL = "/ope042/saveTable";
        this.ajax.post(
          URL,
          JSON.stringify(this.dataTB),
          res => {
            this.messageBarService.successModal("บันทึกข้อมูลสำเร็จ", "สำเร็จ");
          },
          err => {
            this.messageBarService.errorModal(
              "ไม่สามารถบันทึกข้อมูลได้",
              "เกิดข้อผิดพลาด"
            );
          }
        );
      }
    }, "", "บันทึกข้อมูล");
  };

  DF(what) {
    const df = new DecimalFormat("###,###");
    return df.format(what);
  }

  toFixed(x) {
    if (Math.abs(x) < 1.0) {
      var e = parseInt(x.toString().split("e-")[1]);
      if (e) {
        x *= Math.pow(10, e - 1);
        x = "0." + new Array(e).join("0") + x.toString().substring(2);
      }
    } else {
      var e = parseInt(x.toString().split("+")[1]);
      if (e > 20) {
        e -= 20;
        x /= Math.pow(10, e);
        x += new Array(e + 1).join("0");
      }
    }
    return x;
  }
}

class File {
  [x: string]: any;
  name: string;
  type: string;
  value: any;
}

class Data {
  companyName: any = "";
  startDate: any = "";
  endDate: any = "";
  analysNumber: any = "";
  startDateSplit: any = "";
  endDateSplit: any = "";
}
