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
    $('#selectTrading').hide();
  }

  addData() {
    $('#modalSaveResultTrading').modal('show');
    $('#selectTrading').show();
  }

  editData() {
    $('#modalSaveResultTrading').modal('show');
    $('#selectTrading').show();
  }

  closeModal() {
    $('#modalSaveResultTrading').modal('hide');
  }
}
