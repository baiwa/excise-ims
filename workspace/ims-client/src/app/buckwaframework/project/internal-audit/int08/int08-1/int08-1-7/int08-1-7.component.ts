import { Component, OnInit, AfterViewInit } from "@angular/core";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
declare var $: any;
@Component({
  selector: 'app-int08-1-7',
  templateUrl: './int08-1-7.component.html',
  styleUrls: ['./int08-1-7.component.css']
})
export class Int0817Component implements OnInit {

  riskHrdPaperName: any;
  budgetYear: any;
  userCheck: any;
  columnList: any[];
  datatable: any;
  constructor(private router: Router,
    private ajax: AjaxService,
    private messageBarService: MessageBarService,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.columnList = [];
    this.budgetYear = this.route.snapshot.queryParams["budgetYear"];
    this.queryColumnNameListAndSetColumn();
  }

  queryColumnNameListAndSetColumn() {
    var url = "ia/int08/findByBudgetYear";

    this.ajax.post(url, { budgetYear: this.budgetYear }, res => {
      console.log(riskAssRiskWsHdr);
      var riskAssRiskWsHdr = res.json();
      for (let i = 0; i < riskAssRiskWsHdr.length; i++) {
        const element = riskAssRiskWsHdr[i];
        this.columnList.push(element.riskHdrName);
      }

      var trHTML = '<tr><th rowspan="2">ลำดับ</th> <th rowspan="2">โครงการตามยุทธศาสตร์</th><th rowspan="2">หน่วยงาน</th>';
      this.columnList.forEach(element => {
        console.log(element);
        trHTML += '<th rowspan="2">' + element + '</th>';
      });
      trHTML += '<th rowspan="2">รวม</th><th colspan="2">ประเมินความเสี่ยง</th></tr><tr><th>RL</th><th>แปลค่า</th></tr>';
      $("#trColumn").html(trHTML);
    }, errRes => {
      console.log(errRes);
    });
  }


  initDatatable(): void {
    const URL = AjaxService.CONTEXT_PATH + "ia/int08/searchRiskAssRiskWsHdr";
    console.log(URL);
    this.datatable = $("#dataTable").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: true,
      paging: false,
      ajax: {
        type: "POST",
        url: URL,
        data: { budgetYear: this.budgetYear }
      },
      columns: [

        { render: function (data, type, row, meta) { return meta.row + meta.settings._iDisplayStart + 1; }, className: "center" },
        { data: "riskHdrName" },
        { data: "budgetYear" },
        { data: "createdBy" },
        { data: "createdDate" },
        { data: "active" }
      ]

    });
  }





}
