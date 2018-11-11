import { Injectable } from '@angular/core';
import { AjaxService } from 'services/ajax.service';
import { Router } from '@angular/router';
declare var $: any;
@Injectable()
export class Ope0431Service {
 // ==> params
 form: FromVo = new FromVo();
 table: any;
 constructor(
     private router: Router,
     private ajax: AjaxService
 ) { }
 findExciseId = (): Promise<any> => {
     return new Promise((resolve, reject) => {
         let url = "ta/opo0431/findExciseId";
         return this.ajax.get(url, res => {
             resolve(res.json());
         })
     })
 }

 search = (form: any) => {

     this.form = form;
     this.form.searchFlag = "TRUE";        
     this.table.ajax.reload();
 }

 claer = (form: any) => {
     $("#exciseId").dropdown('restore defaults');
     this.form = form;
     this.form.searchFlag = "FALSE";        
     this.table.ajax.reload();
 }

 datatable = () => {
     this.table = $("#dataTable").DataTableTh({
         "serverSide": true,
         "processing": true,
         "scrollX": true,
         "paging": true,
         "ajax": {
             "url": '/ims-webapp/api/ta/opo0431/findAll',
             "contentType": "application/json",
             "type": "POST",
             "data": (d) => {
                 return JSON.stringify($.extend({}, d, this.form));

             },
         },
         "columns": [
             {
                 "data": "taTaxReduceWsHdrId",
                 "render": function (data, type, row, meta) {
                     return meta.row + meta.settings._iDisplayStart + 1;
                 },
                 "className": "ui center aligned"
             }, {
                 "data": "exciseId",
                 "className": "ui left aligned"
             }, {
                 "data": "taAnalysisId",
                 "className": "ui left aligned",
             },  {
                 "data": "startDate",
                 "className": "ui center aligned"
             }, {
                 "data": "endDate",
                 "className": "ui center aligned",
             }, {
                 "data": "result",
                 "className": "ui center aligned",
                 "render": (data) => {
                     return '  <button class="ui mini blue button btn-detail" type="button" ><i class="eye icon"></i> รายละเอียด</button>'
                 }

             },
         ],
         // "drawCallback": (settings) => {
         //     $('.r-mark-tr').closest('td').addClass('background-color : red');
         //     $('.g-mark-tr').closest('td').addClass('background-color : green');
         // }

     });
     this.table.on('click', 'tbody tr button.btn-detail', (e) => {
         var closestRow = $(e.target).closest('tr');
         var data = this.table.row(closestRow).data();
         console.log(data);
         this.router.navigate(["/ope04/3-2"], {
             queryParams: {
                 id: data.taTaxReduceWsHdrId
             }
         });
     });
     // this.table.clear().draw();
     // this.table.rows.add(this.data); // Add new data
     // this.table.columns.adjust().draw(); // Redraw the DataTable\
 }
}
class FromVo {
 endDate: string = "";
 exciseId: string = "";
 searchFlag: string = "";
 startDate: string = "";
}