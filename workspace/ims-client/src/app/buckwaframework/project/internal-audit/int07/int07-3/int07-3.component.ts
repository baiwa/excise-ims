import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'int07-3',
  templateUrl: './int07-3.component.html',
  styleUrls: ['./int07-3.component.css']
})
export class Int073Component implements OnInit {

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
    $('#modalInt073').modal('show');
  }

  closePopupEdit() {
    $('#modalInt073').modal('hide');
  }

}
