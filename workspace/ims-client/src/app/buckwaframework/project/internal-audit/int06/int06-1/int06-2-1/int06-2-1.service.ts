import { Injectable } from "@angular/core";
import { AjaxService } from "services/ajax.service";
import { MessageBarService } from "services/message-bar.service";
import { IaService } from "services/ia.service";
import { Router } from "@angular/router";
declare var $: any;
@Injectable()
export class Int0621Service {

  table: any;
  data: [{ any }];
  model: FormSearch;
  formEdit: FormEdit;
  constructor(
    private ajax: AjaxService,
    private message: MessageBarService,
    private iaService: IaService,
    private route: Router
  ) {
    this.model = new FormSearch();
    this.formEdit = new FormEdit();

  }

  setSearchFlag(searchFlag: string) {
    this.model.searchFlag = searchFlag;
  }

  search(model: FormSearch) {
    this.model = model;
    $("#dataTable").DataTable().ajax.reload();
  }
  dataTable = () => {
    this.table = $("#dataTable").DataTable({
      "serverSide": true,
      "searching": false,
      "ordering": false,
      "processing": true,
      "scrollX": true,
      "fixedColumns": {
        "leftColumns": 3
      },
      "ajax": {
        "url": '/ims-webapp/api/ia/int06121/findAll',
        "contentType": "application/json",
        "type": "POST",
        "data": (d) => {
          return JSON.stringify($.extend({}, d, {
            "accountId": this.model.accountId,
            "accountName": this.model.accountName,
            "searchFlag": this.model.searchFlag
          }));
        },
      },
      "columns": [
        {
          "data": "accountId",
          "render": function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          "className": "ui center aligned"
        }, {
          "data": "accountId"
        }, {
          "data": "accountName",
          "className": "ui center aligned",
        }, {
          "data": "serviceReceive",
          "className": "ui center aligned"
        }, {
          "data": "suppressReceive",
          "className": "ui center aligned"
        }, {
          "data": "budgetReceive",
          "className": "ui center aligned"
        }, {
          "data": "sumReceive",
          "className": "ui center aligned"
        }, {
          "data": "serviceWithdraw",
          "className": "ui center aligned"
        }, {
          "data": "suppressWithdraw",
          "className": "ui center aligned"
        }, {
          "data": "budgetWithdraw",
          "className": "ui center aligned"
        }, {
          "data": "sumWithdraw",
          "className": "ui center aligned"
        }, {
          "data": "serviceBalance",
          "className": "ui center aligned"
        }, {
          "data": "suppressBalance",
          "className": "ui center aligned"
        }, {
          "data": "budgetBalance",
          "className": "ui center aligned"
        }, {
          "data": "sumBalance",
          "className": "ui center aligned"
        }, {
          "data": "averageCost",
          "className": "ui center aligned"
        }, {
          "data": "averageGive",
          "className": "ui center aligned"
        }, {
          "data": "averageFrom",
          "className": "ui center aligned"
        }, {
          "data": "averageComeCost",
          "className": "ui center aligned"
        }, {
          "data": "moneyBudget",
          "className": "ui center aligned"
        }, {
          "data": "moneyOut",
          "className": "ui center aligned"
        }, {
          "data": "note",
          "className": "ui center aligned"
        }, {
          "data": "note",
          "render": function (data, type, row) {
            var btn = '';
            btn += '<button class="ui mini yellow button btn-edit">แก้ไข</button>';
            btn += '<button class="ui mini red button btn-delete">ลบ</button>';
            return btn;
          },
          "className": "ui center aligned"
        }
      ]
    });
    // edit
    this.table.on('click', 'tbody tr button.btn-edit', (e) => {
      var closestRow = $(e.target).closest('tr');
      var data = this.table.row(closestRow).data();
      this.formEdit = data;

      this.formEdit.accountId = data.accountId;
      this.formEdit.accountName = data.accountName;

      this.formEdit.serviceReceive = parseInt(data.serviceReceive);
      this.formEdit.suppressReceive = parseInt(data.suppressReceive);
      this.formEdit.budgetReceive = parseInt(data.budgetReceive);
      this.formEdit.sumReceive = parseInt(data.sumReceive);

      this.formEdit.serviceWithdraw = parseInt(data.serviceWithdraw);
      this.formEdit.suppressWithdraw = parseInt(data.suppressWithdraw);
      this.formEdit.budgetWithdraw = parseInt(data.budgetWithdraw);
      this.formEdit.sumWithdraw = parseInt(data.sumWithdraw);

      this.formEdit.serviceBalance = parseInt(data.serviceBalance);
      this.formEdit.suppressBalance = parseInt(data.suppressBalance);
      this.formEdit.budgetBalance = parseInt(data.budgetBalance);
      this.formEdit.sumBalance = parseInt(data.sumBalance);

      this.formEdit.moneyBudget = parseInt(data.moneyBudget);
      this.formEdit.moneyOut = parseInt(data.moneyOut);

      this.formEdit.averageCost = parseInt(data.averageCost);
      this.formEdit.averageGive = data.averageGive;
      this.formEdit.averageFrom = parseInt(data.averageFrom);
      this.formEdit.averageComeCost = data.averageComeCost;

      this.formEdit.note = data.note;
      this.formEdit.editStatus = "edit";
      this.iaService.setData(this.formEdit);

      this.route.navigate(['/int06/1/2-2']);
    });
    // delete
    this.table.on('click', 'tbody tr button.btn-delete', (e) => {
      var closestRow = $(e.target).closest('tr');
      var data = this.table.row(closestRow).data();
      this.message.comfirm((res) => {
        if (res) {
          let url = "ia/int06121/delete";
          this.ajax.post(url, JSON.stringify(data.id), res => {
            console.log(res.json());
            this.message.successModal(res.json());
            $("#dataTable").DataTable().ajax.reload();
          }, error => {
            console.log(error.json());
            this.message.errorModal(error.json());
          });
        }
      }, '', "ยืนยันการลบ");
    });
  }
}
class FormSearch {
  accountId: string = "";
  accountName: string = "";
  searchFlag: string = "";
}

class FormEdit {
  accountId: string = "";
  accountName: string = "";

  serviceReceive: number = 0;
  suppressReceive: number = 0;
  budgetReceive: number = 0;
  sumReceive: number = 0;

  serviceWithdraw: number = 0;
  suppressWithdraw: number = 0;
  budgetWithdraw: number = 0;
  sumWithdraw: number = 0;

  serviceBalance: number = 0;
  suppressBalance: number = 0;
  budgetBalance: number = 0;
  sumBalance: number = 0;

  moneyBudget: number = 0;
  moneyOut: number = 0;

  averageCost: number = 0;
  averageGive: string = "";
  averageFrom: number = 0;
  averageComeCost: string = "";

  note: string = "";
  editStatus: string = "";
}
