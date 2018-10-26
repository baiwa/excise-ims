import { Injectable } from "@angular/core";
import { AjaxService } from "services/ajax.service";
import { MessageBarService } from "../../../../common/services";
import { FormSearch } from "projects/internal-audit/int06/int06-6/form-search.model";
import { Utils } from "helpers/utils";


declare var $: any;
const URL = {
  export:"ia/int066/exportFile"
}
@Injectable()
export class Int066Service {
  form: FormSearch = new FormSearch();
  constructor(
    private ajax: AjaxService,
    private message: MessageBarService
  ) {
    // TODO
  }

  sector = (): Promise<any> => {
    let url = "ia/int066/sector";

    return new Promise((resolve, reject) => {
      this.ajax.get(url, res => {
        resolve(res.json())
      })
    });
  }
  area = (e) => {
    let url = "ia/int066/area";
    return new Promise((resolve, reject) => {
      this.ajax.post(url, JSON.stringify(e), res => {
        resolve(res.json())
      })
    });
  }
  branch = (e) => {
    let url = "ia/int066/branch";
    return new Promise((resolve, reject) => {
      this.ajax.post(url, JSON.stringify(e), res => {
        resolve(res.json())
      })
    });
  }

  budgetType = () => {
    let url = "ia/int066/budgetType";
    return new Promise((resolve, reject) => {
      this.ajax.get(url, res => {
        resolve(res.json())
      });
    });
  }

  
  exportFile=()=>{
    
    let param = "";

    param +="?sector=" +  this.form.sector;
    param +="&area=" +  this.form.area;
    param +="&branch=" + this.form.branch;
    param +="&dateFrom=" + this.form.dateFrom;
    param +="&dateTo=" + this.form.dateTo;
    param +="&budgetType=" +$("#budgetType").val();
    console.log(URL.export+param);
    this.ajax.download(URL.export+param);

    

  }

  search = () => {
    this.form.searchFlag = "TRUE";
    $("#dataTable").DataTable().ajax.reload();
  }
  clear = () => {
    this.form.searchFlag = "FALSE";
    $(".office").dropdown('restore defaults');
    $("#dateFrom").val("");
    $("#dateTo").val("");
    $("#dataTable").DataTable().ajax.reload();

  }
  dataTable = () => {

    const table = $("#dataTable").DataTableTh({
      "serverSide": true,
      "searching": false,
      "processing": true,
      "ordering": false,
      "ajax": {
        "url": '/ims-webapp/api/ia/int066/findAll',
        "contentType": "application/json",
        "type": "POST",
        "data": (d) => {
          return JSON.stringify($.extend({}, d, {
            "sector": $("#sector").val(),
            "area": $("#area").val(),
            "branch": $("#branch").val(),
            "dateFrom": $("#dateFrom").val(),
            "dateTo": $("#dateTo").val(),
            "searchFlag": this.form.searchFlag,
            "budgetType": $("#budgetType").val(),
          }));
        },
      },

      "columns": [
        {
          "data": "dateOfPay",
          "render": function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          "className": "ui center aligned"
        }, {
          "data": "payee",
          "className": "ui center aligned"
        }, {
          "data": "refPayment",
          "className": "ui center aligned"
        }, {
          "data": "budgetType",
          "className": "ui left aligned"
        },  {
          "data": "itemDesc",
          "className": "ui center aligned"
        },{
          "data": "amount",
          "render": (data)=>{
            return Utils.moneyFormatDecimal(data);
          },
          "className": "ui right aligned"
        },{
          "data": "paymentDate",
          "className": "ui center aligned"
        }
      ]
    });
  }
}