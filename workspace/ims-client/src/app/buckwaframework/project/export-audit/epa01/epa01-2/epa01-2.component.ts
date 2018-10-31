import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { AjaxService } from '../../../../common/services/ajax.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ExportCheckingModel } from 'models/exportCheckingModel';

declare var $: any;

@Component({
  selector: 'app-epa01-2',
  templateUrl: './epa01-2.component.html',
  styleUrls: ['./epa01-2.component.css']
})
export class Epa012Component implements OnInit {

  datatable: any;
  exciseId: string = "";
  exciseName: string = "";
  searchFlag: string = "FALSE";
  taxStampNo = [""];
  factoryStampNo = [""];
  datas: ExportCheckingModel = new ExportCheckingModel();
  saveDatas: ExportCheckingModel = new ExportCheckingModel();
  exportType: string = "";

  constructor(
    private authService: AuthService,
    private ajaxService: AjaxService,
    private ajax: AjaxService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('EXP-01200');
    this.exciseId = this.route.snapshot.queryParams["exciseId"];
    this.exciseName = this.route.snapshot.queryParams["exciseName"];
    this.searchFlag = this.route.snapshot.queryParams["searchFlag"];
  }

  ngAfterViewInit(): void {
    this.getInformation();
    this.initDatatable();
  }

  getInformation() {
    let modalUrl = "epa/epa012/getInformation";
    this.ajaxService.post(modalUrl, { exciseId: this.exciseId }, res => {
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
    const URL = AjaxService.CONTEXT_PATH + "epa/epa012/search";
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
          data: "typeList2",
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
          render: function (data, row) {
            return '<button type="button" class="ui mini primary button checking-button"><i class="edit icon"></i>ตรวจสอบ</button>';
          }
        }
      ]
    });

    this.datatable.on('click', 'tbody tr button.checking-button', (e) => {
      var closestRow = $(e.target).closest('tr');
      var data = this.datatable.row(closestRow).data();
      
      this.datas.exciseId = data.exciseId;
      this.datas.taxReNumber2 = data.taxReNumber2;
      this.datas.exciseName = data.exciseName;
      this.datas.officeArea = data.officeArea;
      this.datas.destination2 = data.destination2;
      this.datas.dateOut2 = data.dateOut2;
      this.datas.productName2 = data.productName2;
      this.datas.quantity = data.quantity;
      this.datas.remark = "";

      this.saveDatas.exciseId2 = data.exciseId2;
      this.saveDatas.taxReNumber2 = data.taxReNumber2;
      this.saveDatas.exciseName2 = data.exciseName2;
      this.saveDatas.officeArea = data.officeArea;
      this.saveDatas.destination2 = data.destination2;
      this.saveDatas.dateOut2 = data.dateOut2;
      this.saveDatas.productName2 = data.productName2;
      this.saveDatas.quantity = data.quantity;
      this.saveDatas.result = "1";

      /* Modal Here */
      // console.log("[data] : ", data);
      $('#ModalCheck').modal('show');
    });

  };

  onClickBack() {
    this.router.navigate(["/epa01/1"], {
      queryParams: {
        exciseId: this.exciseId,
        exciseName: this.exciseName,
        searchFlag: "TRUE"
      }
    });
  };

  addTaxField() {
    this.taxStampNo.push("");
  }

  delTaxField(index) {
    if (this.taxStampNo.length != 1) {
      this.taxStampNo.splice(index, 1);
    }
  }

  addFactoryField() {
    this.factoryStampNo.push("");
  }

  delFactoryField(index) {
    if (this.factoryStampNo.length != 1) {
      this.factoryStampNo.splice(index, 1);
    }
  }

  onClickSave() {
    $('#ModalCheck').modal('hide');
    setTimeout(() => {

      if (this.datas.remark != "") {

        let url = "epa/epa012/saveTaxDatas";
        this.ajaxService.post(url, this.datas, res => { });

        let url2 = "epa/epa012/saveFactoryDatas";
        this.saveDatas.result = this.datas.result;
        this.saveDatas.remark = this.datas.remark;
        this.ajaxService.post(url2, this.saveDatas, res => { });

        this.router.navigate(["/epa01/3"], {
          queryParams: {
            exciseId: this.exciseId,
            searchFlag: "TRUE"
          }
        });

      } else {
        $('#ModalAlert').modal('show');
      }

    }, 300);
  }

  onClickClose() {
    $('#ModalAlert').modal('hide');
    $('#ModalCheck').modal('show');
  }

}
