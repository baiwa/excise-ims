import { Component, OnInit } from '@angular/core';

declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-int09-2-1-4',
  templateUrl: './int09-2-1-4.component.html'
})
export class Int09214Component implements OnInit {

  constructor() { }

  ngOnInit() {
    $('.menu .item').tab();
  }

  ngAfterViewInit(){
   
  }

}
