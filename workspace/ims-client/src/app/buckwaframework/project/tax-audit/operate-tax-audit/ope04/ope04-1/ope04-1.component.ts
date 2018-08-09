import { Component, OnInit, AfterViewInit } from "@angular/core";
import { AjaxService } from "../../../../../common/services/ajax.service";
import {
  TextDateTH,
  formatter,
  digit
} from "../../../../../common/helper/datepicker";
import { DecimalFormat } from "../../../../../common/helper";

declare var $: any;
@Component({
  selector: "ope04-1",
  templateUrl: "./ope04-1.component.html",
  styleUrls: ["./ope04-1.component.css"]
})
export class Ope041Component implements OnInit, AfterViewInit {
  exciseId: any;
  exciseIdArr: any;
  firstDataList: any;
  startDate: any;
  endDate: any;
  MonthDataList: any;
  fileExel: File[];
  analysNumber: any;
  row: any;
  max: any;
  diff: any;
  monthRecieveArr: any;
  rowShift: any;
  showDt: any;
  startDateSplit: any;
  endDateSplit: any;
  rowAndColumn: any;

  constructor(private ajax: AjaxService) {
    this.exciseIdArr = "";
    this.firstDataList = {
      companyName: "",
      analysNumber: "",
      productType: ""
    };

    this.fileExel = new Array<File>(); // initial file array
  }

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ope04-1").css("width", "100%");

    $("#calendarFront").calendar({
      endCalendar: $("#calendarLast"),
      maxDate: new Date(),
      type: "month",
      text: TextDateTH,
      formatter: {
        header: function(date, mode, settings) {
          //return a string to show on the header for the given 'date' and 'mode'
          return date.getFullYear() + 543;
        },
        date: function(date, settings) {
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
        header: function(date, mode, settings) {
          //return a string to show on the header for the given 'date' and 'mode'
          return date.getFullYear() + 543;
        },
        date: function(date, settings) {
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
        this.firstDataList = res[0];
      });
    });

    // get exciseId in input box
    const URL2 = AjaxService.CONTEXT_PATH + "/filter/exise/getSessionEmptyData";
    $.post(URL2, res => {});
  }

  ngAfterViewInit(): void {
    $("#showData").hide();
  }

  ngOnDestroy(): void {}

  changeExiseId = () => {
    this.exciseId = (<HTMLInputElement>(
      document.getElementById("exciseId")
    )).value;
    const URL = AjaxService.CONTEXT_PATH + "/filter/exise/getDataExciseIdList";
    $.post(URL, { exciseId: this.exciseId }, res => {
      this.firstDataList = res[0];
      (<HTMLInputElement>(
        document.getElementById("companyName")
      )).value = this.firstDataList.companyName;
      (<HTMLInputElement>(
        document.getElementById("analysNumber")
      )).value = this.firstDataList.analysNumber;
      (<HTMLInputElement>(
        document.getElementById("productType")
      )).value = this.firstDataList.productType;
    });
  };

  clearAll = () => {
    $("#showData").hide();
    $("#showDt")
      .dataTable()
      .fnClearTable();
    this.showDt.destroy();
  };

  onUpload = (event: any) => {
    // Prevent actual form submission
    event.preventDefault();

    //send form data
    const form = $("#upload-form")[0];
    let formBody = new FormData(form);

    let url = `upload/excel`;
    this.ajax.upload(
      url,
      formBody,
      res => {
        console.log(res.json());
        this.row = res.json();
        this.showDt.destroy();
        this.initDatatable();
      }
      // err => {
      //   alert("asdmiosdfhi");
      // }
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

    //change formatter first date input value
    const date_str1 = this.startDate.split(" ");
    date_str1[0] = digit(TextDateTH.months.indexOf(date_str1[0]) + 1);
    this.startDateSplit = date_str1[0] + "/" + date_str1[1];

    //change formatter end date input value
    const date_str2 = this.endDate.split(" ");
    date_str2[0] = digit(TextDateTH.months.indexOf(date_str2[0]) + 1);
    this.endDateSplit = date_str2[0] + "/" + date_str2[1];

    this.initDatatable();
  };

  initDatatable(): void {
    const URL =
      AjaxService.CONTEXT_PATH + "/filter/exise/getDataExciseIdMonthList";
    this.showDt = $("#showDt").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      processing: true,
      serverSide: false,
      paging: false,
      pagingType: "full_numbers",

      ajax: {
        type: "POST",
        url: URL,
        data: {
          exciseId: this.exciseId,
          type: "W",
          startDate: this.startDateSplit,
          endDate: this.endDateSplit
        }
      },
      columns: [
        {
          render: function(data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "center"
        },
        {
          data: "product",
          className: "center"
        },
        {
          className: "center",
          render: (data, type, full, meta) => {
            return this.DF(full.taxInvoice);
          }
        },
        {
          className: "center",
          render: (data, type, full, meta) => {
            return this.DF(full.dayRecieve);
          }
        },
        {
          className: "center",
          render: (data, type, full, meta) => {
            return this.DF(full.monthRecieve);
          }
        },
        {
          className: "center",
          render: (data, type, full, meta) => {
            return this.DF(full.exd1);
          }
        },
        {
          className: "center",
          render: (data, type, full, meta) => {
            return this.DF(full.calMax);
          }
        },
        {
          className: "center amount",
          render: (data, type, full, meta) => {
            console.log(typeof this.DF(full.diff));
            return this.DF(full.diff);
          }
        }
      ],
      fnDrawCallback: function(oSettings) {
        if ($(".amount").length > 0) {
          $(".amount").each(function() {
            if (this.innerHTML === "0") {
              this.className = "center amount green";
            }
            if (
              +this.innerHTML.split(",").join("") > 0 ||
              +this.innerHTML.split(",").join("") < 0
            ) {
              this.className = "center amount red";
            }
            if (this.innerHTML == null || this.innerHTML === "") {
              this.className = "center amount null";
              this.innerHTML = "-";
            }
          });
        }
      }
    });
  }

  getClassBydata(value) {
    if (value.length === 0) {
      return "null";
    } else if (+value === 0) {
      return "green";
    } else if (+value) {
      return "red";
    }
  }

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
