import { Observable } from "rxjs/Observable";
import { ComboBox } from "models/combobox";
import { AjaxService } from "services/ajax.service";
import { Injectable } from "@angular/core";

import { Router } from "@angular/router";
import { MessageBarService } from "services/message-bar.service";
import { Utils } from "helpers/utils";
import { MessegeConstants } from "helpers/messegeConstants";



const URL = {
  DROPDOWN: "combobox/controller/getDropByTypeAndParentId"
};
declare var $: any;
@Injectable()
export class Int0615Service {

  //==> params
  table: any;
  form: any = {
    sector: "",
    area: "",
    startDate: "",
    endDate: "",
    money: "",
    searchFlag: "FALSE"
  }
  constructor(private ajax: AjaxService,
    private msg: MessageBarService,
    private router: Router) { }



  dropdown = (type: string, id?: number): Observable<any> => {
    const DATA = { type: type, lovIdMaster: id || null };
    return new Observable<ComboBox[]>(obs => {
      this.ajax
        .post(URL.DROPDOWN, DATA, res => {
          this[type] = res.json();
        })
        .then(() => {
          obs.next(this[type]);
        });
    });
  };

  search = (form) => {
    this.form.sector = form.sector;
    this.form.area = form.area;
    this.form.startDate = form.startDate;
    this.form.endDate = form.endDate;
    this.form.money = form.money;
    this.form.searchFlag = "TRUE";
    this.table.ajax.reload();
  }
  clear = (form) => {
    this.form.sector = form.sector
    this.form.area = form.area
    this.form.startDate = form.startDate
    this.form.endDate = form.endDate
    this.form.money = form.money
    this.form.searchFlag = "FALSE";
    this.table.ajax.reload();
  }

  checkAll = event => {
    var rows = this.table.rows({ search: "applied" }).nodes();
    $('input[type="checkbox"]', rows).prop("checked", event.target.checked);

  }
  save = () => {
    this.msg.comfirm((res) => {
      if (res) {
        let dataSave = [];

        let node = this.table.rows().nodes();
        $.each(node, function (index, value) {
          if ($(this).find("input[type=checkbox]").is(":checked")) {

            let data = $("#dataTable").DataTable().data()[index];
            dataSave.push(data);
          }
        });
        console.log("dataSave : ", dataSave);

        let url = "ia/int0615/save"
        this.ajax.post(url, JSON.stringify({ dataList: dataSave }), res => {
          this.msg.successModal("บันทึกรายการสำเร็จ");
        })
      }
    }, "บันทึกรายการ");
  }
  datatable = () => {
    this.table = $("#dataTable").DataTableTh({
      "serverSide": false,
      "processing": true,
      "scrollX": true,
      "paging": true,
      "ajax": {
        "url": '/ims-webapp/api/ia/int0615/findAll',
        "contentType": "application/json",
        "type": "POST",
        data: (d) => {
          return JSON.stringify($.extend({}, d, {
            sector: this.form.sector,
            area: this.form.area,
            startDate: this.form.startDate,
            endDate: this.form.endDate,
            money: this.form.money,
            searchFlag: this.form.searchFlag
          }));
        },
      },
      columns: [
        {
          render: function (data, type, full, meta) {
            return `<input class="ui checkbox" type="checkbox" name="chk${meta.row}" id="chk${
              meta.row
              }" value="${$("<div/>")
                .text(data)
                .html()}">`;
          },
          className: "center"
        },
        {
          data: "name",
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "ui center aligned"
        },
        {
          data: "name",
          className: "ui left aligned"
        },
        {
          data: "position",
          className: "ui left aligned"
        }, {
          data: "flag",
          className: "ui left aligned",
          render: (data, row) => {
            return this.checkType(data);
          }
        }, {
          data: "money",
          className: "ui right aligned",
          render: (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
      ]
    });
  }

  checkType(type) {
    var result = '';
    if (type == MessegeConstants.WIDTHDRAW.HOME_CODE) {
      result = MessegeConstants.WIDTHDRAW.HOME
    }
    if (type == MessegeConstants.WIDTHDRAW.MEDICAL_CODE) {
      result = MessegeConstants.WIDTHDRAW.MEDICAL
    }
    if (type == MessegeConstants.WIDTHDRAW.TUITO_CODE) {
      result = MessegeConstants.WIDTHDRAW.TUITO
    }
    return result;
  }

}