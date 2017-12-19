import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'int08-3-3-3',
  templateUrl: './int08-3-3-3.component.html',
  styleUrls: ['./int08-3-3-3.component.css']
})
export class Int08333Component implements OnInit {

  constructor() { }

  ngOnInit() {

  }

  ngAfterViewInit() {
    $('#select1').hide();
    $('#select2').hide();
    $('#select3').hide();
    $('#selectCondition1').dropdown();
    $('#selectCondition2').dropdown();
    $('#selectCondition3').dropdown();
    $('#selectColor1').dropdown();
    $('#selectColor2').dropdown();
    $('#selectColor3').dropdown();
  }

  popupEditData() {
    $('#select1').show();
    $('#select2').show();
    $('#select3').show();
    $('#modalInt08333').modal('show');
  }

  closePopupEdit() {
    $('#select1').hide();
    $('#select2').hide();
    $('#select3').hide();
    $('#modalInt08333').modal('hide');
  }

  popupWeightData() {
    $('#modalInt08333-weight-data').modal('show');
  }

  clearPopupWeightData() {
    $('#modalInt08333-weight-data').modal('hide');
  }

}
