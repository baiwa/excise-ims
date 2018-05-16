import { Component, OnInit } from '@angular/core';
declare var $: any;
@Component({
  selector: 'int06-4-2',
  templateUrl: './int06-4-2.component.html',
  styleUrls: ['./int06-4-2.component.css']
})
export class Int0642Component implements OnInit {

  constructor() { }

  ngOnInit() {
    $('#calendar').fullCalendar({
  
    });
  }

}
