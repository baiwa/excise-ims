import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ope05-4',
  templateUrl: './ope05-4.component.html',
  styleUrls: ['./ope05-4.component.css']
})
export class Ope054Component implements OnInit {

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
