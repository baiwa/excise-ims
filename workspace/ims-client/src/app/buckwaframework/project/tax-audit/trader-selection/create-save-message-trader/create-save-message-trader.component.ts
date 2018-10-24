import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'app-create-save-message-trader',
  templateUrl: './create-save-message-trader.component.html',
  styleUrls: []
})
export class CreateSaveMessageTraderComponent implements OnInit {

  constructor(
    private authService: AuthService
  ) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('TAX-03000');
  }

}
