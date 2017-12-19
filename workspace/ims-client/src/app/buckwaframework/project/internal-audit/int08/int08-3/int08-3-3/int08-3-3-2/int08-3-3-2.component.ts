import { Component, OnInit } from '@angular/core';

declare var $: any
@Component({
  selector: 'int08-3-3-2',
  templateUrl: './int08-3-3-2.component.html',
  styleUrls: ['./int08-3-3-2.component.css']
})
export class Int08332Component implements OnInit {

  private showData: boolean = false;

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

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }

  popupEditData() {
    $('#select1').show();
    $('#select2').show();
    $('#select3').show();
    $('#modalInt08332').modal('show');
  }

  closePopupEdit() {
    $('#select1').hide();
    $('#select2').hide();
    $('#select3').hide();
    $('#modalInt08332').modal('hide');
  }
}
