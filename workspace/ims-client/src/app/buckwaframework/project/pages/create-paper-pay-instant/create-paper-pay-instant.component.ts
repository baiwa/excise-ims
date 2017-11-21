import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'create-paper-pay-instant',
  templateUrl: './create-paper-pay-instant.component.html',
  styleUrls: ['./create-paper-pay-instant.component.css']
})
export class CreatePaperPayInstantComponent implements OnInit {

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
