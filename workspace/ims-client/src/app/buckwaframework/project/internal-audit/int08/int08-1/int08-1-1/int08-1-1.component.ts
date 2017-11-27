import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'int08-1-1',
  templateUrl: './int08-1-1.component.html',
  styleUrls: ['./int08-1-1.component.css']
})
export class Int0811Component implements OnInit {

  private showData: boolean = false;

  constructor() { }

  ngOnInit() {

  }

  ngAfterViewInit() {
    $('#select1').hide();
    $('#select2').hide();
    $('#select3').hide();
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
    $('#modalInt0811').modal('show');
  }

  closePopupEdit() {
    $('#select1').hide();
    $('#select2').hide();
    $('#select3').hide();
    $('#modalInt0811').modal('hide');
  }
}
