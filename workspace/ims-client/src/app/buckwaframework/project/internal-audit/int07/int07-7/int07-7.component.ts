import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

declare var $: any;
@Component({
  selector: 'int07-7',
  templateUrl: './int07-7.component.html',
  styleUrls: ['./int07-7.component.css']
})
export class Int077Component implements OnInit {

  constructor(private authService: AuthService) { }


  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-07700');
  }
  ngAfterViewInit() {
    $('#export .dropdown')
      .dropdown({
        transition: 'drop'
      });
      
  }

  popupEditData() {
    $('#modalInt0771').modal('show');
  }

  closePopupEdit() {
    $('#modalInt0771').modal('hide');
  }
}
