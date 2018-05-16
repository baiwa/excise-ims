import { Component, OnInit } from '@angular/core';
declare var $: any;
@Component({
  selector: 'int06-5-2',
  templateUrl: './int06-5-2.component.html',
  styleUrls: ['./int06-5-2.component.css']
})
export class Int0652Component implements OnInit {

  constructor() { }

  ngOnInit() {
    $('#calendar').fullCalendar({
  
    });
  }

}
