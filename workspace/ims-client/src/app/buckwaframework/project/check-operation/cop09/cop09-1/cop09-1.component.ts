import { Component, OnInit } from "@angular/core";
import { TextDateTH, formatter, stringToDate } from "../../../../common/helper/datepicker";
import { AjaxService, MessageBarService, AuthService } from "../../../../common/services";
import { TravelCostHeader } from "../../../../common/models";
import { Router, ActivatedRoute } from "@angular/router";
import { TravelCostDetail } from "app/buckwaframework/common/models/travelcostdetail";
import { IaService } from 'app/buckwaframework/common/services/ia.service';
import { BreadCrumb } from 'models/index';
import { setTimeout } from "timers";


declare var $: any;
@Component({
  selector: "app-cop09-1",
  templateUrl: "./cop09-1.component.html",
  styleUrls: ["./cop09-1.component.css"]
})
export class Cop091Component implements OnInit {

  searchFlag: String;
  breadcrumb: BreadCrumb[];

  totalAsPlanNumber: Number = 0;
  totalAsPlanSuccess: Number = 0;
  totalAsPlanWait: Number = 0;
  totalOutsidePlanNumber: Number = 0;
  totalOutsidePlanSuccess: Number = 0;
  totalOutsidePlanWait: Number = 0;

  dataList:any;
  

  constructor(
    private message: MessageBarService,
    private ajax: AjaxService,
    private route: ActivatedRoute,
    private router: Router,
    private dataService: IaService,
    private authService: AuthService,
    private iaService: IaService,
    private msg: MessageBarService
  ) {
    this.breadcrumb = [
      { label: "ตรวจปฏิบัติการ", route: "#" },
      { label: "ตรวจสอบข้อมูล", route: "#" }
    ];

  }

  calenda = () => {

    $("#date").calendar({
      type: "year",
      text: TextDateTH,
      formatter: formatter("ป")
    });
    $("#dateModal").calendar({
      type: "month",
      text: TextDateTH,
      formatter: formatter("month-year")
    });

  }

  clickSearch = ()=> {
    if ($("#fiscalYear").val() == "") {
      this.message.alert("กรุณาระบุ แผนการตรวจปฏิบัติการประจำปี");
      return false;
    }

    $("#searchFlag").val("TRUE");
    this.dataTable();
    this.dataTable2();
  }

  dataTable = () => {
    if ($('#tableData').DataTable() != null) { $('#tableData').DataTable().destroy(); };
    var table = $('#tableData').DataTableTh({
      "lengthChange": true,
      "serverSide": false,
      "searching": false,
      "ordering": false,
      "processing": true,
      "scrollX": true,
      "ajax": {
        "url": '/ims-webapp/api/cop/cop091/list',
        "contentType": "application/json",
        "type": "POST",
        "data": (d) => {
          return JSON.stringify($.extend({}, d, {
            "searchFlag": $("#searchFlag").val(),
            "fiscalYear": $("#fiscalYear").val()
          }));
        },
      },
      "columns": [
        {
          "className": "ui center aligned",
          "render": (data, row)=> {
            return  '<button class="mini ui primary button btn-record" type="button" ><i class="edit icon"></i>เลือก</button>';
          }
        }, {
          "data": "id",
          "className": "ui center aligned",
          "render":  (data, type, row, meta) => {
            return meta.row + meta.settings._iDisplayStart + 1;
          }
        }, {
          "data": "entrepreneurNo", "className": "ui center aligned"
        }, {
          "data": "entrepreneurName"
        }, {
          "data": "entrepreneurLoca"
        }, {
          "data": "checkDate", "className": "ui center aligned"
        }, {
          "data": "actionPlan", "className": "ui center aligned",
          "render":  (data, type, row, meta) => {
            let s = '';
            if (data == '1871') {
              s = 'ตามแผนปฏิบัติการ';
            } else {
              s = 'นอกแผนปฏิบัติการ';
            }
            return s;
          }
        }, {
          "data": "status", "className": "ui center aligned",
          "render": (data, type, row, meta) => {
            let s = '';
            if (data == '1874') {
              s = 'เสร็จสิ้น';
            } else {
              s = 'รอการดำเนินการ';
            }
            return s;
          }
        }
      ]
    });
    table.on('click', 'tbody tr button.btn-record',(e)=> {
      var closestRow = $(e.target).closest('tr');
      var data = table.row(closestRow).data();
      this.setDataT(data);
     this.showModalTable();
    });
  }

