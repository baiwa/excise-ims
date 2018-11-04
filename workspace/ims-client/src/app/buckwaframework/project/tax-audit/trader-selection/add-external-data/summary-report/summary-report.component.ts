import { Component, OnInit } from "@angular/core";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { ExciseService } from "../../../../../common/services/excise.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { BreadCrumb } from "models/breadcrumb";
import { AuthService } from "services/auth.service";

declare var jQuery: any;
declare var $: any;
@Component({
  selector: 'app-summary-report',
  templateUrl: './summary-report.component.html',
  styleUrls: ['./summary-report.component.css']
})
export class SummaryReportComponent implements OnInit {
  breadcrumb: BreadCrumb[] = [
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'การคัดเลือกราย', route: '#' },
    { label: 'รับกระดาษทำการคัดเลือกลาย', route: '#' },
  ]
  sendLineUser: any;
  listItem: any[];
  before: any;
  last: any;
  month: any;
  from: any;
  analysNumber: any = '';
  exciseProductType: any;
  flag: any;
  coordinates: any;
  coordinatesArr: any;
  workSheetNumber: any;
  exciseId: any[];
  sector: any;
  sectorArr: any;
  role: string;
  loading: boolean = true;
  dataTable: any;
  summaryReport: any[];
  constructor(
    private route: ActivatedRoute,
    private messageBarService: MessageBarService,
    private ex: ExciseService,
    private router: Router,
    private ajax: AjaxService,
    private authService: AuthService
  ) {
    this.exciseId = [];
    const URL = "filter/exise/getOfficeCodeByUserLogin";
    this.ajax.post(URL, {}, res => {
      let officeCode = res.json();
      console.log(officeCode);
      if (officeCode.substr(0, 2) == "00") {
        this.role = 'C'
      } else {
        this.role = 'S'
        this.sector = 'ภาคที่ ' + (Number(officeCode.substr(0, 2)));
      }
    });
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('TAX-01070');
    console.log("ngOnInit");
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");

    const analysUrl = "combobox/controller/getAnalysNumber";
    this.ajax.post(analysUrl, {}, res => {
      console.log(res.json()[0]);
      this.analysNumber = res.json()[0];
      this.ajax.post("filter/exise/summaryReport", { analysnumber: res.json()[0] }, res => {
        this.summaryReport = res.json();
        this.initDatatable();
      });

    });






  }




  ngAfterViewInit() { }

  // initDatatable(): void {
  //   console.log("analysNumber : ", this.analysNumber);
  //   const URL = AjaxService.CONTEXT_PATH + "filter/exise/summaryReport";
  //   var dataTable = $("#dataTable").DataTable({
  //     lengthChange: false,
  //     searching: false,
  //     ordering: false,
  //     processing: true,
  //     serverSide: true,

  //     paging: true,
  //     pagingType: "full_numbers",

  //     ajax: {
  //       type: "POST",
  //       url: URL,
  //       data: {
  //         analysNumber: this.analysNumber

  //       }
  //     },
  //     columns: [


  //       { data: "sector", className: "center" },
  //       { data: "sendDate", className: "center" },
  //       { data: "receiveDate", className: "center" }

  //     ]
  //   });


  // }

  initDatatable(): void {
    if (this.dataTable != null) {
      this.dataTable.destroy();
    }

    console.log(this.summaryReport);
    this.dataTable = $("#dataTable").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      processing: true,
      serverSide: false,
      paging: false,
      data: this.summaryReport,
      columns: [


        { data: "sector", className: "center" },
        {
          render: function (data, type, row, meta) {
            //console.log("data :", row.createdDate)
            if (row.sendDate != null && row.sendDate != undefined && row.sendDate != '') {
              var dateTime = new Date(row.sendDate).toLocaleString("th-TH");
              return dateTime.split(' ')[0];
            } else {
              return "-";
            }
          }, className: "center"
        },
        {
          render: function (data, type, row, meta) {
            //console.log("data :", row.createdDate)
            if (row.receiveDate != null && row.receiveDate != undefined && row.receiveDate != '') {
              var dateTime = new Date(row.receiveDate).toLocaleString("th-TH");
              return dateTime.split(' ')[0];
            } else {
              return "-";
            }
          }, className: "center"
        }

      ]
    });
  }





}
