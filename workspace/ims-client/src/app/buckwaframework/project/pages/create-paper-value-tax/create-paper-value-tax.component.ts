import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'create-paper-value-tax',
  templateUrl: './create-paper-value-tax.component.html',
  styleUrls: ['./create-paper-value-tax.component.css']
})
export class CreatePaperValueTaxComponent implements OnInit {

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
