import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ope05-7',
  templateUrl: './ope05-7.component.html',
  styleUrls: ['./ope05-7.component.css']
})
export class Ope057Component implements OnInit {

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
