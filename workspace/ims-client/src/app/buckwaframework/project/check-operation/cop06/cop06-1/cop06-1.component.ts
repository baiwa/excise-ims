import { Component, OnInit } from '@angular/core';
import { DecimalFormat } from 'helpers/decimalformat';
import { TextDateTH, digit } from 'helpers/datepicker';
import { AjaxService } from 'services/ajax.service';
import { MessageBarService } from '../../../../common/services';
declare var $: any;
@Component({
  selector: 'app-cop06-1',
  templateUrl: './cop06-1.component.html',
  styleUrls: ['./cop06-1.component.css']
})
export class Cop061Component implements OnInit {


  constructor(
    private ajax: AjaxService,
    private messageBarService: MessageBarService
  ) {



  }

  ngOnInit() {
    this.hidedata();
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


      // get exciseId in input box
      const URL =
        AjaxService.CONTEXT_PATH + "/filter/exise/getDataExciseIdList";

    });
  }


  ngOnDestroy(): void { }


  showdata() {
    $("#showData").show();
  }
  hidedata() {
    $("#showData").hide();
  }
}

