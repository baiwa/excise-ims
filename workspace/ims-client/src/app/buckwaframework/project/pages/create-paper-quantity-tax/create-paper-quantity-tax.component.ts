import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'create-paper-quantity-tax',
  templateUrl: './create-paper-quantity-tax.component.html',
  styleUrls: ['./create-paper-quantity-tax.component.css']
})
export class CreatePaperQuantityTaxComponent implements OnInit {

  public showData: boolean = false;

  constructor() { }

  ngOnInit() {
  }

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }

}
