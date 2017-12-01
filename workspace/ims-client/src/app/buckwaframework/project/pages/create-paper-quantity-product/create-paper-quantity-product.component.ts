import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'create-paper-quantity-product',
  templateUrl: './create-paper-quantity-product.component.html',
  styleUrls: ['./create-paper-quantity-product.component.css']
})
export class CreatePaperQuantityProductComponent implements OnInit {

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
