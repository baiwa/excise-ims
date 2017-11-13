import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'app-save-result-trading',
  templateUrl: './save-result-trading.component.html',
  styleUrls: ['./save-result-trading.component.css']
})
export class SaveResultTradingComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  

  addData() {
    $('#modalSaveResultTrading').modal('show');
  }

  editData() {
    $('#modalSaveResultTrading').modal('show');
  }

  closeModal() {
    $('#modalSaveResultTrading').modal('hide');
  }
}
