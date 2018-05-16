import { Component, OnInit } from '@angular/core';
declare var $: any;
@Component({
  selector: 'int06-3-2',
  templateUrl: './int06-3-2.component.html',
  styleUrls: ['./int06-3-2.component.css']
})
export class Int0632Component implements OnInit {

  constructor() { }

  ngOnInit() {
    $('#calendar').fullCalendar({
  
    });
    
  }
 
  

}


