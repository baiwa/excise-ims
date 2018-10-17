import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
declare var $: any;
@Component({
  selector: 'app-int07-7-2',
  templateUrl: './int07-7-2.component.html',
  styleUrls: ['./int07-7-2.component.css']
})
export class Int0772Component implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-07720');
    $('.menu .item')
  .tab()
;
  }

}
