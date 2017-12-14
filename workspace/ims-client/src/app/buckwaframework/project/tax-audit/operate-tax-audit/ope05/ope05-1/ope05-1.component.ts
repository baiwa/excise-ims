import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ope05-1',
  templateUrl: './ope05-1.component.html',
  styleUrls: ['./ope05-1.component.css']
})
export class Ope051Component implements OnInit {

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
