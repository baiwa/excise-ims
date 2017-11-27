import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'app-int08-1-3',
  templateUrl: './int08-1-3.component.html',
  styleUrls: ['./int08-1-3.component.css']
})
export class Int0813Component implements OnInit {

  constructor() { }

  ngOnInit() {

  }

  ngAfterViewInit() {
    $('#select1').hide();
    $('#select2').hide();
    $('#select3').hide();
  }

  popupEditData() {
    $('#select1').show();
    $('#select2').show();
    $('#select3').show();
    $('#modalInt0813').modal('show');
  }

  closePopupEdit() {
    $('#select1').hide();
    $('#select2').hide();
    $('#select3').hide();
    $('#modalInt0813').modal('hide');
  }

  popupWeightData() {
    $('#modalInt0813-weight-data').modal('show');
  }

  clearPopupWeightData() {
    $('#modalInt0813-weight-data').modal('hide');
  }

}
