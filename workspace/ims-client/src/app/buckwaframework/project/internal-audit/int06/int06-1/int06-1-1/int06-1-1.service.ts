import { Injectable } from '@angular/core';
import { AjaxService, MessageBarService } from '../../../../../common/services';
import { File } from '../../../../../common/models';

declare var $: any;

@Injectable()
export class Int0611Service {
  formVo: FormVo;
  table: any;
  data: any;  
  formEditModal : FormEditModal;
  constructor(
    private ajax: AjaxService,
    private message : MessageBarService
  ) {
    this.formVo = new FormVo();
    this.data = [];  
    this.formEditModal = new FormEditModal();
  }

  async onSubmit(form: any) {   
    let url = await "ia/int0611/upload";
    let params = await form;
    await console.log("Params : ", params);
    return await this.ajax.upload(url, params, success => {
      this.data = success.json();
    }, error => {
      console.log("Fail!");
    }).then(data => {
      console.log(this.data);
      this.table.clear().draw();
      this.table.rows.add(this.data.data); // Add new data        
      this.table.columns.adjust().draw(); // Redraw the DataTable    
    });
  }

  onChangeUpload = async (event: any) => {    
    if (event.target.files && event.target.files.length > 0) {
      let reader = new FileReader();
      reader.onload = (e: any) => {
        const f = {
          name: event.target.files[0].name,
          type: event.target.files[0].type,
          value: e.target.result
        };
        this.formVo.fileName = [f];
        console.log(this.formVo);
      };
      reader.readAsDataURL(event.target.files[0]);
    }
  };

  edit=()=>{  
    this.formEditModal.columnId = $("#columId").val();
    this.formEditModal.accountNumber = $("#accountNumber").val();
    this.formEditModal.accountName = $("#accountName").val();
    this.formEditModal.monryComes = $("#monryComes").val();
    this.formEditModal.moneyGoes = $("#moneyGoes").val();
    this.formEditModal.debit = $("#debit").val();
    this.formEditModal.credit = $("#credit").val();
    
    const index = this.data.data.findIndex(obj => obj.columId == this.formEditModal.columnId);
    let obj = this.data.data[index];
    obj.colum0 = this.formEditModal.accountNumber;
    obj.colum2 = this.formEditModal.accountName;
    obj.colum4 = this.formEditModal.monryComes;
    obj.colum7 = this.formEditModal.debit;
    obj.colum8 = this.formEditModal.credit;
    obj.colum9 = this.formEditModal.moneyGoes;    
    
    this.table.clear().draw();
    this.table.rows.add(this.data.data); // Add new data        
    this.table.columns.adjust().draw(); // Redraw the DataTable    

    $('#edit-modal').modal('hide');
  }

  dataTable = () => { 
    this.table = $("#dataTable").DataTable({
      "serverSide": false,
      "searching": false,
      "ordering": false,
      "processing": true,
      "scrollX": true,
      "pageLength": 25,
      "data": this.data,
      "columns": [
        {
          "data": "colum0",
          "render": function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          "className": "ui center aligned"
        }, {
          "data": "colum0"
        }, {
          "data": "colum2",
          "className": "ui center aligned",
        }, {
          "data": "colum4",
          "className": "ui center aligned"
        }, {
          "data": "colum7",
          "className": "ui center aligned"
        }, {
          "data": "colum8",
          "className": "ui center aligned"
        }, {
          "data": "colum9",
          "className": "ui center aligned"
        }, {
          "data": "colum6",
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
    // this.table.clear().draw();
    // this.table.rows.add(this.data.data); // Add new data
    // this.table.columns.adjust().draw(); // Redraw the DataTable    

    //on click edit
    this.table.on('click', 'tbody tr button.btn-edit', (e) => {
      var closestRow = $(e.target).closest('tr');
      var data = this.table.row(closestRow).data(); 
      $('#edit-modal').modal({
        onShow : ()=>{
          $("#columId").val(data.columId);
          $("#accountNumber").val(data.colum0);
          $("#accountName").val(data.colum2);
          $("#monryComes").val(data.colum4);
          $("#debit").val(data.colum7);
          $("#credit").val(data.colum8);
          $("#moneyGoes").val(data.colum9);
        },
        autofocus : false
      }).modal('show');

    });

    this.table.on('click', 'tbody tr button.btn-delete', (e) => {
      var closestRow = $(e.target).closest('tr');
      var data = this.table.row(closestRow).data(); 
      this.message.comfirm((res)=>{
        if (res) {
          const index = this.data.data.findIndex(obj => obj.columId == data.columId);
          this.data.data.splice(index, 1);
          this.table.clear().draw();
          this.table.rows.add(this.data.data); // Add new data
          this.table.columns.adjust().draw(); // Redraw the DataTable    
        }
      },"","ยืนยันการลบ");      
    });
  }
}

class FormVo {
  fileName: File[]
}
class FormEditModal{  
  accountNumber : string;
  accountName : string;
  monryComes : number;
  moneyGoes : number;
  debit : string;
  credit : string;
  columnId : string;
}