import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ope05-3',
  templateUrl: './ope05-3.component.html',
  styleUrls: ['./ope05-3.component.css']
})
export class Ope053Component implements OnInit {

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
