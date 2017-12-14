import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ope05-5',
  templateUrl: './ope05-5.component.html',
  styleUrls: ['./ope05-5.component.css']
})
export class Ope055Component implements OnInit {

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
