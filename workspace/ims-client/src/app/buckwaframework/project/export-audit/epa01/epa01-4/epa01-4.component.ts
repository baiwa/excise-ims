import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { AjaxService } from '../../../../common/services/ajax.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ExportCheckingModel } from 'models/exportCheckingModel';
import { ExportCheckingReportModel } from 'models/exportCheckingReportModel';

declare var $: any;

@Component({
  selector: 'app-epa01-4',
  templateUrl: './epa01-4.component.html',
  styleUrls: ['./epa01-4.component.css']
})
export class Epa014Component implements OnInit {

  datatable: any;
  exciseId: string = "";
  exciseName: string = "";
  searchFlag: string = "FALSE";
  taxStampNo = [""];
  factoryStampNo = [""];
  datas: ExportCheckingModel = new ExportCheckingModel();
  saveDatas: ExportCheckingModel = new ExportCheckingModel();
  reportDatas: ExportCheckingReportModel = new ExportCheckingReportModel();
  exportType: string = "";
  
  constructor(
    private authService: AuthService,
    private ajaxService: AjaxService,
    private ajax: AjaxService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('EXP-01400');
    this.exciseId = this.route.snapshot.queryParams["exciseId"];
    this.exciseName = this.route.snapshot.queryParams["exciseName"];
    this.searchFlag = this.route.snapshot.queryParams["searchFlag"];
  }

  ngAfterViewInit(): void {
    this.getInformation();
    this.initDatatable();
  }

  getInformation() {
    let url = "epa/epa014/getInformation";
    this.ajaxService.post(url, { exciseId: this.exciseId }, res => {
      this.datas.exciseName = res.json()[0].exciseName;
      this.datas.exportType = res.json()[0].exportType;
      if ('1' == this.datas.exportType) {
        this.exportType = "ส่งออกนอกราชอาณาจักร";
      }
      if ('2' == this.datas.exportType) {
        this.exportType = "นําเข้าไปในเขตปลอดอากร";
      }
      this.datas.exciseName2 = res.json()[0].exciseName2;
      this.datas.exciseId2 = res.json()[0].exciseId2;
    });
  }

  initDatatable = () => {
    const URL = AjaxService.CONTEXT_PATH + "epa/epa014/search";
    this.datatable = $("#dataTable").DataTableTh({
      serverSide: true,
      searching: false,
      processing: true,
      ordering: false,
      scrollX: true,
      ajax: {
        type: "POST",
        url: URL,
        contentType: "application/json",
        data: (d) => {
          return JSON.stringify($.extend({}, d, {
            "exciseId": this.exciseId,
            "searchFlag": this.searchFlag
          }));
        }
      },
      columns: [
        {
          className: "ui center aligned",
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          }
        }, {
          data: "eaReInventoryNumber",
          className: "ui center aligned",
        }, {
          data: "productName2",
          className: "ui center aligned",
        }, {
          data: "model2",
          className: "ui center aligned",
        }, {
          data: "size12",
          className: "ui center aligned",
        }, {
          data: "amount2",
          className: "ui center aligned",
        }, {
          data: "price2",
          className: "ui center aligned",
        }, {
          data: "pricePer2",
          className: "ui center aligned",
        }, {
          data: "amountPer2",
          className: "ui center aligned",
        }, {
          data: "tax2",
          className: "ui center aligned",
        }, {
          data: "tax2",
          className: "ui center aligned",
        }, {
          data: "tax2",
          className: "ui center aligned",
          render: function (data, row) {
            return '<button type="button" class="ui mini primary button checking-button"><i class="edit icon"></i>รายงานการตรวจสอบ</button>';
          }
        }
      ]
    });

    this.datatable.on('click', 'tbody tr button.checking-button', (e) => {
      let closestRow = $(e.target).closest('tr');
      let data = this.datatable.row(closestRow).data();

      console.log("[data]: ", data);
      this.datas.exciseId = data.exciseId;
      this.datas.taxReNumber2 = data.taxReNumber2;
      this.datas.exciseName = data.exciseName;
      this.datas.officeArea = data.officeArea;
      this.datas.destination2 = data.destination2;
      this.datas.dateOut2 = data.dateOut2;
      this.datas.productName2 = data.productName2;
      this.datas.quantity = data.quantity;
      this.datas.resultDtl = data.resultDtl;
      this.datas.comment1Dtl = data.comment1Dtl;

      this.saveDatas.exciseId2 = data.exciseId2;
      this.saveDatas.taxReNumber2 = data.taxReNumber2;
      this.saveDatas.exciseName2 = data.exciseName2;
      this.saveDatas.officeArea = data.officeArea;
      this.saveDatas.destination2 = data.destination2;
      this.saveDatas.dateOut2 = data.dateOut2;
      this.saveDatas.productName2 = data.productName2;
      this.saveDatas.quantity = data.quantity;

      $('#ModalShow').modal('show');
    });

  }

  onClickBack() {
    this.router.navigate(["/epa01/3"], {
      queryParams: {
        exciseId: this.exciseId,
        exciseName: this.exciseName,
        searchFlag: "TRUE"
      }
    });
  };

}
