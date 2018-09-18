import { Injectable } from '@angular/core';
import { AjaxService } from '../../../../../common/services';
import { File } from '../../../../../common/models';

declare var $: any;

@Injectable()
export class Int0611Service {
  formVo: FormVo;
  table: any;
  data: any;
  constructor(
    private ajax: AjaxService
  ) {
    this.formVo = new FormVo();
    this.data = [];
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

  dataTable = () => {
    this.table = $("#dataTable").DataTable({
      "serverSide": false,
      "searching": false,
      "ordering": false,
      "processing": true,
      "scrollX": true,
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
    this.table.clear().draw();
    this.table.rows.add(this.data); // Add new data
    this.table.columns.adjust().draw(); // Redraw the DataTable    
  }
}

class FormVo {
  fileName: File[]
}
