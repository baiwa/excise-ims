import { Component, OnInit } from '@angular/core';
  
declare var $: any;
@Component({
  selector: 'int01-2-2',
  templateUrl: './int01-2-2.component.html',
  styleUrls: ['./int01-2-2.component.css']
})
export class Int0122Component implements OnInit {
  private showData: boolean = false;
  constructor() { }

  ngOnInit() {
    
    
    $('#idint').hide();
    $('#id').hide();
  }
  
  popupEditData() {
    $('#modalEditData').modal('show');
    $('#modalInt062').modal('show');
    $('#idint').show();
    $('#id').show();
    $('#selectTrading').show();
  }

  closePopupEdit() {
    $('#selectTrading').show();
    $('#modalEditData').modal('hide');
    $('#modalInt062').modal('hide');
  }
  
  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }

}

