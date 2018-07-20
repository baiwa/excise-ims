import { Component, OnInit } from "@angular/core";
import { AjaxService } from "../../../../../common/services/ajax.service";
import {
  TextDateTH,
  formatter,
  digit
} from "../../../../../common/helper/datepicker";

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
  fileEx: any;

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

  // uploadData() {
  //   this.startDate = (<HTMLInputElement>(
  //     document.getElementById("startDate")
  //   )).value;
  //   this.endDate = (<HTMLInputElement>document.getElementById("endDate")).value;
  //   this.showData = true;
  //   console.log(this.exciseId);
  //   console.log(this.startDate);
  //   console.log(this.endDate);

  //   const date_str1 = this.startDate.split(" ");
  //   date_str1[0] = digit(TextDateTH.months.indexOf(date_str1[0]) + 1);
  //   const startDateSplit = date_str1[0] + "/" + date_str1[1];
  //   console.log(startDateSplit);

  //   const date_str2 = this.endDate.split(" ");
  //   date_str2[0] = digit(TextDateTH.months.indexOf(date_str2[0]) + 1);
  //   const endDateSplit = date_str2[0] + "/" + date_str2[1];
  //   console.log(endDateSplit);
  // }

  // clearData() {
  //   this.showData = false;
  // }

  onUpload = e => {
    // Prevent actual form submission
    e.preventDefault();
    // this.startDate = e.target["startDate"].value;
  };

  onChangeUpload = (event: any) => {
    let reader = new FileReader();
    if (event.target.files && event.target.files.length > 0) {
      let file = event.target.files[0];
      reader.readAsDataURL(file);
      console.log(file);

      reader.onload = (event: any) => {
        console.log(event.target.result);
      };
    }
  };

  onClickShow = e => {
    // Prevent actual form submission
    e.preventDefault();
    this.startDate = e.target["startDate"].value;
    this.endDate = e.target["endDate"].value;
    console.log(this.exciseId);
    console.log(this.startDate);
    console.log(this.endDate);

    //change formatter first date input value
    const date_str1 = this.startDate.split(" ");
    date_str1[0] = digit(TextDateTH.months.indexOf(date_str1[0]) + 1);
    const startDateSplit = date_str1[0] + "/" + date_str1[1];
    console.log(startDateSplit);

    //change formatter end date input value
    const date_str2 = this.endDate.split(" ");
    date_str2[0] = digit(TextDateTH.months.indexOf(date_str2[0]) + 1);
    const endDateSplit = date_str2[0] + "/" + date_str2[1];
    console.log(endDateSplit);

    const URL =
      AjaxService.CONTEXT_PATH + "/filter/exise/getDataExciseIdMonthList";
    $.post(
      URL,
      {
        exciseId: this.exciseId,
        startDate: startDateSplit,
        endDate: endDateSplit
      },
      res => {
        this.MonthDataList = res.length == 0 ? new MonthData() : res[0];
        console.log(this.MonthDataList);
      }
    );

    //update data
    this.showData = true;
  };
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
