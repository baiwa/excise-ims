import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'int06-2-1',
  templateUrl: './int06-2-1.component.html',
  styleUrls: ['./int06-2-1.component.css']
})
export class Int0621Component implements OnInit {

  private showData: boolean = false;

  constructor() { }

  ngOnInit() {
  }

  ngAfterViewInit() {
    $('#multi-select1').dropdown();
    $('#multi-select2').dropdown();
    $('#multi-select3').dropdown();
  }

  compareData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }

}
