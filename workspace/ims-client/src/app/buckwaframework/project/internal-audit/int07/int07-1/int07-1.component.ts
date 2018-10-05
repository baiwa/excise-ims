import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'int07-1',
  templateUrl: './int07-1.component.html',
  styleUrls: ['./int07-1.component.css']
})
export class Int071Component implements OnInit {

  constructor() { }


  ngOnInit() {
    this.hideData();

    $('#Int072').DataTable({
      createdRow: function (row, data, dataIndex) {
       console.log("row" , row);
       console.log("data" , data);
       console.log("dataIndex" , dataIndex);
          //$(row).addClass('bg-c-red');
        
       

      }         

    });

  }
  hideData() {
    $('#Int072').hide();
  }
  showData() {
    $('#Int072').show();
  }
  popupEditData() {
    $('#modalInt071').modal('show');
  }

  closePopupEdit() {
    $('#modalInt071').modal('hide');
  }



}
