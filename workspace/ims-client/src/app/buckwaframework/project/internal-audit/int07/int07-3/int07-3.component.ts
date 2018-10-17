import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

declare var $: any;
@Component({
  selector: 'int07-3',
  templateUrl: './int07-3.component.html',
  styleUrls: ['./int07-3.component.css']
})
export class Int073Component implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-07300');
  }
  ngAfterViewInit() {
    $('#export .dropdown')
      .dropdown({
        transition: 'drop'
      });
      
  }

  popupEditData() {
    $('#modalInt073').modal('show');
  }

  closePopupEdit() {
    $('#modalInt073').modal('hide');
  }

}
