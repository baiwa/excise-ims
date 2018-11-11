import { Component, AfterViewInit } from "@angular/core";
import * as Chart from "chart.js";
import { AuthService } from "services/auth.service";
import { Cop10Service } from "projects/check-operation/cop10/cop10.service";
import { TextDateTH, formatter ,stringToDate} from "../../../common/helper/datepicker";
declare var $: any;
@Component({
  selector: "app-cop10",
  templateUrl: "./cop10.component.html",
  styleUrls: ["./cop10.component.css"],
  providers: [Cop10Service]
})
export class Cop10Component implements AfterViewInit {
  canvas: any;
  ctx: any;
  fiscalYear:any = 0;

  constructor(
    private authService: AuthService,
    private cop10Service: Cop10Service
  ) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-07100');
    this.calenda();   
  }
  calenda = function () {
   
    $("#date").calendar({
      type: "year",
      text: TextDateTH,
      formatter: formatter("ป")
    });
  }

  clickSearch = function () {
    if ($("#fiscalYear").val()=="") {
      this.message.alert("กรุณาระบุ แผนการตรวจปฏิบัติการประจำปี");
      return false;
    }
    $("#searchFlag").val("TRUE");
    this.showChart()
  }
  ngAfterViewInit() {
    this.authService.reRenderVersionProgram('COP-10000');
    this.showChart();
  }

  showChart(){
    this.fiscalYear = ($("#fiscalYear").val()=="")?0:$("#fiscalYear").val();

    this.cop10Service.getData(this.fiscalYear).then(res => {
     
      let dataCop071 = res.data;
      console.log("dataCop071 : ",dataCop071);
      this.canvas = <HTMLCanvasElement>document.getElementById("myChart2");
      this.ctx = this.canvas.getContext("2d");
      let myChart = new Chart(this.ctx, {
        type: "bar",
        data: {
          labels: this.gatFiscalYearText(dataCop071),
          datasets: [
            { label: "จำนวน ตามแผนปฏิบัติการ",
              data: this.gatAsPlanNumber(dataCop071),
              fill: false,
              backgroundColor: this.getBackgroundColor("rgb(52, 152, 219)"),
            },
            { label: "เสร็จสิ้น ตามแผนปฏิบัติการ",
              data: this.gatAsPlanSuccess(dataCop071),
              fill: false,
              backgroundColor: this.getBackgroundColor("rgb(46, 204, 113)"),
            },
            { label: "รอดำเนินการ ตามแผนปฏิบัติการ",
              data: this.gatAsPlanWait(dataCop071),
              fill: false,
              backgroundColor: this.getBackgroundColor("rgb(230, 126, 34)"),
            },
            { label: "จำนวน นอกแผนปฏิบัติการ",
              data: this.gatOutsidePlanNumber(dataCop071),
              fill: false,
              backgroundColor: this.getBackgroundColor("rgb(36, 113, 163)"),
            },
            { label: "เสร็จสิ้น นอกแผนปฏิบัติการ",
              data: this.gatOutsidePlanSuccess(dataCop071),
              fill: false,
              backgroundColor: this.getBackgroundColor("rgb(34, 153, 84)"),
            },
            { label: "รอดำเนินการ นอกแผนปฏิบัติการ",
              data: this.gatOutsidePlanWait(dataCop071),
              fill: false,
              backgroundColor: this.getBackgroundColor("rgb(203, 67, 53)"),
            }
          ]
        },
        options: {
          responsive: false,
          display: true,
          scales: {
            yAxes: [
              {
                ticks: {
                  beginAtZero: true
                }
              }
            ]
          }
        }
      });
    });

  }
  getBackgroundColor = (color) => {
    let list = [];
   for(let i=0;i<12;i++){
    list.push(color);
   }

  //  "rgb(0, 204, 0)", //1
  // "rgb(0, 0, 255)", //2
  // "rgb(255, 0, 0)", //3
  // "rgb(102, 0, 0)", //4
  // "rgb(255, 102, 0)", //5
  // "rgb(205, 92, 92)", //6
  // "rgb(148, 0, 211)", //7
  // "rgb(0, 229, 238)", //8
  // "rgb(255, 193, 193)", //9
  // "rgb(28, 28, 28)", //10
  // "rgb(119, 136, 153)", //ส่วนกลาง
   return list;
  } 

  gatFiscalYearText = (dataCop071) => {
    let list = [];
   for(let i=0;i<12;i++){
    list.push(dataCop071[i].fiscalYearText);
   }
   return list;
  }
  // ตามแผนปฏิบัติการ
  gatAsPlanNumber = (dataCop071) => {
    let list = [];
   for(let i=0;i<12;i++){
    list.push(dataCop071[i].asPlanNumber);
   }
   return list;
  }
  gatAsPlanSuccess = (dataCop071) => {
    let list = [];
   for(let i=0;i<12;i++){
    list.push(dataCop071[i].asPlanSuccess);
   }
   return list;
  }
  gatAsPlanWait = (dataCop071) => {
    let list = [];
   for(let i=0;i<12;i++){
    list.push(dataCop071[i].asPlanWait);
   }
   return list;
  }

  // นอกแผนปฏิบัติการ
  gatOutsidePlanNumber = (dataCop071) => {
    let list = [];
   for(let i=0;i<12;i++){
    list.push(dataCop071[i].outsidePlanNumber);
   }
   return list;
  }
  gatOutsidePlanSuccess = (dataCop071) => {
    let list = [];
   for(let i=0;i<12;i++){
    list.push(dataCop071[i].outsidePlanSuccess);
   }
   return list;
  }
  gatOutsidePlanWait = (dataCop071) => {
    let list = [];
   for(let i=0;i<12;i++){
    list.push(dataCop071[i].outsidePlanWait);
   }
   return list;
  }
 
}
