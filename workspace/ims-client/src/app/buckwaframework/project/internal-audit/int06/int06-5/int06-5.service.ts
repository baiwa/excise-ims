import { Injectable } from "@angular/core";
import { AjaxService } from "services/ajax.service";
import { MessageBarService } from "services/message-bar.service";
import { FormSearch } from "projects/internal-audit/int06/int06-5/from-search.model";
import { Utils } from "helpers/utils";

declare var $: any;

@Injectable()
export class Int065Service {

  form : FormSearch = new FormSearch();
  constructor(
    private ajax: AjaxService,
    private message: MessageBarService
  ) {
    // TODO
  }

  sector = ():Promise<any> => {
    let url = "ia/int065/sector";
   
    return  new Promise((resolve, reject) => {
      this.ajax.get(url,res=>{
        resolve(res.json())
    })});
  }
  area = (e) => {
    let url = "ia/int065/area";   
    return  new Promise((resolve, reject) => {
      this.ajax.post(url,JSON.stringify(e),res=>{
        resolve(res.json())
    })});
  }
  branch = (e) => {
    let url = "ia/int065/branch";   
    return  new Promise((resolve, reject) => {
      this.ajax.post(url,JSON.stringify(e),res=>{
        resolve(res.json())
    })});
  }
  search = () => {
    this.form.searchFlag = "TRUE";
    $("#dataTable").DataTable().ajax.reload();
  }
  clear = () =>{
    this.form.searchFlag = "FALSE";
      $("#dataTable").DataTable().ajax.reload();
    
    console.log(this.form.searchFlag);

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
          "className": "ui left aligned"
        }, {
          "data": "amount",
          "className": "ui right aligned",
          "render" : (data) =>{
            return Utils.moneyFormat(data);
          }
        }, {
          "data": "budgetType",
          "className": "ui left aligned"
        }, {
          "data": "itemDesc",
          "className": "ui left aligned"
        }, {
          "data": "payee",
          "className": "ui left aligned"
        }, 
      ]
    });    
  }  
  
}
