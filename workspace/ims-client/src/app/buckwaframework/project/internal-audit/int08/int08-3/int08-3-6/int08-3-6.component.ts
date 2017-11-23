import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'int08-3-6',
  templateUrl: './int08-3-6.component.html',
  styleUrls: ['./int08-3-6.component.css']
})
export class Int0836Component implements OnInit {

  constructor() { }

  ngOnInit() {

  }

  ngAfterViewInit() {
    $('#select1').hide();
    $('#select2').hide();
    $('#select3').hide();
  }

  popupEditData() {
    $('#modalInt0836').modal('show');
    $('#select1').show();
    $('#select2').show();
    $('#select3').show();
  }

  closePopupEdit() {
    $('#select1').hide();
    $('#select2').hide();
    $('#select3').hide();
    $('#modalInt0836').modal('hide');
  }

  popupWeightData() {
    $('#modalInt0836-weight-data').modal('show');
  }

  clearPopupWeightData() {
    $('#modalInt0836-weight-data').modal('hide');
  }

}
