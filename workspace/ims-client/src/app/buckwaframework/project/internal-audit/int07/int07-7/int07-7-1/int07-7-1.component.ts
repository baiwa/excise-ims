import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

declare var $: any;
@Component({
  selector: 'app-int07-7-1',
  templateUrl: './int07-7-1.component.html',
  styleUrls: ['./int07-7-1.component.css']
})
export class Int0771Component implements OnInit {

  constructor(
    private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-07710');
  }

  popupEditData() {
    $('#modalInt0771').modal('show');
  }

  closePopupEdit() {
    $('#modalInt0771').modal('hide');
  }


}
