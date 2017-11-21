import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'create-paper-check-value',
  templateUrl: './create-paper-check-value.component.html',
  styleUrls: ['./create-paper-check-value.component.css']
})
export class CreatePaperCheckValueComponent implements OnInit {

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
