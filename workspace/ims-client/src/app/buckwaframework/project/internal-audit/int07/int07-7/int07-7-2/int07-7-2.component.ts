import { Component, OnInit } from '@angular/core';
declare var $: any;
@Component({
  selector: 'app-int07-7-2',
  templateUrl: './int07-7-2.component.html',
  styleUrls: ['./int07-7-2.component.css']
})
export class Int0772Component implements OnInit {

  constructor() { }

  ngOnInit() {
    $('.menu .item')
  .tab()
;
  }

}
