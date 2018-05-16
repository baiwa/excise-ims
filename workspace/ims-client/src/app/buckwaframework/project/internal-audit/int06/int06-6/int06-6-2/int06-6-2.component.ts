import { Component, OnInit } from '@angular/core';
declare var $: any;
@Component({
  selector: 'int06-6-2',
  templateUrl: './int06-6-2.component.html',
  styleUrls: ['./int06-6-2.component.css']
})
export class Int0662Component implements OnInit {

  constructor() { }

  ngOnInit() {
    $('#calendar').fullCalendar({
  
    });
  }

}
