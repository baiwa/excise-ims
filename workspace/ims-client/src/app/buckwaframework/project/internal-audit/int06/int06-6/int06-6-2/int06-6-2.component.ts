import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
declare var $: any;
@Component({
  selector: 'int06-6-2',
  templateUrl: './int06-6-2.component.html',
  styleUrls: ['./int06-6-2.component.css']
})
export class Int0662Component implements OnInit {

  constructor(
    private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-06620');
    $('#calendar').fullCalendar({
  
    });
  }

}
