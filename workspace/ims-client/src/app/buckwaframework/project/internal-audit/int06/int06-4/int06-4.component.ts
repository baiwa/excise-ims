import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

declare var $: any;
@Component({
  selector: 'int06-4',
  templateUrl: './int06-4.component.html',
  styleUrls: ['./int06-4.component.css']
})
export class Int064Component implements OnInit {

  private showData: boolean = false;

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-06400');
  }

  clearData() {
    this.showData = false;
  }

  popupEditData() {
    $('#modalOtWithdrawal').modal('show');
  }

  closePopupEdit() {
    $('#modalOtWithdrawal').modal('hide');
  }

  editData() {
    this.showData = true;
  }

}
