import { Injectable } from "@angular/core";
import { AjaxService } from "services/ajax.service";
import { MessageBarService } from "../../../../common/services";
import { FormSearch } from "projects/internal-audit/int06/int06-6/form-search.model";

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

    const table = $("#dataTable").DataTable({
      "serverSide": true,
      "searching": false,
      "processing": true,
      "ordering": false,
      "scrollX": true,
      "ajax": {
        "url": '/ims-webapp/api/ia/int065/findAll',
        "contentType": "application/json",
        "type": "POST",
        "data": (d) => {
          return JSON.stringify($.extend({}, d, {
            "sector": $("#sector").val(),//($("#sector option:selected").text()=="กรุณาเลือก" ? "":$("#sector option:selected").text()),
            "area": $("#area").val(),//($("#area option:selected").text()=="กรุณาเลือก" ? "":$("#area option:selected").text()),
            "branch": $("#branch").val(),//($("#branch option:selected").text()=="กรุณาเลือก" ? "":$("#branch option:selected").text()),
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
          "data": "paymentDate",
          "className": "ui center aligned"
        }, {
          "data": "refPayment",
          "className": "ui center aligned"
        }, {
          "data": "amount",
          "className": "ui center aligned"
        }, {
          "data": "budgetType",
          "className": "ui center aligned"
        }, {
          "data": "itemDesc",
          "className": "ui center aligned"
        }, {
          "data": "payee",
          "className": "ui center aligned"
        },
      ]
    });
  }
}