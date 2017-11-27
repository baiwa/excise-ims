import { Component, OnInit } from '@angular/core';


declare var $: any;
@Component({
  selector: 'int08-3-5',
  templateUrl: './int08-3-5.component.html',
  styleUrls: ['./int08-3-5.component.css']
})
export class Int0835Component implements OnInit {

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
    $('#modalInt0835').modal('show');
  }

  closePopupEdit() {
    $('#select1').hide();
    $('#select2').hide();
    $('#select3').hide();
    $('#modalInt0835').modal('hide');
  }

}
