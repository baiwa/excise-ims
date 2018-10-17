import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

declare var $: any;
@Component({
  selector: 'int07-5',
  templateUrl: './int07-5.component.html',
  styleUrls: ['./int07-5.component.css']
})
export class Int075Component implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-07500');
  }
  ngAfterViewInit() {
    $('#export .dropdown')
      .dropdown({
        transition: 'drop'
      });
      
  }

  popupEditData() {
    $('#modalInt075').modal('show');
  }

  closePopupEdit() {
    $('#modalInt075').modal('hide');
  }
}
