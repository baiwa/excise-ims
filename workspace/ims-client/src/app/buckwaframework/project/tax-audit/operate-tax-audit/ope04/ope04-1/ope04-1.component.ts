import { Component, OnInit } from "@angular/core";
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
export class Ope041Component implements OnInit {
  public showData: boolean = false;
  exciseId: any;
  exciseIdArr: any;
  firstDataList: any;
  startDate: any;
  endDate: any;
  MonthDataList: MonthData;
  fileExel: File[];
  analysNumber: any;
  row: any;
  max: any;
  diff: any;

  constructor(private ajax: AjaxService) {
    this.exciseIdArr = "";
    this.firstDataList = {
      companyName: "",
      analysNumber: "",
      productType: ""
    };
    this.MonthDataList = {
      product1: "",
      product2: "",
      product3: "",
      product4: "",
      product5: "",
      product6: "",
      monthRecieve1: "",
      monthRecieve2: "",
      monthRecieve3: "",
      monthRecieve4: "",
      monthRecieve5: "",
      monthRecieve6: ""
    };

    this.row = [];
    for (let i = 0; i < 7; i++) {
      this.row.push({
        column1: "",
        column2: "",
        column3: "",
        column4: "",
        column5: "",
        column6: ""
      });
    }

    this.max = [0, 0, 0, 0, 0, 0, 0];
    this.diff = [0, 0, 0, 0, 0, 0, 0];
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
  }

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

  clearData() {
    this.row = [];
    for (let i = 0; i < 7; i++) {
      this.row.push({
        column1: "",
        column2: "",
        column3: "",
        column4: "",
        column5: "",
        column6: ""
      });
    }
    this.max = [0, 0, 0, 0, 0, 0, 0];
    this.diff = [0, 0, 0, 0, 0, 0, 0];

    this.showData = false;
  }

  onUpload = (event: any) => {
    // Prevent actual form submission
    event.preventDefault();

    //send form data
    const form = $("#upload-form")[0];
    let formBody = new FormData(form);

    let url = `upload/excel`;
    this.ajax.upload(url, formBody, res => {
      this.row = res.json();

      for (let i = 1; i <= 6; i++) {
        this.max[i] = 0;
        for (let j = 4; j <= 6; j++) {
          //find max value
          if (j != 5) {
            this.max[i] =
              parseFloat(this.row[i]["column" + j]) > this.max[i]
                ? parseFloat(this.row[i]["column" + j])
                : this.max[i];
          }
        }
        this.max[i] =
          this.MonthDataList["monthRecieve" + i] > this.max[i]
            ? this.MonthDataList["monthRecieve" + i]
            : this.max[i];
        this.diff[i] = parseFloat(this.row[i]["column3"]) - this.max[i];

        //set format "#,###"
        this.row[i]["column3"] = this.DF(this.row[i]["column3"]);
        this.row[i]["column4"] = this.DF(this.row[i]["column4"]);
        this.row[i]["column6"] = this.DF(this.row[i]["column6"]);
        this.max[i] = this.DF(this.max[i].toString());
        this.diff[i] = this.DF(this.diff[i].toString());
      }
    });
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
    // Prevent actual form submission
    e.preventDefault();
    this.startDate = e.target["startDate"].value;
    this.endDate = e.target["endDate"].value;

    //change formatter first date input value
    const date_str1 = this.startDate.split(" ");
    date_str1[0] = digit(TextDateTH.months.indexOf(date_str1[0]) + 1);
    const startDateSplit = date_str1[0] + "/" + date_str1[1];

    //change formatter end date input value
    const date_str2 = this.endDate.split(" ");
    date_str2[0] = digit(TextDateTH.months.indexOf(date_str2[0]) + 1);
    const endDateSplit = date_str2[0] + "/" + date_str2[1];

    const URL =
      AjaxService.CONTEXT_PATH + "/filter/exise/getDataExciseIdMonthList";
    $.post(
      URL,
      {
        exciseId: this.exciseId,
        type: "W",
        startDate: startDateSplit,
        endDate: endDateSplit
      },
      res => {
        if (res.length == 0) {
          this.MonthDataList = null;
        } else {
          this.MonthDataList = res[0];
          for (let i = 1; i <= 6; i++) {
            this.MonthDataList["monthRecieve" + i] = this.DF(
              this.MonthDataList["monthRecieve" + i]
            ).toString();
          }
        }
      }
    );

    //update data
    this.showData = true;
  };

  DF(what) {
    const df = new DecimalFormat("#,###");
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

class MonthData {
  product1: any;
  product2: any;
  product3: any;
  product4: any;
  product5: any;
  product6: any;
  monthRecieve1: any;
  monthRecieve2: any;
  monthRecieve3: any;
  monthRecieve4: any;
  monthRecieve5: any;
  monthRecieve6: any;
}

class File {
  [x: string]: any;
  name: string;
  type: string;
  value: any;
}

class Column {
  column1: any;
  column2: any;
  column3: any;
  column4: any;
  column5: any;
  column6: any;
}
