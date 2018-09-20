import { Injectable } from "@angular/core";
import { AjaxService } from "services/ajax.service";
import { MessageBarService } from "services/message-bar.service";
declare var $: any;
@Injectable()
export class Int0621Service {

  table: any;
  data: [{ any }];
  model: FormSearch;
  constructor(
    private ajax: AjaxService,
    private message: MessageBarService
  ) {
    this.model = new FormSearch();
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
    this.table.on('click', 'tbody tr button.btn-edit', (e) => {
      var closestRow = $(e.target).closest('tr');
      var data = this.table.row(closestRow).data();
      console.log(data);
    });
    this.table.on('click', 'tbody tr button.btn-delete', (e) => {
      var closestRow = $(e.target).closest('tr');
      var data = this.table.row(closestRow).data();
      console.log(data);
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
      }, 'ลบรายการ', "ยืนยันการลบ");
    });
  }
}
class FormSearch {
  accountId: string = "";
  accountName: string = "";
  searchFlag: string = "";
}