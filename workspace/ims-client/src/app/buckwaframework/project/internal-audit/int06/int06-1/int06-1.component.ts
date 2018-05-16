import { Component, OnInit } from '@angular/core';
declare var $: any;
@Component({
  selector: 'int06-1',
  templateUrl: './int06-1.component.html',
  styleUrls: ['./int06-1.component.css']
})
export class Int061Component implements OnInit {

  private showData: boolean = false;

  constructor() { }

  ngOnInit() {
  }
  ngAfterViewInit() {
    $('#export .dropdown')
      .dropdown({
        transition: 'drop'
      });
      
  }
  popupEditData() {
    $('#modalInt062').modal('show');
  }

  closePopupEdit() {
    $('#modalInt062').modal('hide');
  }


  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }

}
