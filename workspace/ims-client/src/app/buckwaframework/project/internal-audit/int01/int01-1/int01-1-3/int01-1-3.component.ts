import { Component, OnInit } from '@angular/core';
declare var $: any;
@Component({
  selector: 'app-int01-1-3',
  templateUrl: './int01-1-3.component.html',
  styleUrls: ['./int01-1-3.component.css']
})
export class Int0113Component implements OnInit {
 
  private selectedProduct:string = 'สำนักงานสรรพสามิตพื้นที่เมืองพิษณุโลก ';
  private productList: any[];
  constructor() { }

  ngOnInit() {

    this.productList = [
      { 'value': 'สำนักงานสรรพสามิตพื้นที่เมืองพิษณุโลก '},
      { 'value': 'สำนักงานสรรพสามิตพื้นที่เมืองพิษณุโลก '},
      { 'value': 'สำนักงานสรรพสามิตพื้นที่เมืองพิษณุโลก '},
      { 'value': 'สำนักงานสรรพสามิตพื้นที่เมืองพิษณุโลก '},
      { 'value': 'สำนักงานสรรพสามิตพื้นที่เมืองพิษณุโลก '},
      { 'value': 'สำนักงานสรรพสามิตพื้นที่เมืองพิษณุโลก '},
    ];
  }
  popupEditData() {
    $('#modalEditData').modal('show');
    $('#modalInt062').modal('show');
    $('#selectTrading').show();
  }

  closePopupEdit() {
    $('#selectTrading').show();
    $('#modalEditData').modal('hide');
    $('#modalInt062').modal('hide');
  }
}