  dataTable2 = () => {
    if ($('#tableData2').DataTable() != null) { $('#tableData2').DataTable().destroy(); };
    var table2 = $('#tableData2').DataTableTh({
      "lengthChange": true,
      "serverSide": false,
      "searching": false,
      "ordering": false,
      "processing": true,
      "scrollX": true,
      "ajax": {
        "url": '/ims-webapp/api/cop/cop091/list2',
        "contentType": "application/json",
        "type": "POST",
        "data": (d) => {
          return JSON.stringify($.extend({}, d, {
            "searchFlag": $("#searchFlag").val(),
            "fiscalYear": $("#fiscalYear").val()
          }));
        },
      },
      "columns": [
        {
          "className": "ui center aligned",
          "render": (data, row)=> {
            return '<button class="mini ui primary button btn-record2" type="button" ><i class="edit icon"></i>เลือก</button>';
          }
        }, {
          "data": "id",
          "className": "ui center aligned",
          "render": (data, type, row, meta) => {
            return meta.row + meta.settings._iDisplayStart + 1;
          }
        }, {
          "data": "entrepreneurNo", "className": "ui center aligned"
        }, {
          "data": "entrepreneurName"
        }, {
          "data": "entrepreneurLoca"
        }, {
          "data": "checkDate", "className": "ui center aligned"
        }, {
          "data": "actionPlan", "className": "ui center aligned",
          "render":  (data, type, row, meta) => {
            let s = '';
            if (data == '1871') {
              s = 'ตามแผนปฏิบัติการ';
            } else {
              s = 'นอกแผนปฏิบัติการ';
            }
            return s;
          }
        }, {
          "data": "status", "className": "ui center aligned",
          "render":  (data, type, row, meta) =>{
            let s = '';
            if (data == '1874') {
              s = 'เสร็จสิ้น';
            } else {
              s = 'รอการดำเนินการ';
            }
            return s;
          }
        }
      ]
    });

    table2.on('click', 'tbody tr button.btn-record2',(e)=> {
      var closestRow = $(e.target).closest('tr');
      var data = table2.row(closestRow).data();
      this.setDataT(data);
     this.showModalTable();
    });

  };

  showModalTable(){
     $('#modalTsl').modal({
        onShow: () => {
          setTimeout(() => {
            this.calenda();
          }, 500);
        }
      }).modal('show');
  }
  setDataT=(data)=>{
    console.log("setDataT :",data);
    this.dataList = { analysisNumber: "25611115-01-03721",
    companyAddress: "252/133 อาคาร- ซอย- ถนนรัชดาภิเษก แขวงห้วยขวาง เขตห้วยขวาง  จังหวัดกรุงเทพมหานคร 10310",
    companyName: "บริษัท ดัชมิลด์ ดิไวซ์ เซลส์ (ประเทศไทย) จำกัด",
    exciseArea: "สรรพสามิตภาคที่ 1",
    exciseId: "0105540039831-3-001",
    exciseSubArea: "สรรพสามิตพื้นที่ปทุมธานี 2",
    flag: "1",
    flagDesc: "รอดำเนินการ",
    product: null,
    riskType: "1",
    riskTypeDesc: "ความถี่ของเดือนที่ชำระภาษี",
    taYearPlanId: 63,
    userId: null
    }
  }

  onClickOK() {
    $('#modalTsl').modal('hide');

    this.dataService.setData(this.dataList);
    setTimeout(() => {
      // this.router.navigate(["/cop09/2"],
    this.router.navigate(["/tax-audit-select-line/tsl0107-00"],
    {
      queryParams: {
        "dateCalendar": $("#dateM").val(),
        "searchFlag": "TRUE"
      }
    }
  );
    }, 500);
   
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('COP-09100');

    this.calenda();
    this.dataTable();
    this.dataTable2();

  
  }

}
