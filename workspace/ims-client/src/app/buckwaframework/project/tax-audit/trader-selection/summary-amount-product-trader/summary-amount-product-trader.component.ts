import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'app-summary-amount-product-trader',
  templateUrl: './summary-amount-product-trader.component.html',
  styleUrls: []
})
export class SummaryAmountProductTraderComponent implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('TAX-06000');
  }

}
