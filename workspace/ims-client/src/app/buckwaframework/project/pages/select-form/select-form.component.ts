import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

// services
import { MessageBarService } from '../../../common/services/message-bar.service';

// models
import { Message } from '../../../common/models/message';
import { AjaxService } from '../../../common/services';
import { TextDateTH } from '../../../common/helper';

declare var jQuery: any;
declare var $: any;

@Component({
    selector: 'select-form',
    templateUrl: 'select-form.component.html',
    styleUrls: ['select-form.component.css']
})
export class SelectFormComponent implements OnInit {
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
    private showSubMenuMat: boolean = false;
    private showSubMenuIns: boolean = false;
    private showSubMenuTax: boolean = false;
    private coordinate: String;
    private routerOpe051: String;

    constructor(
        private messageBarService: MessageBarService,
        private router: Router,
        private route: ActivatedRoute,
        private ajax: AjaxService,
       
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
        this.fileExel = new Array<File>(); 
    }

    ngOnInit(): void {
        this.coordinate = this.route.snapshot.params['coordinate'];
        if (this.coordinate == "สนามกอล์ฟ") {
            this.routerOpe051 = "/ope05-1-1";
        } else {
            this.routerOpe051 = "/ope05-1";
        }

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
            this.obj = res[0];
            this.firstDataList = res[0];
          });
        });
    }

    ngAfterViewInit(): void {
        $("#showData").hide();
      }

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
        $("#showData").hide();
        // this.showDt.fnClearTable();
        this.showDt.clear().draw();
      };

    clickMenuMat() {
        this.showSubMenuMat = true;
        this.showSubMenuIns = false;
        this.showSubMenuTax = false;
    }

    clickMenuIns() {
        this.showSubMenuMat = false;
        this.showSubMenuIns = true;
        this.showSubMenuTax = false;
    }

    clickMenuTax() {
        this.showSubMenuMat = false;
        this.showSubMenuIns = false;
        this.showSubMenuTax = true;
    }
}
class Data {
    companyName: any = "";
    startDate: any = "";
    endDate: any = "";
    analysNumber: any = "";
    startDateSplit: any = "";
    endDateSplit: any = "";
}
