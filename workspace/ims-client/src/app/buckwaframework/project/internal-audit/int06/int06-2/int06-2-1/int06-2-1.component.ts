import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

declare var $: any;
@Component({
  selector: 'int06-2-1',
  templateUrl: './int06-2-1.component.html',
  styleUrls: ['./int06-2-1.component.css']
})
export class Int0621Component implements OnInit {



  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-06210');
  this.hideData();
  }

  ngAfterViewInit() {
    $('#multi-select1').dropdown();
    $('#multi-select2').dropdown();
    $('#multi-select3').dropdown();
  }

  hideData() {
    $('#Int0621').hide();
  }
  showData() {
    $('#Int0621').show();
  }
}
