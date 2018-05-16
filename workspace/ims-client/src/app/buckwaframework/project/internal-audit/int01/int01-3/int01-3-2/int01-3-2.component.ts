import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'app-int01-3-2',
  templateUrl: './int01-3-2.component.html',
  styleUrls: ['./int01-3-2.component.css']
})
export class Int0132Component implements OnInit {
  private productList: any[];
  
  private selectedProduct:string = 'สุรา';

  constructor() { }

  ngOnInit() {

    
    this.productList = [
      { 'value': 'เครื่องดื่ม'},
      { 'value': 'สุราแช่'},
      { 'value': 'สุราแช่ชุมชน'},
      { 'value': 'สุรากลั่น'},
      { 'value': 'สุรากลั่นชุมชน'},
      { 'value': 'ยาสูบ'},
      
    ];
    $('#id').hide();
  }
  onChange(newValue) {
    console.log(newValue);
    this.selectedProduct = newValue;  // don't forget to update the model here
}

popupEditData() {
  $('#modalEditData').modal('show');
  $('#modalInt062').modal('show');
  $('#id').show();
  $('#selectTrading').show();
}

closePopupEdit() {
  $('#selectTrading').show();
  $('#modalEditData').modal('hide');
  $('#modalInt062').modal('hide');
}

}
