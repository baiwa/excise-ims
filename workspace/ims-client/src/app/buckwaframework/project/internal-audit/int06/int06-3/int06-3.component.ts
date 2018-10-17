import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

declare var $: any;
@Component({
  selector: 'int06-3',
  templateUrl: './int06-3.component.html',
  styleUrls: ['./int06-3.component.css']
})
export class Int063Component implements OnInit {

  private showData: boolean = false;

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-06300');

  }

  clearData() {
    this.showData = false;
  }

  popupEditData() {
    $('#modalAllowanceWithdrawal').modal('show');
  }

  closePopupEdit() {
    $('#modalAllowanceWithdrawal').modal('hide');
  }

  editData() {
    this.showData = true;
  }
}
