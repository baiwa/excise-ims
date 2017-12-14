import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ope05-2',
  templateUrl: './ope05-2.component.html',
  styleUrls: ['./ope05-2.component.css']
})
export class Ope052Component implements OnInit {

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
