import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'create-paper-except-tax',
  templateUrl: './create-paper-except-tax.component.html',
  styleUrls: ['./create-paper-except-tax.component.css']
})
export class CreatePaperExceptTaxComponent implements OnInit {

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
