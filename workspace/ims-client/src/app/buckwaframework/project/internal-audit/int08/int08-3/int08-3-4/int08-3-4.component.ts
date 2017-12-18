import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'int08-3-4',
  templateUrl: './int08-3-4.component.html',
  styleUrls: ['./int08-3-4.component.css']
})
export class Int0834Component implements OnInit {

  private showData: boolean = false;

  constructor() { }

  ngOnInit() {

  }

  ngAfterViewInit() {
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
    $('#modalInt0834').modal('show');
  }

  closePopupEdit() {
    $('#modalInt0834').modal('hide');
  }

}
