import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'int05-4',
  templateUrl: './int05-4.component.html',
  styleUrls: ['./int05-4.component.css']
})
export class Int054Component implements OnInit {

  constructor() { }

  ngOnInit() {
    $('#selectTrading').hide();
  }

  addData() {
    $('#modalInt054').modal('show');
    $('#selectTrading').show();
  }

  editData() {
    $('#modalInt054').modal('show');
    $('#selectTrading').show();
  }

  closeModal() {
    $('#modalInt054').modal('hide');
  }
}
