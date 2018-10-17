import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
declare var $: any;
@Component({
  selector: 'int06-3-2',
  templateUrl: './int06-3-2.component.html',
  styleUrls: ['./int06-3-2.component.css']
})
export class Int0632Component implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-06320');
    $('#calendar').fullCalendar({
  
    });
    
  }
 
  

}


