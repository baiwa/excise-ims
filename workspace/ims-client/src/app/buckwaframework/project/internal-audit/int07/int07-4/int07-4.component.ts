import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

declare var $: any;
@Component({
  selector: 'int07-4',
  templateUrl: './int07-4.component.html',
  styleUrls: ['./int07-4.component.css']
})
export class Int074Component implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-07400');

  }
  ngAfterViewInit() {
    $('#export .dropdown')
      .dropdown({
        transition: 'drop'
      });
      
  }

  popupEditData() {
    $('#modalInt074').modal('show');
  }

  closePopupEdit() {
    $('#modalInt074').modal('hide');
  }
}
